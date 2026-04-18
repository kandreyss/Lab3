package org.suai.lab06;

import static org.suai.lab06.converter.EncodingConverter.convert;

public class FilesTest {
    static void main(String[] args) {
        if (args.length != 4) {
            System.err.println("Usage: java org.suai.lab06.FilesTest <inEncoding> <inFile> <outFile> <outEncoding>");

            for (String arg : args) {
                System.out.println(arg);
            }

            return;
        }

        String inEncoding = args[0];
        String inFile = args[1];
        String outEncoding = args[2];
        String outFile = args[3];

        try {
            convert(inEncoding, inFile, outEncoding, outFile);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}