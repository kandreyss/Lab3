package org.suai.lab06.input;

import java.io.*;
import java.util.*;

public class FormattedInput {

    private enum State {
        TEXT,
        PERCENT
    }

    public static Object[] scanf(String format) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                return internalScanf(reader, format);
            } catch (RuntimeException e) {
                System.err.println(e.getMessage());
                System.err.println("Invalid input. Please try again:");
            }
        }
    }

    public static Object[] sscanf(String in, String format) throws IOException {
        return internalScanf(new BufferedReader(new StringReader(in)), format);
    }

    private static Object[] internalScanf(BufferedReader reader, String format) throws IOException {
        List<Object> result = new ArrayList<>();
        State state = State.TEXT;

        for (int i = 0; i < format.length(); i++) {
            char c = format.charAt(i);

            switch (state) {
                case TEXT:
                    if (c == '%') {
                        state = State.PERCENT;
                    } else if (Character.isWhitespace(c)) {
                        skipWhitespace(reader);
                    } else {
                        checkChar(reader, c, i);
                    }
                    break;

                case PERCENT:
                    Object value = parse(reader, c);
                    if (value != null) {
                        result.add(value);
                    }
                    state = State.TEXT;
                    break;
            }
        }

        return result.toArray();
    }

    private static Object parse(BufferedReader reader, char fmt) throws IOException {
        try {
            switch (fmt) {
                case 'd':
                    return Integer.parseInt(readToken(reader));
                case 'f':
                    return Double.parseDouble(readToken(reader));
                case 's':
                    return readToken(reader);
                case 'c': {
                    int c = reader.read();
                    if (c == -1) {
                        throw new NoSuchElementException("Unexpected end for %c");
                    }
                    return (char) c;
                }
                case 'x' : {
                    return Integer.parseInt(readToken(reader), 16);
                }
                case 'b' : {
                    return Integer.parseInt(readToken(reader), 2);
                }
                case '%': {
                    int c = reader.read();
                    if (c != '%') {
                        throw new InputMismatchException("Expected '%'");
                    }
                    return null;
                }
                default:
                    throw new IllegalArgumentException("Unknown specifier %" + fmt);
            }
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Invalid token for %" + fmt);
        }
    }

    private static void checkChar(BufferedReader reader, char expected, int pos) throws IOException {
        int c = reader.read();
        if (c == -1) {
            throw new NoSuchElementException("Unexpected end at pos " + pos);
        }
        if ((char) c != expected) {
            throw new InputMismatchException(
                    "Expected '" + expected + "', got '" + (char) c + "'"
            );
        }
    }

    private static String readToken(BufferedReader reader) throws IOException {
        skipWhitespace(reader);

        StringBuilder sb = new StringBuilder();
        reader.mark(1);

        int c;
        while ((c = reader.read()) != -1 && !Character.isWhitespace(c)) {
            sb.append((char) c);
            reader.mark(1);
        }

        reader.reset();

        if (sb.isEmpty()) {
            throw new NoSuchElementException("No token");
        }

        return sb.toString();
    }

    private static void skipWhitespace(BufferedReader reader) throws IOException {
        reader.mark(1);

        int c;
        while ((c = reader.read()) != -1 && Character.isWhitespace(c)) {
            reader.mark(1);
        }

        reader.reset();
    }
}