package org.br.reversing;

import java.io.IOException;
import java.util.Arrays;

public class App {

    private static final String DEPLOY_RESOURCES = "deployResources";
    private static final String DEBUG = "debug";

    public static void main(final String[] args) throws IOException {

        if (args.length == 0) {
            System.out.println("usage");
            System.out.println("\tjava -jar tostinator.jar /classes/path file.java [options]");
            System.out.println("options");
            System.out.println("\tdebug - generate detailed exceptions");
            System.out.println("\tdeployResources - install classes on defined path");
            System.out.println("information");
            System.out.println("\tyou need to deploy that tool resources and grab the translation method");
            System.out.println("\tfrom your sample and place it in the indicated area");
            System.out.println("usage sample");
            System.out.println("\tto install resources:");
            System.out.println("\t\tjava -jar tostinator.jar \"/home/user/Classes\" deployResources");
            System.out.println("\tto get transformed strings:");
            System.out.println("\t\tjava -jar tostinator.jar \"/home/user/Classes\" \"/home/user/Downloads/malware/java/decompiled.java\"");
            return;
        }

        final Boolean deployResources = Arrays.asList(args).contains(DEPLOY_RESOURCES);
        if (deployResources) {

            final String classesPath = args[0];
            new ResourceDeployer().deploy(classesPath);
        } else {

            final Boolean debug = Arrays.asList(args).contains(DEBUG);
            final String classesPath = args[0];
            final String fileName = args[1];
            new StringTransformerExecutor(debug).execute(fileName, classesPath);
        }
    }
}