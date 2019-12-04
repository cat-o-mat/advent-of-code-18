package com.cat.o.mat.day_two;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Task_II {

    private List<String> inputIds = new ArrayList<>();

    public void countOccurns() {
        readInput();

        Long result = inputIds.stream()
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
        System.out.println("Correct answer: 6175");
    }


    public void findId() {
        readInput();

        int diff = 0;
        String id_1;
        String id_2;

        // iterating over strings
        for (int thisWord = 0; thisWord < inputIds.size(); thisWord++) {
            for (int otherWord = thisWord + 1; otherWord < inputIds.size(); otherWord++) {

                // counting different chars in two words
                for (int idx = 0; idx < inputIds.get(thisWord).length() && diff <= 1; idx++) {
                    if (inputIds.get(thisWord).charAt(idx) != inputIds.get(otherWord).charAt(idx)) {
                        diff++;
                    }
                }

                if (diff == 1) {
                    id_1 = inputIds.get(thisWord);
                    id_2 = inputIds.get(otherWord);

                    for (int idx = 0; idx < id_1.length(); ++idx) {
                        if (id_1.charAt(idx) != id_2.charAt(idx)) {

                            String fstHalf = id_1.substring(0, idx);
                            String sndHalf = id_1.substring(idx + 1);
                            String result = fstHalf.concat(sndHalf);

                            System.out.println(result);
                            System.out.println("Correct answer: asgwjcmzredihqoutcylvzinx");
                            return;
                        }
                    }

                    return;
                }

                diff = 0;
            }
        }
    }


    private void readInput() {
        try {
            inputIds = Files.readAllLines(Paths.get("src/com/cat/o/mat/day_two/input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
