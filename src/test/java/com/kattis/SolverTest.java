package com.kattis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {
    private static final String EXPECTED_STRING = "Hello world!";

    private Solver solver;

    @BeforeEach
    void setUp() {
        solver = new Solver();
    }

    @Test
    void testSolve() {
        String actual = solver.solve();

        assertEquals(EXPECTED_STRING, actual);
    }
}