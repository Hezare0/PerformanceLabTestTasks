package org.testTask;


public class Task1 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("В качестве аргументов укажите длину массива и размер интервала");
            return;
        }
        try {
            int length = Integer.parseInt(args[0]);
            int step = Integer.parseInt(args[1]);
            StringBuilder path = new StringBuilder("1");
            int current = 1;
            if (step > length) {
                current = (1 + step - 1) % length;
            } else {
                current = (1 + step - 1);
            }

            while (current != 1) {
                path.append(current);
                current = (current + step - 1) % length;
                if (current == 0) {
                    current = length;
                }
            }
            System.out.println(path.toString());
        } catch (NumberFormatException e) {
            System.out.println("Не корректные входные аргументы. Пример: 5 4");
        }

    }
}