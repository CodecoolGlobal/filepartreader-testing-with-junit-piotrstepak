package com.codecool.filepartreader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FilePartReaderTest {
    private FilePartReader filePartReader;
    private final String pathTestShort = "/home/pietia/CodeCool/web/si5/filepartreader-testing-with-junit-piotrstepak/src/main/java/com/codecool/resources/testShort.txt";
    private final String pathTestEmpty = "/home/pietia/CodeCool/web/si5/filepartreader-testing-with-junit-piotrstepak/src/main/java/com/codecool/resources/testEmpty.txt";
    private final String pathTest = "../resources/test.txt";
    private final String pathInvalid = "../resources/trelemorele";

    @BeforeEach
    void setUp() {
        filePartReader = new FilePartReader();
    }

    @Test
    public void testShouldThrowException_FromlineIsBiggerThanToline() {
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> filePartReader.setup(pathTest, 17, 15));
    }

    @Test
    public void testShouldThrowException_FromlineEqualsZero() {
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> filePartReader.setup(pathTest, 0, 15));
    }

    @Test
    public void testShouldThrowException_NegativeNumbers() { ;
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> filePartReader.setup(pathTest, -1, -2));
    }

    @Test
    public void testIsSetupAssignProperValues() {
        filePartReader.setup(pathTest,5,17);
        assertEquals(pathTest, filePartReader.getFilePath());
        assertEquals(5, filePartReader.getFromLine());
        assertEquals(17, filePartReader.getToLine());
    }

    @Test
    public void testShouldThrowIOException_FileDoesntExistInReadMethod() {
        filePartReader.setup(pathInvalid, 5, 17);
        Assertions.assertThrows(IOException.class, filePartReader::read);
    }

    @Test
    public void testShouldReturnString() throws IOException {
        filePartReader.setup(pathTestShort,1,1);
        String read = filePartReader.read();
        String expected = "first line\nSecond line of test file\nLast line\n";
        assertEquals(expected, read);
    }

    @Test
    public void testShouldReturnEmptyString_FileIsEmpty() throws IOException {
        filePartReader.setup(pathTestEmpty,8,17);
        String expected = "";
        assertEquals(expected, filePartReader.read());
    }

    @Test
    public void testShouldThrowIOException_FileDoesntExistInReadLinesMethod() {
        filePartReader.setup(pathInvalid, 5, 17);
        Assertions.assertThrows(IOException.class, filePartReader::readLines);
    }

    @Test
    public void testShouldReturnFirstLine() throws IOException {
        filePartReader.setup(pathTestShort,1,1);
        String line = filePartReader.readLines();
        String expected = "first line\n";
        assertEquals(expected, line);
    }

    @Test
    public void testShouldReturnSecondAndThirdLine() throws IOException {
        filePartReader.setup(pathTestShort,2,3);
        String lines = filePartReader.readLines();
        String expected = "Second line of test file\nLast line\n";
        assertEquals(expected, lines);
    }

    @Test
    public void testShouldReturnSecondAndThirdLine_TolineIsBiggerThanFileLength() throws IOException {
        filePartReader.setup(pathTestShort,2,100);
        String lines = filePartReader.readLines();
        String expected = "Second line of test file\nLast line\n";
        assertEquals(expected, lines);
    }
}