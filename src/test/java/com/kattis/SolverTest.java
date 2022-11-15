package com.kattis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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