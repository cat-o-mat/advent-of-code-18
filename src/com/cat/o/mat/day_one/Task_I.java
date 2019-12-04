package com.cat.o.mat.day_one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task_I {

    private static final int SIGN_IDX = 0;

    private List<String> inputFreq = new ArrayList<>();

    public void findFreq() {
        int sum = 0;
        try {
            inputFreq = Files.readAllLines(Paths.get("src/com/cat/o/mat/day_one/input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!inputFreq.isEmpty()) {
            for (String s : inputFreq) {
                char sign = s.charAt(SIGN_IDX);
                switch (sign) {
                    case '+':
                        sum += Integer.parseInt(s.substring(SIGN_IDX + 1));
                        break;
                    case '-':
                        sum -= Integer.parseInt(s.substring(SIGN_IDX + 1));
                        break;
                    default:
                        throw new RuntimeException("Not a valid operation. " + sign);
                }
            }
        }

        System.out.println("Resulting frequency after all of the changes in frequency have been applied: " + sum);
    }

    public void findRepeatingFreq() {
        int sum = 0;
        List<Integer> foundFreq = new ArrayList<>();
        try {
            inputFreq = Files.readAllLines(Paths.get("src/com/cat/o/mat/day_one/input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!inputFreq.isEmpty()) {
            foundFreq.add(0);
            for (int idx = 0; idx < inputFreq.size(); idx++) {
                char sign = inputFreq.get(idx).charAt(SIGN_IDX);
                switch (sign) {
                    case '+':
                        sum += Integer.parseInt(inputFreq.get(idx).substring(SIGN_IDX + 1));
                        if (!foundFreq.contains(sum)) {
                            foundFreq.add(sum);
                        } else {
                            System.out.println("First repeating frequency: " + sum);
                            return;
                        }
                        break;
                    case '-':
                        sum -= Integer.parseInt(inputFreq.get(idx).substring(SIGN_IDX + 1));
                        if (!foundFreq.contains(sum)) {
                            foundFreq.add(sum);
                        } else {
                            System.out.println("First repeating frequency: " + sum);
                            return;
                        }
                        break;
                    default:
                        throw new RuntimeException("Not a valid operation. " + sign);
                }
                if (idx == inputFreq.size() - 1) {
                    idx = -1;
                }
            }
        }
    }

}
