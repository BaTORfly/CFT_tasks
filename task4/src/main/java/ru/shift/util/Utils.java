package ru.shift.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Utils {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Logger log = LogManager.getLogger(Utils.class);

    public static long getPositiveLong() {
        long N;
        while (true) {
            System.out.println("Enter the number of row members (N, must be a positive integer): ");
            if (scanner.hasNextLong()) {
                N = scanner.nextLong();
                if (N > 0) {
                    log.info("User entered a valid value for N: {}", N);
                    return N;
                } else {
                    log.warn("User entered a negative value for N: {}", N);
                    System.out.println("N must be greater than 0. Please try again.");
                }
            } else {
                log.error("Invalid input detected. User did not enter a valid integer.");
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next();
            }
        }
    }
}
