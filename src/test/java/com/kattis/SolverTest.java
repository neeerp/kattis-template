package com.kattis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {
    private static final String TEST_RESOURCE_PATH_PREFIX = "src/test/resources/";
    private static final String INPUT_SUFFIX = ".input";
    private static final String EXPECTED_SUFFIX = ".expected";
    private static final String OUTPUT_SUFFIX = ".output";

    private Solver solver;

    @BeforeEach
    void setUp() {
        solver = new Solver();
    }

    @Test
    void testSimple() throws IOException {
        runIOTest("testSimple");
    }

    @Test
    void testGoodbye() throws IOException {
        runIOTest("testGoodbye");
    }

    void runIOTest(String testName) throws IOException {
        String inputPath = TEST_RESOURCE_PATH_PREFIX + testName + INPUT_SUFFIX;
        String outputPath = TEST_RESOURCE_PATH_PREFIX + testName + OUTPUT_SUFFIX;
        String expectedPath = TEST_RESOURCE_PATH_PREFIX + testName + EXPECTED_SUFFIX;

        File inputFile = new File(inputPath);
        InputStream inputStream = new FileInputStream(inputFile);
        Solver.InputReader in = new Solver.InputReader(inputStream);

        FileOutputStream fos = new FileOutputStream(outputPath);
        PrintWriter out = new PrintWriter(fos);

        solver.solve(in, out);
        out.close();

        Path expected = Path.of(expectedPath);
        Path actual = Path.of(outputPath);
        assertArrayEquals(Files.readAllBytes(expected), Files.readAllBytes(actual));
    }

    @Test
    void testIsGreeting() {
        for (String s : Solver.GREETINGS) {
            assertTrue(solver.isGreeting(s));
        }
    }
    @Test
    void testIsNotGreeting() {
        assertFalse(solver.isGreeting("Goodbye"));
    }

    @Test
    void testIsExciting() {
        assertTrue(solver.isExciting("World!"));
    }
    @Test
    void testIsNotExciting() {
        assertFalse(solver.isExciting("World"));
    }

    @Test
    void testSayHello() {
        String greeting = "Hello";
        String subject = "World!";
        String expected = "Hello World!";

        Optional<String> maybeResult = solver.sayHello(greeting, subject);
        assertTrue(maybeResult.isPresent());
        assertEquals(expected, maybeResult.get());
    }

    @Test
    void testSayNothingBecauseLackingEnthusiasm() {
        String greeting = "Hello";
        String subject = "World";

        Optional<String> maybeResult = solver.sayHello(greeting, subject);
        assertTrue(maybeResult.isEmpty());
    }

    @Test
    void testSayNothingBecauseNoGreeting() {
        String greeting = "Goodbye";
        String subject = "World!";

        Optional<String> maybeResult = solver.sayHello(greeting, subject);
        assertTrue(maybeResult.isEmpty());
    }
}