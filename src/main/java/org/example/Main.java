package org.example;

import java.util.Scanner;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String input = "";
        while (true) {

            if (input.equals("exit")) {
                break;
            }
            System.out.println(ConsoleColors.BLUE + "Please select an option:" + ConsoleColors.RESET);
            Printer.printOptions();
            input = inputScanner.nextLine();
            switch (input) {
                case "add":
                    TasksFileHandler.addTask();
                    break;
                case "remove":
                    TasksFileHandler.removeTask();
                    break;
                case "list":
                    Printer.printTasksList();
                    break;
                case "exit":
                    System.out.println(ConsoleColors.RED_BOLD + "Bye, bye!");
                    break;
                default:
                    System.out.println(ConsoleColors.RED + "Unknown option! Please select one of the options listed below:" + ConsoleColors.RESET);
            }
        }

    }
}