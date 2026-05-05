package org.suai.lab07;

import org.suai.lab07.library.Library;
import org.suai.lab07.library.Book;

import java.util.Set;

public class LibraryTest {

    static void main() {

        Library library = new Library();

        library.loadFromFile("books.csv");

        System.out.println("Books newer than 1900:");
        Set<Book> newerBooks = library.findNewerThan(1900);

        for (Book b : newerBooks) {
            System.out.println(b);
        }

        library.add(new Book("Test Book", "Me", 12));

        System.out.println("\nAfter adding (newer than 1900):");
        Set<Book> updated = library.findNewerThan(1900);

        for (Book b : updated) {
            System.out.println(b);
        }

        library.saveToFile("books_out.csv");

        System.out.println("\nSaved to books_out.csv");
    }
}