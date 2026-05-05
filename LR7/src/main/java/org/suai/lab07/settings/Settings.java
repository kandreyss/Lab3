package org.suai.lab07.settings;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Settings {

    private final HashMap<String, Integer> map;
    
    public Settings() {
        this.map = new HashMap<>();
    }

    public Integer put(String name, int value) {
        if (value < -128 ||  value > 127) {
            throw new IllegalArgumentException("Value must be one byte!");
        }
        return map.put(name, value);
    }

    public Integer get(String name) {
        return map.get(name);
    }

    public Integer delete(String name) {
        return map.remove(name);
    }

    public int loadFromBinaryFile(String filename) throws IOException {
        int counter = 0;
        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
            while (dis.available() > 0) {
                map.put(dis.readUTF(), (int) dis.read());
                counter++;
            }
        }
        return counter;
    }

    public int saveToBinaryFile(String filename) throws IOException {
        int counter = 0;
        try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                dos.writeUTF(entry.getKey());
                dos.write(entry.getValue());
                counter++;
            }
        }
        return counter;
    }

    public int loadFromTextFile(String filename) throws IOException {
        int counter = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 2) {
                    map.put(parts[0], Integer.parseInt(parts[1]));
                    counter++;
                }
            }
        }
        return counter;
    }

    public int saveToTextFile(String filename) throws IOException {
        int counter = 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue());
                writer.newLine();
                counter++;
            }
        }
        return counter;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {

        if (o == null) {
            return false;
        }

        if (o == this) {
            return true;
        }

        if (!(o instanceof Settings)) {
            return false;
        }

        Settings other = (Settings) o;

        return map.equals(other.map);
    }
}