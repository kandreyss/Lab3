package org.suai.lab06.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;

public class FormattedInput {

    private enum State {
        TEXT, PERCENT
    }

    public static Object[] scanf(String format) throws IOException {
        return internalScanf(new BufferedReader(new InputStreamReader(System.in)), format);
    }

    public static Object[] sscanf(String in, String format) throws IOException {
        return internalScanf(new BufferedReader(new StringReader(in)), format);
    }

    private static Object[] internalScanf(BufferedReader reader, String format) throws IOException {
        List<Object> result = new ArrayList<>();
        State state = State.TEXT;

        for (int i = 0; i < format.length(); i++) {
            char formatChar = format.charAt(i);

            if (state == State.TEXT) {
                if (formatChar == '%') {
                    state = State.PERCENT;
                } else if (Character.isWhitespace(formatChar)) {
                    skipWhitespace(reader);
                } else {
                    int inputChar = reader.read();
                    if (inputChar == -1) {
                        throw new NoSuchElementException(
                            "Неожиданный конец ввода. Ожидалось: '" + formatChar + "'" + " на позиции: " + i
                        );
                    }
                    if ((char) inputChar != formatChar) {
                        throw new InputMismatchException(
                            "Ожидалось: '" + formatChar + "', получено: '" + (char) inputChar + "'" + "'" + " на позиции: " + i
                        );
                    }
                }
            } else {
                String token = "";
                try {
                    switch (formatChar) {
                        case 'd' -> {
                            token = readToken(reader);
                            result.add(Integer.parseInt(token));
                        }
                        case 'f' -> {
                            token = readToken(reader);
                            result.add(Double.parseDouble(token));
                        }
                        case 's' -> result.add(readToken(reader));
                        case 'c' -> {
                            int c = reader.read();
                            if (c == -1) throw new NoSuchElementException("Неожиданный конец ввода при чтении %c");
                            result.add((char) c);
                        }
                        case '%' -> {
                            int p = reader.read();
                            if (p != '%') throw new InputMismatchException(
                                "Ожидался символ '%', получено: '" + (char) p + "'"
                            );
                        }
                        default -> throw new IllegalArgumentException(
                            "Неизвестный спецификатор формата: %" + formatChar
                        );
                    }
                } catch (NumberFormatException e) {
                    throw new InputMismatchException(
                        "Не удалось преобразовать '" + token + "' для спецификатора %" + formatChar
                    );
                }
                state = State.TEXT;
            }
        }

        return result.toArray();
    }

    private static String readToken(BufferedReader reader) throws IOException {
        skipWhitespace(reader);
        StringBuilder sb = new StringBuilder();

        while (true) {
            reader.mark(1);
            int c = reader.read();
            if (c == -1 || Character.isWhitespace((char) c)) {
                if (c != -1) reader.reset();
                break;
            }
            sb.append((char) c);
        }

        if (sb.isEmpty()) {
            throw new NoSuchElementException("Не найдено данных для чтения токена.");
        }

        return sb.toString();
    }

    private static void skipWhitespace(BufferedReader reader) throws IOException {
        while (true) {
            reader.mark(1);
            int c = reader.read();
            if (c == -1 || !Character.isWhitespace((char) c)) {
                if (c != -1) reader.reset();
                break;
            }
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("Введите строку и число (например: hello 42):");
            Object[] elements = FormattedInput.scanf("%s %d");
            System.out.println("Прочитано: " + elements[0] + " и " + elements[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}