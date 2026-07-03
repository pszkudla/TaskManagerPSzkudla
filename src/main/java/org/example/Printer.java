package org.example;

public class Printer {
    public static void printOptions() {
        String[] programOptions = new String[] {"add", "remove", "list", "exit"};
        for (String option : programOptions) {
            System.out.println(option);
        }
    }
}
