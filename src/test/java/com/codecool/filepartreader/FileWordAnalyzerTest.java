package com.codecool.filepartreader;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FileWordAnalyzerTest {
//    private FilePartReader filePartReader;
    private FileWordAnalyzer alphabeticallAnalyzer;
    private FileWordAnalyzer emptyAnalyzer;
    private FileWordAnalyzer palindromeAnalyzer;
//    private final String pathTestPalindrome = "/home/pietia/CodeCool/web/si5/filepartreader-testing-with-junit-piotrstepak/src/main/java/com/codecool/resources/testPalindrome.txt";
//    private final String pathTestAlphabetically = "/home/pietia/CodeCool/web/si5/filepartreader-testing-with-junit-piotrstepak/src/main/java/com/codecool/resources/testAlphabetically.txt";
//    private final String pathTestEmpty = "/home/pietia/CodeCool/web/si5/filepartreader-testing-with-junit-piotrstepak/src/main/java/com/codecool/resources/testEmpty.txt";

    @Mock
    private static FilePartReader mockAlphabeticallyReader;
    private static FilePartReader mockEmptyReader;
    private static FilePartReader mockPalindromeReader;

    @BeforeEach
    public void setUpMock() throws IOException {
        mockAlphabeticallyReader = Mockito.mock(FilePartReader.class);
        mockEmptyReader = Mockito.mock(FilePartReader.class);
        mockPalindromeReader = Mockito.mock(FilePartReader.class);
//        mockAlphabeticallyReader.setup(pathTestAlphabetically,1,100);
        String expectedReturnAlphabetically = "WomBAT\ncat\nape\nmonkey\n";
        String expectedReturnPalindrome = "OKO\nokO\nMaNnAM\nnothing\nANNa\n";
        when(mockAlphabeticallyReader.readLines()).thenReturn(expectedReturnAlphabetically);
        when(mockEmptyReader.readLines()).thenReturn(String.valueOf(Collections.EMPTY_LIST));
        when(mockPalindromeReader.readLines()).thenReturn(expectedReturnPalindrome);
        alphabeticallAnalyzer = new FileWordAnalyzer(mockAlphabeticallyReader);
        emptyAnalyzer = new FileWordAnalyzer(mockEmptyReader);
        palindromeAnalyzer = new FileWordAnalyzer(mockPalindromeReader);
    }

//    @Test
//    public void verifyMock() {
//        mockAlphabeticallyReader.setup(pathTestAlphabetically,1,100);
////        Mockito.verify(mockAlphabeticallyReader).setup(pathTestAlphabetically,1,100);
////        mockAlphabeticallyReader.getFilePath(),mockAlphabeticallyReader.getFromLine(),mockAlphabeticallyReader.getToLine();
//    }

//    @BeforeEach
//    void setUp() {
//        filePartReader = new FilePartReader();
//        fileWordAnalyzer = new FileWordAnalyzer(mockAlphabeticallyReader);
//    }

//    @Test
//    public void testShouldCreateMockInstance() {
//        assertTrue(mockAlphabeticallyReader,);
//    }

    @Test
    public void testAreWordsOrderedAlphabetically() throws IOException {
//        filePartReader.setup(pathTestAlphabetically,1,100);
        List<String> words = alphabeticallAnalyzer.getWordsOrderedAlphabetically();
        List<String> expected = Arrays.asList("ape", "cat" , "monkey", "WomBAT");
        assertEquals(expected, words);
    }

    @Test
    public void testShouldReturnEmptyList_EmptyFile() throws IOException {
//        filePartReader.setup(pathTestEmpty,1,100);
        List<String> words = emptyAnalyzer.getWordsOrderedAlphabetically();
        List<String> expected = Collections.emptyList();
        assertEquals(expected, words);
    }

    @Test
    public void testIsSubstringsAreFoundInWords() throws IOException {
//        filePartReader.setup(pathTestAlphabetically,1,100);
        List<String> words = alphabeticallAnalyzer.getWordsContainingSubstring("at");
        List<String> expected = Arrays.asList("WomBAT", "cat");
        assertEquals(expected, words);
    }

    @Test
    public void testShouldReturnEmptyList_InvalidSubstring() throws IOException {
//        filePartReader.setup(pathTestAlphabetically,1,100);
        List<String> words = alphabeticallAnalyzer.getWordsContainingSubstring("trelemorele");
        List<String> expected = Collections.emptyList();
        assertEquals(expected, words);
    }

    @Test
    public void testIsPalindrome() throws IOException {
//        filePartReader.setup(pathTestPalindrome,1,100);
        List<String> words = palindromeAnalyzer.getStringsWhichPalindromes();
        List<String> expected = Arrays.asList("OKO", "okO", "MaNnAM", "ANNa");
        assertEquals(expected, words);
    }

    @Test
    public void testShouldReturnEmptyList_AbsentPalindromeInFile() throws IOException {
//        filePartReader.setup(pathTestAlphabetically,1,100);
        List<String> words = alphabeticallAnalyzer.getStringsWhichPalindromes();
        List<String> expected = Collections.emptyList();
        assertEquals(expected, words);
    }
}