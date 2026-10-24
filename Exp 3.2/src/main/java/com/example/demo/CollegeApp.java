
package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CollegeApp implements CommandLineRunner {

    @Autowired
    private StudentService studentService;

    // ANSI colors for a colorful terminal
    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[96m";
    private static final String BLUE = "\u001B[94m";
    private static final String GREEN = "\u001B[92m";
    private static final String YELLOW = "\u001B[93m";
    private static final String BOLD = "\u001B[1m";

    public static void main(String[] args) {
        SpringApplication.run(CollegeApp.class, args);
    }

    @Override
    public void run(String... args) {
        var students = studentService.getStudents();

        System.out.println();

        // Header
        System.out.println(CYAN + BOLD
                + "=============================================================="
                + RESET);
        System.out.println(CYAN + BOLD
                + "                 COLLEGE STUDENT DIRECTORY"
                + RESET);
        System.out.println(BLUE
                + "                  Spring Boot | Experiment 3.2"
                + RESET);
        System.out.println(CYAN + BOLD
                + "=============================================================="
                + RESET);

        // Table heading
        System.out.printf(CYAN + BOLD
                + "| %-5s | %-15s | %-30s |%n"
                + RESET, "NO.", "STUDENT", "DEPARTMENT");

        System.out.println(BLUE
                + "--------------------------------------------------------------"
                + RESET);

        // Student records
        int number = 1;

        for (String student : students) {
            String[] details = student.split(" - ", 2);

            String name = details[0];
            String department = details.length > 1
                    ? details[1] : "Not specified";

            System.out.printf(
                    GREEN + "| %-5s " + RESET
                    + "| " + YELLOW + "%-15s " + RESET
                    + "| " + CYAN + "%-30s " + RESET
                    + "|%n",
                    String.format("%02d", number),
                    name,
                    department
            );

            number++;
        }

        // Footer
        System.out.println(CYAN + BOLD
                + "=============================================================="
                + RESET);

        System.out.println(
                BOLD + " TOTAL STUDENTS: " + GREEN
                + students.size() + RESET
                + BOLD + "     STATUS: " + GREEN
                + "LOADED SUCCESSFULLY" + RESET
        );

        System.out.println(CYAN + BOLD
                + "=============================================================="
                + RESET);

        System.out.println();
    }
}
