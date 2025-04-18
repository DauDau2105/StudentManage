package com.btec.binhtth_Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// Student Class
class Student {
    private String id;
    private String name;
    private double mark;

    public Student(String id, String name, double mark) {
        this.id = id;
        this.name = name;
        this.mark = mark;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getMark() { return mark; }

    public void setName(String name) { this.name = name; }
    public void setMark(double mark) { this.mark = mark; }

    public String getGrade() {
        if (mark >= 9.0) return "Excellent";
        if (mark >= 7.5) return "Very Good";
        if (mark >= 6.5) return "Good";
        if (mark >= 5.0) return "Average";
        return "Fail";
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Mark: " + mark + ", Grade: " + getGrade();
    }
}

// Student Management System
class StudentSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Student> students = new ArrayList<>();

    // Add Student
    public static void addStudent() {
        System.out.println("=== Add New Student ===");
        System.out.print("Enter ID: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("Error: ID cannot be empty!");
            return;
        }

        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.println("Error: ID " + id + " already exists!");
                return;
            }
        }

        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Error: Name cannot be empty!");
            return;
        }

        System.out.print("Enter mark (0-10): ");
        try {
            double mark = Double.parseDouble(scanner.nextLine());
            if (mark < 0 || mark > 10) {
                System.out.println("Error: Mark must be from 0 to 10!");
                return;
            }
            Student student = new Student(id, name, mark);
            students.add(student);
            System.out.println("Student added: ID " + id);
        } catch (NumberFormatException e) {
            System.out.println("Error: Mark must be a number!");
        }
    }

    // Delete Student
    public static void deleteStudent() {
        System.out.println("=== Delete Student ===");
        System.out.print("Enter student ID to delete: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("Error: ID cannot be empty!");
            return;
        }

        Student studentToDelete = null;
        for (Student student : students) {
            if (student.getId().equals(id)) {
                studentToDelete = student;
                break;
            }
        }

        if (studentToDelete != null) {
            students.remove(studentToDelete);
            System.out.println("Deleted: " + studentToDelete);
        } else {
            System.out.println("Error: No student found with ID " + id);
        }
    }

    // Edit Student
    public static void editStudent() {
        System.out.println("=== Edit Student ===");
        System.out.print("Enter student ID to edit: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("Error: ID cannot be empty!");
            return;
        }

        Student student = null;
        for (Student s : students) {
            if (s.getId().equals(id)) {
                student = s;
                break;
            }
        }

        if (student == null) {
            System.out.println("Error: No student found with ID " + id);
            return;
        }

        System.out.print("Enter new name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Error: Name cannot be empty!");
            return;
        }

        System.out.print("Enter new mark (0-10): ");
        try {
            double mark = Double.parseDouble(scanner.nextLine());
            if (mark < 0 || mark > 10) {
                System.out.println("Error: Mark must be from 0 to 10!");
                return;
            }
            student.setName(name);
            student.setMark(mark);
            System.out.println("Updated: " + student);
        } catch (NumberFormatException e) {
            System.out.println("Error: Mark must be a number!");
        }
    }

    // Show Ranking
    public static void displayRanking() {
        if (students.isEmpty()) {
            System.out.println("Error: Student list is empty!");
            return;
        }

        List<Student> sortedStudents = new ArrayList<>(students);
        Collections.sort(sortedStudents, (s1, s2) -> Double.compare(s2.getMark(), s1.getMark()));

        System.out.println("=== Student Ranking ===");
        for (int i = 0; i < sortedStudents.size(); i++) {
            System.out.println((i + 1) + ". " + sortedStudents.get(i));
        }
    }

    // Search Student (Linear Search)
    public static void searchStudent() {
        System.out.println("=== Search Student ===");
        System.out.print("Enter student ID to find: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("Error: ID cannot be empty!");
            return;
        }

        // Linear search through the students list
        Student foundStudent = null;
        for (Student student : students) {
            if (student.getId().equals(id)) {
                foundStudent = student;
                break;
            }
        }

        if (foundStudent != null) {
            System.out.println("Found: " + foundStudent);
        } else {
            System.out.println("Error: No student found with ID " + id);
        }
    }

    // Display All Students
    public static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("Error: No students in the system!");
            return;
        }

        System.out.println("=== All Students ===");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    // Sort Students by Mark
    public static void sortStudentsByMark() {
        if (students.isEmpty()) {
            System.out.println("Error: No students to sort!");
            return;
        }
        System.out.print("Sort by (1) Ascending or (2) Descending? Choose 1 or 2: ");
        String option = scanner.nextLine().trim();
        List<Student> sortedList = new ArrayList<>(students);
        if (option.equals("1")) {
            sortedList.sort((s1, s2) -> Double.compare(s1.getMark(), s2.getMark()));
            System.out.println("=== Students Sorted by Mark (Ascending) ===");
        } else if (option.equals("2")) {
            sortedList.sort((s1, s2) -> Double.compare(s2.getMark(), s1.getMark()));
            System.out.println("=== Students Sorted by Mark (Descending) ===");
        } else {
            System.out.println("Error: Invalid choice.");
            return;
        }
        for (Student s : sortedList) {
            System.out.println(s);
        }
    }

    // Main Menu
    public static void showMenu() {
        while (true) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add student");
            System.out.println("2. Delete student");
            System.out.println("3. Edit student");
            System.out.println("4. Show student ranking");
            System.out.println("5. Search student");
            System.out.println("6. Display all students");
            System.out.println("7. Sort students by mark");
            System.out.println("8. Exit");
            System.out.print("Choose option (1-8): ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        deleteStudent();
                        break;
                    case 3:
                        editStudent();
                        break;
                    case 4:
                        displayRanking();
                        break;
                    case 5:
                        searchStudent();
                        break;
                    case 6:
                        displayAllStudents();
                        break;
                    case 7:
                        sortStudentsByMark();
                        break;
                    case 8:
                        System.out.println("Exiting the program.");
                        return;
                    default:
                        System.out.println("Error: Please choose a valid option (1-8).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number!");
            }
        }
    }

    // Main method to run the program
    public static void main(String[] args) {
        showMenu();
    }
}
