package org.testTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("В качестве аргумента укажите путь к файлу с згачениями массива");
            return;
        }
        List<Integer> inputArray = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(args[0]))) {
           while (scanner.hasNextInt()) {
               inputArray.add(scanner.nextInt());
           }
        } catch (FileNotFoundException e) {
            System.out.println("Не удается найти указанный файл");
        }

        Collections.sort(inputArray);
        int median = 0;
        if (inputArray.size() % 2 == 0) {
            median = (inputArray.get(inputArray.size() / 2 - 1) + inputArray.get(inputArray.size() / 2)) / 2;
        }else
            median = inputArray.get(inputArray.size() / 2);

        int moves = 0;
        for (int num : inputArray) {
            moves += Math.abs(num - median);
        }
        System.out.println(moves);
    }
}
