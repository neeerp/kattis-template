package com.kattis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {
    private static final String EXPECTED_STRING = "Hello world!";

    private Solver solver;

    @BeforeEach
    void setUp() {
        solver = new Solver();
    }

    @Test
    void testSimple() throws IOException {
        File inputFile = new File("src/test/resources/testSimple.input");
        InputStream inputStream = new FileInputStream(inputFile);
        Solver.InputReader in = new Solver.InputReader(inputStream);

        FileOutputStream fos = new FileOutputStream("src/test/resources/testSimple.output");
        PrintWriter out = new PrintWriter(fos);

        solver.solve(in, out);
        out.close();

        Path expected = Path.of("src/test/resources/testSimple.expected");
        Path actual = Path.of("src/test/resources/testSimple.output");
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