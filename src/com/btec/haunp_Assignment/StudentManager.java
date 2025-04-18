package com.btec.haunp_Assignment;

import static com.btec.haunp_Assignment.StudentManager.Student.displayStudentsByScoreDesc;
import java.util.*;

public class StudentManager {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Student> studentList = new ArrayList<>();

    // Student class
    static class Student {
        String id;
        String name;
        double score;
        // Display all students sorted by score descending
public static void displayStudentsByScoreDesc() {
    if (studentList.isEmpty()) {
        System.out.println("No students found.");
        return;
    }

    
    studentList.sort((a, b) -> Double.compare(b.score, a.score));

    System.out.println("List of students (high to low score):");
    for (Student s : studentList) {
        s.display();
    }
}

        public Student(String id, String name, double score) {
            this.id = id;
            this.name = name;
            this.score = score;
        }

        public void display() {
            System.out.println("ID: " + id + " | Name: " + name + " | Score: " + score);
        }
    }

    // Main method
    public static void main(String[] args) {
        int choice = -1;
        do {
            try {
                System.out.println("\n==== STUDENT MANAGEMENT MENU ====");
                System.out.println("1. Add a student");
                System.out.println("2. Delete a student by ID");
                System.out.println("3. Update student");
                System.out.println("4. Show ranking");
                System.out.println("5. Search by ID");
                System.out.println("6. Display students by score (high to low)");
                System.out.println("0. Exit");
                System.out.print("Choose: ");

                choice = Integer.parseInt(scanner.nextLine()); // may cause error

                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> deleteStudent();
                    case 3 -> updateStudent();
                    case 4 -> rankStudents();
                    case 5 -> searchStudent();
                    case 6 -> displayStudentsByScoreDesc();
                    case 0 -> System.out.println("Goodbye.");
                    default -> System.out.println("Wrong choice!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please type a number (e.g., 1, 2, 3...).");
            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        } while (choice != 0);
    }

    // Add student
    public static void addStudent() {
    try {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();

        // Check if ID already exists
        for (Student s : studentList) {
            if (s.id.equalsIgnoreCase(id)) {
                System.out.println("This ID already exists. Please use another ID.");
                return;
            }
        }

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter score (0 to 10): ");
        double score = Double.parseDouble(scanner.nextLine());

        // Check if score is valid
        if (score < 0 || score > 10) {
            System.out.println(" Score must be between 0 and 10.");
            return;
        }

        studentList.add(new Student(id, name, score));
        System.out.println("Student added successfully.");
    } catch (NumberFormatException e) {
        System.out.println("Invalid score. Please enter a number (e.g., 7.5).");
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}

    // Delete student
    public static void deleteStudent() {
        System.out.print("Enter ID to delete: ");
        String id = scanner.nextLine();
        boolean removed = studentList.removeIf(s -> s.id.equalsIgnoreCase(id));
        if (removed)
            System.out.println("Student deleted.");
        else
            System.out.println("Student not found.");
    }

    // Update student
    public static void updateStudent() {
        System.out.print("Enter ID to update: ");
        String id = scanner.nextLine();
        for (Student s : studentList) {
            if (s.id.equalsIgnoreCase(id)) {
                try {
                    System.out.print("Enter new name: ");
                    s.name = scanner.nextLine();
                    System.out.print("Enter new score: ");
                    s.score = Double.parseDouble(scanner.nextLine());
                    System.out.println("Student updated.");
                } catch (NumberFormatException e) {
                    System.out.println("Wrong score. Update failed.");
                }
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // Show ranking
    public static void rankStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        studentList.sort((a, b) -> Double.compare(b.score, a.score));
        System.out.println("Student ranking:");
        for (Student s : studentList) {
            s.display();
            System.out.println("   → Classification: " + classifyScore(s.score));
        }
    }

    // Classify score
    public static String classifyScore(double score) {
        if (score < 5.0) return "Fail";
        else if (score < 6.5) return "Medium";
        else if (score < 7.5) return "Good";
        else if (score < 9.0) return "Very Good";
        else return "Excellent";
    }

    // Search by ID 
    //This is a linear algorithm
    public static void searchStudent() {
        System.out.print("Enter ID to search: ");
        String id = scanner.nextLine();
        for (Student s : studentList) {
            }
    }
}