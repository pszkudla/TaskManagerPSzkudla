package org.example;

public class Printer {
    public static void printOptions() {
        String[] programOptions = new String[] {"add", "remove", "list", "exit"};
        for (String option : programOptions) {
            System.out.println(option);
        }
    }

    public static void printTasksList() {
        String[][] taksList = TasksFileHandler.getTasksArray();
        for (int i = 0; i < taksList.length; i++) {
            String[] taskArray = taksList[i];
            String lineToPrint = "%s\t:\t%s\t%s\t%s".formatted(i, taskArray[0], taskArray[1], taskArray[2]);
            System.out.println(ConsoleColors.PURPLE_BOLD + lineToPrint + ConsoleColors.RESET);
        }
    }
}
