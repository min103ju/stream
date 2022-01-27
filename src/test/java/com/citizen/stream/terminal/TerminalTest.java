package com.citizen.stream.terminal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Consumer;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

public class TerminalTest {

    @Test
    void forEach() {
        List<String> players = List.of("jordan", "kobe", "iverson", "wade", "curry");
        players.stream()
            .forEach(System.out::println);
    }

    @Test
    void count() {
        List<String> players = List.of("jordan", "kobe", "iverson", "wade", "curry");
        long count = players.stream()
            .count();

        System.out.println("count = " + count);
    }

    @Test
    void filter_count() {
        List<String> players = List.of("jordan", "kobe", "iverson", "wade", "curry");
        long count = players.stream()
            .filter(name -> name.length() > 4)
            .count();

        System.out.println("count = " + count);
    }

    @Test
    void findFirst() {
        List<String> players = List.of("jordan", "kobe", "iverson", "wade", "curry");
        Optional<String> first = players.stream()
            .filter(name -> name.length() < 4)
            .findFirst();

        System.out.println("first = " + first);
    }

    @Test
    void findAny() {
        List<String> players = List.of("jordan", "kobe", "iverson", "wade", "curry");
        Optional<String> any = players.stream()
            .filter(name -> name.length() > 4)
            .findAny();

        System.out.println("any = " + any);
    }

    @Test
    void differentFind() {
        Runnable findFirst = new Runnable() {
            @Override
            public void run() {
                List<String> findFirstStream = List.of("jordan", "kobe", "iverson", "wade", "curry");
                Optional<String> first = findFirstStream.stream()
                    .parallel()
                    .filter(name -> name.length() > 4)
                    .findFirst();

                System.out.println("first = " + first);
            }
        };

        Runnable findAny = new Runnable() {
            @Override
            public void run() {
                List<String> findAnyStream = List.of("jordan", "kobe", "iverson", "wade", "curry");
                Optional<String> any = findAnyStream.stream()
                    .parallel()
                    .filter(name -> name.length() > 4)
                    .findAny();

                System.out.println("any = " + any);
            }
        };

        for (int i = 0; i < 100; i++) {
            findFirst.run();
            findAny.run();
        }

    }

    @Test
    void allMatch() {
        List<String> players = List.of("jordan", "kobe", "iverson", "wade", "curry");
        boolean isMatched = players.stream()
            .allMatch(name -> name.length() > 3);

        System.out.println("isMatched = " + isMatched);
    }

    @Test
    void anyMatch() {
        List<String> players = List.of("jordan", "kobe", "iverson", "wade", "curry");
        boolean isMatched = players.stream()
            .anyMatch(name -> name.length() > 7);

        System.out.println("isMatched = " + isMatched);
    }

    @Test
    void noneMatch() {
        List<String> players = List.of("jordan", "kobe", "iverson", "wade", "curry");
        boolean isMatched = players.stream()
            .noneMatch(name -> name.length() > 7);

        System.out.println("isMatched = " + isMatched);
    }

    @Test
    void reduce() {
        List<String> players = List.of("jordan", "kobe", "iverson", "wade", "curry");
        OptionalInt reduce = players.stream()
            .parallel()
            .mapToInt(name -> name.length())
            .reduce((a, b) -> a + b);

        System.out.println("length = " + reduce.getAsInt());
    }

    @Test
    void parallel_reduce() {
        Integer result = Arrays.asList(1, 2, 3, 4, 5)
            .parallelStream()
            .reduce(10,
                Integer::sum,
                (a, b) -> {
                    int i = a + b;
                    System.out.println("a = " + a);
                    System.out.println("b = " + b);
                    System.out.println("combiner 호츌 : " + i);
                    return i;
                });

        System.out.println("result = " + result);
    }

    @Test
    void collect_toList() {
        List<String> players = List.of("jordan", "kobe", "iverson", "wade", "curry");
        List<String> collect = players.stream()
            .collect(Collectors.toList());

        System.out.println("collect = " + collect);
    }

    @Test
    void collect_joining() {
        List<String> players = List.of("jordan", "kobe", "iverson", "wade", "curry");
        String collect = players.stream()
            .collect(Collectors.joining(" : "));

        System.out.println("collect = " + collect);
    }

}
