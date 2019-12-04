package com.cat.o.mat.day_one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task_I {

    private List<String> inputFreq = new ArrayList<>();

    public void findFreq() {
        readInput();
        int sum = inputFreq.stream()
                .mapToInt(Integer::parseInt)
                .sum();

        System.out.println("Resulting frequency after all of the changes in frequency have been applied: " + sum);
    }

    public void findRepeatingFreq() {
        int sum = 0;
        Set<Integer> foundFreq = new HashSet<>();

        readInput();

        final int[] ints = inputFreq.stream()
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int idx = 0; idx < inputFreq.size(); idx++) {
            if (!foundFreq.add(sum)) {
                System.out.println("First repeating frequency: " + sum);
                return;
            }
            sum += ints[idx];
            if (idx == inputFreq.size() - 1) {
                idx = -1;
            }
        }
    }


    private void readInput() {
        try {
            inputFreq = Files.readAllLines(Paths.get("src/com/cat/o/mat/day_one/input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
