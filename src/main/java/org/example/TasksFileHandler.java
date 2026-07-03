package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TasksFileHandler {
    static String[] getTasksArray() {
        Path tasksFilePath = Paths.get("tasks.csv");
        try {
            List<String> lines = Files.readAllLines(tasksFilePath);
            System.out.println(lines.toString());
        }
        catch (IOException e) {
            System.out.println("Error reading tasks.csv file");
            e.printStackTrace();
        }
        //tymczasowo zwracam pustą tablicę, wyłącznie po to, aby przetestować funkcję getTasksArray.
        return new String[] {};


    }
}
