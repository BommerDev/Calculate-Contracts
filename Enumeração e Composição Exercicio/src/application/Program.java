package application;

import java.text.ParseException;
import java.util.Scanner;
import entities.WorkerOperations;

public class Program {

    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        WorkerOperations workerOperations = new WorkerOperations(sc);
        workerOperations.run();
        sc.close();
    }
}