package org.suai.lab06.converter;

import java.io.*;

public class EncodingConverter {
    public static void convert(String inEncoding, String inFile, String outEncoding, String outFile) throws IOException {

        try (Reader reader = new InputStreamReader(new FileInputStream(inFile), inEncoding);
             Writer writer = new OutputStreamWriter(new FileOutputStream(outFile), outEncoding)) {

            int c;
            while ((c = reader.read()) != -1) {
                writer.write(c);
            }
        }
    }
}
