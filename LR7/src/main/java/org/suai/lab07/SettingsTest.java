package org.suai.lab07;

import org.suai.lab07.settings.Settings;

import java.io.IOException;
import java.util.Random;

public class SettingsTest {

    public static void main(String[] args) {
        Settings source = new Settings();
        Settings binaryLoaded = new Settings();
        Settings textLoaded = new Settings();

        Random rnd = new Random();

        int count = 10 + rnd.nextInt(11); // 10–20 настроек

        for (int i = 0; i < count; i++) {
            String key = randomString(rnd, 5 + rnd.nextInt(10));
            int value = rnd.nextInt(100);
            source.put(key, value);
        }

        String binFile = "settings.bin";
        String txtFile = "settings.txt";

        try {
            int savedBin = source.saveToBinaryFile(binFile);
            int savedTxt = source.saveToTextFile(txtFile);

            int loadedBin = binaryLoaded.loadFromBinaryFile(binFile);
            int loadedTxt = textLoaded.loadFromTextFile(txtFile);

            System.out.println("Source settings:");
            System.out.println(source);

            System.out.println("\nBinary loaded:");
            System.out.println(binaryLoaded);

            System.out.println("\nText loaded:");
            System.out.println(textLoaded);

            System.out.println("\nBinary result:");
            compare(source, binaryLoaded, savedBin, loadedBin);

            System.out.println("\nText result:");
            compare(source, textLoaded, savedTxt, loadedTxt);

        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
        }
    }

    private static void compare(Settings source, Settings loaded, int saved, int loadedCount) {
        System.out.println(source.equals(loaded)
                ? "SUCCESS: data matches"
                : "FAIL: data mismatch");

        System.out.println("Saved: " + saved + ", Loaded: " + loadedCount);
    }

    private static String randomString(Random r, int length) {
        String letters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            sb.append(letters.charAt(r.nextInt(letters.length())));
        }

        return sb.toString();
    }
}