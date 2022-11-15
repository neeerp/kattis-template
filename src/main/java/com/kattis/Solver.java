package com.kattis;

import java.io.*;
import java.util.*;

public class Solver {
    static final HashSet<String> GREETINGS = new HashSet<String>(List.of("Hello", "Greetings"));

    /*
     * PROBLEM DESCRIPTION
     * A greeting is either the word "Hello" or the word "Greetings".
     * A word is considered exciting when it is followed immediately by
     * an exclamation point.
     *
     * Given an input string consisting of two space separated strings,
     * a supposed greeting and a subject, echo the input iff the first
     * string was a greeting and the second was exciting.
     * Finally, finish by printing on a new line "That is all!".
     *
     */
    public void solve(InputReader in, PrintWriter out) {
        String greeting = in.next();
        String subject = in.next();

        sayHello(greeting, subject)
                .ifPresent(out::println);
        out.println("That is all!");
    }
    public Optional<String> sayHello(String greeting, String subject) {
        if (isGreeting(greeting) && isExciting(subject)) {
            return Optional.of(greeting + " " + subject);
        }

        return Optional.empty();
    }

    public boolean isGreeting(String s) {
        return Optional.ofNullable(s)
                .map(GREETINGS::contains)
                .orElse(false);
    }

    public boolean isExciting(String s) {
        return Optional.ofNullable(s)
                .map(t -> t.endsWith("!"))
                .orElse(false);
    }

    /*
     * Credits to Petr Mitrichev (https://codeforces.com/profile/Petr)
     * for the input/output template, which was lifted from one of his
     * Java 8 submissions (#63483344).
     **/
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);

        Solver solver = new Solver();
        solver.solve(in, out);
        out.close();
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}