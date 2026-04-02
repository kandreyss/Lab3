package org.suai.lab07;

import java.io.IOException;
import java.util.Random;
import org.suai.lab07.settings.Settings;

public class App {

    public static void main(String[] args) {
        Settings settings = new Settings();
        Random rnd = new Random();
        final int countOfSettings = 10000;

        for (int i = 0; i < countOfSettings; i++) {
            String key = randomString(rnd, 5 + rnd.nextInt(25));
            settings.put(key, rnd.nextInt(100));
        }

        final String binFile = "settings.bin";
        final String txtFile = "settings.txt";

        for (int i = 1; i <= 50; i++) {
            System.out.println("Test: " + i);
            testFormat(true, "Binary", binFile, settings, countOfSettings);
            testFormat(false, "Text", txtFile, settings, countOfSettings);
        }
    }

    public static String randomString(Random r, int length) {
        String letters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(letters.charAt(r.nextInt(letters.length())));
        }
        return sb.toString();
    }

    private static void testFormat(boolean isBinary, String label, String filename, Settings source, int expectedSize) {
        System.out.println("Testing " + label + " Format");
        try {
            Settings loadedSettings = new Settings();
            int saved;
            int loaded;

            if (isBinary) {
                saved = source.saveToBinaryFile(filename);
                loaded = loadedSettings.loadFromBinaryFile(filename);
            } else {
                saved = source.saveToTextFile(filename);
                loaded = loadedSettings.loadFromTextFile(filename);
            }

            if (source.equals(loadedSettings)) {
                System.out.println(label + " success: Data verified.");
            } else {
                System.err.println(label + " failure: Data mismatch.");
                System.err.printf("Expected count: %d, Saved: %d, Loaded: %d%n", expectedSize, saved, loaded);
            }
        } catch (IOException e) {
            System.err.println(label + " error: " + e.getMessage());
        }
        System.out.println();
    }
}