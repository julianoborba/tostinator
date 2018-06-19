package org.br.reversing;

import org.apache.commons.text.StringEscapeUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringTransformerExecutor {

    private static final Pattern PATTERN_TO_CAPTURE_FILLED_QUOTATION_MARK = Pattern.compile("([\"'])(?:(?=(\\\\?))\\2.)*?\\1");
    private static final String TRANSFORMER_IMPLEMENTATION = "org.br.reversing.StringTransformerImpl";

    private final Boolean debug;

    public StringTransformerExecutor(Boolean debug) {
        this.debug = debug;
    }

    public void execute(final String fileName, final String classesPath) {

        final List<String> strings = extractStrings(fileName);
        final Object newStringTransformer = getNewStringTransformerInstance(classesPath);
        transformStrings(strings, (StringTransformer) newStringTransformer);
    }

    private List<String> extractStrings(final String fileName) {

        final List<String> strings = new ArrayList<>();
        for (final String line : getFileLines(fileName)) {

            final Matcher matcher = PATTERN_TO_CAPTURE_FILLED_QUOTATION_MARK.matcher(line);
            while (matcher.find()) {

                final String group = matcher.group();
                final String string = group.substring(1, group.length() - 1);
                if (!string.isEmpty()) {
                    strings.add(StringEscapeUtils.unescapeJava(string));
                }
            }
        }
        return strings;
    }

    private List<String> getFileLines(final String fileName) {

        try (final Stream<String> stream = Files.lines(Paths.get(fileName))) {

            return stream.collect(Collectors.toList());
        } catch (final IOException exception) {

            if (debug) {
                exception.printStackTrace();
            }
            return null;
        }
    }

    private Object getNewStringTransformerInstance(final String classesPath) {

        try {

            final URL[] urls = {new File(classesPath).toURI().toURL()};
            final ClassLoader classLoader = new URLClassLoader(urls);
            return classLoader.loadClass(TRANSFORMER_IMPLEMENTATION).newInstance();
        } catch (MalformedURLException | ClassNotFoundException | IllegalAccessException | InstantiationException exception) {
            if (debug) {
                exception.printStackTrace();
            }
            return null;
        }
    }

    private void transformStrings(final List<String> strings, final StringTransformer newStringTransformer) {

        for (final String string : strings) {

            try {

                System.out.println(newStringTransformer.apply(string));
            } catch (Exception exception) {
                if (debug) {
                    exception.printStackTrace();
                }
            }
        }
    }
}