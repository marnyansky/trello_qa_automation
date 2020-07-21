package ru.stqa.selenium.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class StringGenerator {

    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String PUNCTUATION = "!@#$%&*()_+-=[]|,./?><";
    private boolean useLower;
    private boolean useUpper;
    private boolean useDigits;
    private boolean usePunctuation;

    private StringGenerator() {
        throw new UnsupportedOperationException("Empty constructor " +
                "for StringGenerator is not supported");
    }

    private StringGenerator(StringGeneratorBuilder builder) {
        this.useLower = builder.useLower;
        this.useUpper = builder.useUpper;
        this.useDigits = builder.useDigits;
        this.usePunctuation = builder.usePunctuation;
    }

    public static class StringGeneratorBuilder {

        private boolean useLower;
        private boolean useUpper;
        private boolean useDigits;
        private boolean usePunctuation;

        public StringGeneratorBuilder() {
            this.useLower = false;
            this.useUpper = false;
            this.useDigits = false;
            this.usePunctuation = false;
        }

        public StringGeneratorBuilder useLower(boolean useLower) {
            this.useLower = useLower;
            return this;
        }

        public StringGeneratorBuilder useUpper(boolean useUpper) {
            this.useUpper = useUpper;
            return this;
        }

        public StringGeneratorBuilder useDigits(boolean useDigits) {
            this.useDigits = useDigits;
            return this;
        }

        public StringGeneratorBuilder usePunctuation(boolean usePunctuation) {
            this.usePunctuation = usePunctuation;
            return this;
        }

        public StringGenerator build() {
            return new StringGenerator(this);
        }

    }

    public String generateString(int minLength, int maxLength) {
        if (minLength <= 0 || maxLength <= 0 || maxLength <= minLength) {
            return "";
        }

        List<String> charCategories = new ArrayList<>(4);
        if (useLower) {
            charCategories.add(LOWER);
        }
        if (useUpper) {
            charCategories.add(UPPER);
        }
        if (useDigits) {
            charCategories.add(DIGITS);
        }
        if (usePunctuation) {
            charCategories.add(PUNCTUATION);
        }

        int strLength = new Random(System.nanoTime())
                .nextInt(maxLength - minLength) + minLength;
        StringBuilder sb = new StringBuilder(strLength);
        Random random = new Random(System.nanoTime());

        // Build the password.
        for (int i = 0; i < strLength; i++) {
            String charCategory = charCategories.get(random.nextInt(charCategories.size()));
            int position = random.nextInt(charCategory.length());
            sb.append(charCategory.charAt(position));
        }
        return new String(sb);
    }

}
