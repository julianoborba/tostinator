package org.br.reversing;

public class StringTransformerImpl implements StringTransformer {

    @Override
    public String apply(String string) {
        return decoder(string);
    }

    // Malware translation functions goes below...
    // Just place it and call from the overridden method above.

    private String decoder(String string) {
        return "Find or craft your decoder... may the force be with you.";
    }
}
