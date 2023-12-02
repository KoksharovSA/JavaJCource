package org.example.hw1;


import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println(Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .filter(x -> x % 2 == 0)
                .mapToDouble(y -> y)
                .average());
    }
}
