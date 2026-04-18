package org.suai.lab06;

import org.suai.lab06.input.FormattedInput;

import java.io.IOException;

public class InputTest {
    static void main(String[] args) {
        Object[] objs = null;
        try {
            objs = FormattedInput.scanf("%x %b");
        } catch (IOException e) {
            System.err.println("IO Error: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Read values: ");
        if (objs != null) {
            for (Object o : objs) {
                System.out.print(o + " ");
            }
        }
    }
}
