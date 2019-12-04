package com.cat.o.mat.day_two;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Task_II {

    private List<String> inputIds = new ArrayList<>();

    public void countOccurns() {
        readInput();

        Long result = inputIds.stream()
                // filter out all without duplicate chars
                .filter(Task_II::hasDuplicateChars)
                // count chars in strings
                .map(s -> s.chars()
                        .boxed()
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())))
                // filter out the ones that are not duplicated 2 or 3 times; char -> num_of_duplicates
                .flatMap(hm -> hm.values().stream()
                        .filter(v -> v == 2 || v == 3)
                        // a string can only supply once with the same dup
                        .distinct())
                // group the resulting stream; 2/3_times_dup -> numbers_of_dup
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                // multiply the occurrences to get result
                .values().stream()
                .reduce(Math::multiplyExact)
                .orElse(0L);

        System.out.println(result);
    }

    private void readInput() {
        try {
            inputIds = Files.readAllLines(Paths.get("src/com/cat/o/mat/day_two/input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean hasDuplicateChars(String s) {
        Set<Character> chars = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (!chars.add(c)) return true;
        }
        return false;
    }

}
