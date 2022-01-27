package com.citizen.stream.intermediate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.namespace.QName;
import org.junit.jupiter.api.Test;

public class IntermediateTest {

    @Test
    void filter() {
        List<String> players = List.of("jordan", "kobe", "iverson", "wade", "curry");
        players.stream()
            .filter(name -> name.length() >= 5)
            .forEach(System.out::println);
    }

    @Test
    void map1() {
        List<String> players = List.of("jordan", "kobe", "iverson", "wade", "curry");
        players.stream()
            .map(name -> name.length())
            .forEach(System.out::println);
    }

    @Test
    void map2() {
        List<String> players = List.of("jordan", "kobe", "iverson", "wade", "curry");
        players.stream()
            .map(name -> name + name.length())
            .forEach(System.out::println);
    }

    @Test
    void flatMap() {

        List<List<String>> allPlayers = new ArrayList<>();
        List<String> players1 = List.of("jordan", "kobe");
        List<String> players2 = List.of("iverson", "wade");
        List<String> players3 = List.of("curry", "lebron");

        allPlayers.add(players1);
        allPlayers.add(players2);
        allPlayers.add(players3);

        List<String> collect = allPlayers.stream()
            .flatMap(players -> players.stream())
            .collect(Collectors.toList());

        collect.stream()
            .forEach(System.out::println);

    }

    @Test
    void peek() {
        List<String> players = List.of("jordan", "kobe", "iverson", "wade", "curry");
        long count = players.stream()
            .filter(name -> name.length() >= 5)
            .peek(System.out::println)
            .count();

        System.out.println("count = " + count);
    }

    @Test
    void string_sorted() {
        List<String> players = List.of("jordan", "kobe", "iverson", "wade", "curry");
        players.stream().sorted()
            .forEach(System.out::println);
    }

    @Test
    void integer_sorted() {
        List<Integer> numbers = List.of(4, 5, 1, 3, 2);
        numbers.stream().sorted()
            .forEach(System.out::println);
    }

    @Test
    void reverse_sorted() {
        List<String> players = List.of("jordan", "kobe", "iverson", "wade", "curry");
        players.stream()
            .sorted(Comparator.reverseOrder())
            .forEach(System.out::println);
    }

    @Test
    void distinct() {
        List<String> players = List.of("jordan", "kobe", "iverson", "wade", "curry", "jordan", "kobe", "iverson", "wade", "curry");
        players.stream()
            .distinct()
            .forEach(System.out::println);
    }

    @Test
    void limit() {
        List<String> players = List.of("jordan", "kobe", "iverson", "wade", "curry");
        players.stream()
            .limit(3)
            .forEach(System.out::println);
    }

    @Test
    void skip() {
        List<String> players = List.of("jordan", "kobe", "iverson", "wade", "curry");
        players.stream()
            .skip(3)
            .forEach(System.out::println);
    }


}
