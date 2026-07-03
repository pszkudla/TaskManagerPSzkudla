package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



public class TasksFileHandler {

    private final static String SEPARATOR = ",";
    private final static String TASKS_FILE_NAME = "tasks.csv";
    private final static Path tasksFilePath = Paths.get(TASKS_FILE_NAME);

    static String[][] getTasksArray() {

        String[][] returnedArray = new String[1][];
        try {
            List<String> tasksFileLines = Files.readAllLines(tasksFilePath);
            System.out.println(tasksFileLines);
            int tasksNumber = tasksFileLines.size();
            String[][] tasksArray = new String[tasksNumber][];
            for (int i = 0; i < tasksNumber; i++) {
                String[] lineValues = tasksFileLines.get(i).split(SEPARATOR);
                tasksArray[i] = lineValues;
            }
            returnedArray = tasksArray;
        }
        catch (IOException e) {
            System.out.println("Error reading tasks.csv file");
            e.printStackTrace();
        }

        return returnedArray;
    }

    //this function performs all the necessary actions to add a task.
    public static void addTask() {
        Scanner addedTaskScanner = new Scanner(System.in);
        System.out.println("Enter task name and press enter: ");
        String taskName = addedTaskScanner.nextLine();

        System.out.println("Enter task due date in a YYYY-MM-DD format and press enter: ");
        String taskDueDate;
        while (true) {
            taskDueDate = addedTaskScanner.nextLine();
            if (taskDueDate.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")) {
                break;
            }
            else {
                System.out.println("Invalid task due date. Enter the date in the YYYY-MM-DD format.");
            }
        }


        System.out.println("Enter task importance (true/false) and press enter:");
        String taskImportance = addedTaskScanner.nextLine();

        String[] taskRow = new String[] {taskName, taskDueDate, taskImportance};

        String[][] oldTasksArray = getTasksArray();
        String[][] newTasksArray = getAppendedTasksArray(oldTasksArray, taskRow);
        writeTasksArrayToCsv(newTasksArray, TASKS_FILE_NAME);
    }

    public static void removeTask() {
        Scanner removedTaskScanner = new Scanner(System.in);
        String[][] oldTasksArray = getTasksArray();

        System.out.println(ConsoleColors.RED + "Write number of the task to be removed" + ConsoleColors.RESET);

        Printer.printTasksList();
        int removedTaskIndex;
        while (true) {
            String removedTaskIndexInput = removedTaskScanner.nextLine();
            try {
                removedTaskIndex = Integer.parseInt(removedTaskIndexInput);

                if (removedTaskIndex >= 0 && removedTaskIndex < oldTasksArray.length) {
                    break;
                }
                else {
                    System.out.println(ConsoleColors.RED + "Index out of range. Write a correct index." + ConsoleColors.RESET);
                }
            }
            catch (NumberFormatException e) {
                System.out.println(ConsoleColors.RED_BOLD + "The value you provided is not int. Please provide a correct value." + ConsoleColors.RESET);
            }
        }

        String[][] shortenedTasksList = getShortenedTasksList(oldTasksArray, removedTaskIndex);

        writeTasksArrayToCsv(shortenedTasksList, TASKS_FILE_NAME);
    }

    private static String[][] getShortenedTasksList(String[][] oldTasksArray, int removedRowIndex) {
        int returnedArrayLength = oldTasksArray.length - 1;
        String[][] newTasksArray = new String[returnedArrayLength][];

        int newArrayIndex = 0;
        for (int oldArrayIndex = 0; oldArrayIndex < oldTasksArray.length; oldArrayIndex++) {
            if (oldArrayIndex == removedRowIndex) {
                continue;
            }
            else {
                newTasksArray[newArrayIndex] = oldTasksArray[oldArrayIndex];
                newArrayIndex++;
            }
        }
        return newTasksArray;
    }


    private static String[][] getAppendedTasksArray(String[][] oldTasksArray, String[] newTaksRow) {
        String[][] returnedArray = Arrays.copyOf(oldTasksArray, oldTasksArray.length + 1);
        returnedArray[oldTasksArray.length] = newTaksRow;
        return returnedArray;
    }

    private static void writeTasksArrayToCsv(String[][] tasksArray, String filePath) {
        StringBuilder csvString = new StringBuilder();
        for (int i = 0; i < tasksArray.length; i++) {
            String lineString = String.join(SEPARATOR, tasksArray[i]) + System.lineSeparator();
            csvString.append(lineString);
        }

        try (Writer fileWriter = new FileWriter(filePath, false)) {
            fileWriter.write(csvString.toString());
        }
        catch (IOException e) {
            System.out.println("Error writing tasks.csv file");
            e.printStackTrace();
        }

    }
}
