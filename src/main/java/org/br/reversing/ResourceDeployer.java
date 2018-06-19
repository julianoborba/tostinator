package org.br.reversing;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ResourceDeployer {

    public static final String ORG_BR_REVERSING = "/org/br/reversing";
    public static final String STRING_TRANSFORMER_JAVA = "StringTransformer.java";
    public static final String STRING_TRANSFORMER_IMPL_JAVA = "StringTransformerImpl.java";

    public void deploy(final String classesPath) throws IOException {

        Files.createDirectories(Paths.get(classesPath.concat(ORG_BR_REVERSING)));

        final InputStream transformerInterface = ClassLoader.getSystemResourceAsStream(STRING_TRANSFORMER_JAVA);
        Files.copy(transformerInterface, new File(classesPath.concat(ORG_BR_REVERSING).concat("/").concat(STRING_TRANSFORMER_JAVA)).toPath(), StandardCopyOption.REPLACE_EXISTING);
        org.apache.commons.io.IOUtils.closeQuietly(transformerInterface);

        final InputStream transformerImplementation = ClassLoader.getSystemResourceAsStream(STRING_TRANSFORMER_IMPL_JAVA);
        Files.copy(transformerImplementation, new File(classesPath.concat(ORG_BR_REVERSING).concat("/").concat(STRING_TRANSFORMER_IMPL_JAVA)).toPath(), StandardCopyOption.REPLACE_EXISTING);
        org.apache.commons.io.IOUtils.closeQuietly(transformerImplementation);

        System.out.println("\n\nedit the transformer implementation, adding the");
        System.out.println("malware translation function in the reserved area\n");
        System.out.println("\tnano ".concat(classesPath).concat(ORG_BR_REVERSING).concat("/").concat(STRING_TRANSFORMER_IMPL_JAVA).concat("\n"));
    }
}
