package com.codecool.filepartreader;

import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileWordAnalyzer {
    private FilePartReader filePartReader;

    public FileWordAnalyzer(FilePartReader filePartReader) {
        this.filePartReader = filePartReader;
    }

    public List<String> getWordsOrderedAlphabetically() throws IOException {
        String[] wordsArray = filePartReader.readLines().split("[^a-zA-Z0-9]+");
        Arrays.sort(wordsArray, String.CASE_INSENSITIVE_ORDER);
        return Arrays.asList(wordsArray);
    }

    public List<String> getWordsContainingSubstring(String subString) throws IOException {
        String[] wordsArray = filePartReader.readLines().split("[^a-zA-Z0-9]+");
        List<String> wordsContainingSubstring = new ArrayList<>();
        for (String word : wordsArray) {
            if (word.toLowerCase().contains(subString.toLowerCase())) {
                wordsContainingSubstring.add(word);
            }
        }
        return wordsContainingSubstring;
    }

    public List<String> getStringsWhichPalindromes() throws IOException {
//        String[] wordsArray = filePartReader.readLines().split("\\s|\n");
        String[] wordsArray = filePartReader.readLines().split("[^a-zA-Z0-9]+");
        List<String> stringsWhichArePalindrome = new ArrayList<>();
        for (String word : wordsArray) {
            if (word.toLowerCase().equals(new StringBuilder(word.toLowerCase()).reverse().toString())) {
                stringsWhichArePalindrome.add(word);
            }
        }
        return stringsWhichArePalindrome;
    }
}
