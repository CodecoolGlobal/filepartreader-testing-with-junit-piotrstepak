package com.codecool.filepartreader;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FilePartReader {
    private String filePath;
    private int fromLine;
    private int toLine;

//    invalid default values
    public FilePartReader() {
        this.filePath = "../resources/test.txt";
        this.fromLine = 17;
        this.toLine = -3;
    }

    public void setup(String filePath, int fromLine, int toLine) {
        if (toLine < fromLine || fromLine < 1) throw new IllegalArgumentException();
        this.filePath = filePath;
        this.fromLine = fromLine;
        this.toLine = toLine;
    }

    public String read() throws IOException {
//        Path path = Paths.get(filePath);
        File path = new File(this.filePath);
//        Scanner scanner = new Scanner(Files.newBufferedReader(path));
        Scanner scanner = new Scanner(path);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine())
                         .append("\n");
        }
        return stringBuilder.toString();
    }

    public String readLines() throws IOException {
        String[] fileContent = read().split("\n");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < fileContent.length; i++) {
            if (i + 1 >= fromLine && i + 1 <= toLine) {
                stringBuilder.append(fileContent[i])
                             .append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public String getFilePath() {
        return filePath;
    }

    public int getFromLine() {
        return fromLine;
    }

    public int getToLine() {
        return toLine;
    }
}
