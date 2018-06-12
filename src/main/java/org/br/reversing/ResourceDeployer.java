package org.br.reversing;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ResourceDeployer {

    private static final String ORG_BR_REVERSING = "/org/br/reversing";

    public void deploy(final String classesPath) throws IOException {

        Files.createDirectories(Paths.get(classesPath.concat(ORG_BR_REVERSING)));
        final InputStream transformerInterface = ClassLoader.getSystemResourceAsStream("StringTransformer.java");
        final InputStream transformerImplementation = ClassLoader.getSystemResourceAsStream("StringTransformerImpl.java");
        Files.copy(transformerInterface, new File(classesPath.concat(ORG_BR_REVERSING).concat("/StringTransformer.java")).toPath(), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(transformerImplementation, new File(classesPath.concat(ORG_BR_REVERSING).concat("/StringTransformerImpl.java")).toPath(), StandardCopyOption.REPLACE_EXISTING);
        org.apache.commons.io.IOUtils.closeQuietly(transformerInterface);
        org.apache.commons.io.IOUtils.closeQuietly(transformerImplementation);

        System.out.println("edit the Impl Java file in ".concat(classesPath.concat(ORG_BR_REVERSING)).concat(":"));
        System.out.println("\tcd ".concat(classesPath).concat(ORG_BR_REVERSING));
        System.out.println("and compile both Java files to use them. Repeat if Impl changes:");
        System.out.println("\tjavac ".concat(classesPath).concat(ORG_BR_REVERSING).concat("/*.java"));
    }
}
