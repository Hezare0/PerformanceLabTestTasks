package org.testTask;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class Task2 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("В качестве аргументов укажите путь к файлам с параметрами окружности и координатами точек");
            return;
        }
        List<Point> points = new ArrayList<Point>();
        int circleX = 0;
        int circleY = 0;
        int circleR = 0;
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            circleX = scanner.nextInt();
            circleY = scanner.nextInt();
            circleR = scanner.nextInt();
        } catch (FileNotFoundException e) {
            System.out.println("Не удается найти указанный файл");
        }
        try (Scanner scanner = new Scanner(new File(args[1]))) {
            while (scanner.hasNextInt()) {
                points.add(new Point(scanner.nextInt(), scanner.nextInt()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Не удается найти указанный файл");
        }
        for (Point dot: points) {
            int dotX = dot.x;
            int dotY = dot.y;
            double distance = Math.sqrt(Math.pow(dotX - circleX, 2) + Math.pow(dotY - circleY, 2));
            if (distance > circleR)
                System.out.println(2);
            if (distance < circleR)
                System.out.println(1);
            if (distance == circleR)
                System.out.println(0);
        }
    }
}

/*
координаты центра окружности (x₀, y₀)
координаты точки (x₁, y₁)
d = √((x₁ - x₀)² + (y₁ - y₀)²)
d = √((5 - 2)² + (7 - 3)²) = √(3² + 4²) = √25 = 5
*/