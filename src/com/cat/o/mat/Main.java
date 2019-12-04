package com.cat.o.mat;

import com.cat.o.mat.day_one.Task_I;
import com.cat.o.mat.day_two.Task_II;

public class Main {

    public static void main(String[] args) {
        Task_I day_one = new Task_I();
        day_one.findFreq();
        day_one.findRepeatingFreq();

        Task_II day_two = new Task_II();
        day_two.countOccurns();
    }
}
