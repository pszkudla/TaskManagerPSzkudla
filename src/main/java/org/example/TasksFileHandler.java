package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class TasksFileHandler {

    private final static String SEPARATOR = ",";


    static String[][] getTasksArray() {
        Path tasksFilePath = Paths.get("tasks.csv");
        String[][] returnedArray = new String[1][];
        try {
            List<String> tasksFileLines = Files.readAllLines(tasksFilePath);
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

        System.out.println("Returned array: " + Arrays.deepToString(returnedArray));
        //tymczasowo zwracam pustą tablicę, wyłącznie po to, aby przetestować funkcję getTasksArray.

        return returnedArray;


    }
}
