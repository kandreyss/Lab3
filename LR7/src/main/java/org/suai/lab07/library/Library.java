package org.suai.lab07.library;

import java.io.*;
import java.util.*;

public class Library {

    private final TreeSet<Book> storage;

    public Library() {
        storage = new TreeSet<>(Comparator.comparingInt(Book::getYear)
                .thenComparing(Book::getTitle));
    }

    public void add(Book book) {
        storage.add(book);
    }

    public Book findByTitle(String title) {
        for (Book book : storage) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public Set<Book> findNewerThan(int year) {
        Book from = new Book("", "", year + 1);
        Book to = new Book("", "", Integer.MAX_VALUE);

        return storage.subSet(from, true, to, true);
    }

    public void loadFromFile(String filename) {

        storage.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            String line;
            line = reader.readLine();

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                if (parts.length == 3) {
                    String title = parts[0].trim();
                    String author = parts[1].trim();
                    int year = Integer.parseInt(parts[2].trim());

                    add(new Book(title, author, year));
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveToFile(String filename) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {

            writer.write("title,author,year");
            writer.newLine();

            for (Book book : storage) {
                writer.write(book.toString());
                writer.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}