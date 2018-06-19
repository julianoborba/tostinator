package org.br.reversing;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.nio.file.Paths;

import static org.br.reversing.ResourceDeployer.ORG_BR_REVERSING;
import static org.br.reversing.ResourceDeployer.STRING_TRANSFORMER_IMPL_JAVA;
import static org.br.reversing.ResourceDeployer.STRING_TRANSFORMER_JAVA;

public class ClassCompiler {

    private static final JavaCompiler JAVA_COMPILER = ToolProvider.getSystemJavaCompiler();

    public void compile(final String classesPath) {

        final String pathInterface = Paths.get(classesPath.concat(ORG_BR_REVERSING).concat("/").concat(STRING_TRANSFORMER_JAVA)).toString();
        final String pathImplementation = Paths.get(classesPath.concat(ORG_BR_REVERSING).concat("/").concat(STRING_TRANSFORMER_IMPL_JAVA)).toString();

        JAVA_COMPILER.run(null, null, null, new File(pathInterface).getPath());
        JAVA_COMPILER.run(null, null, null, new File(pathImplementation).getPath());
    }
}
