import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private int grade;

    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Grade: " + grade;
    }
}

public class GradeTracker {
    private ArrayList<Student> students;

    public GradeTracker() {
        students = new ArrayList<>();
    }

    // Method to add a student with a name and grade
    public void addStudent(String name, int grade) {
        students.add(new Student(name, grade));
        System.out.println("Student added: " + name + " with grade " + grade);
    }

    // Method to display all students with their grades
    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        System.out.println("Student List:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    // Method to update a student's name or grade
    public boolean updateStudent(String currentName, String newName, Integer newGrade) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(currentName)) {
                if (newName != null) {
                    student.setName(newName);
                }
                if (newGrade != null) {
                    student.setGrade(newGrade);
                }
                return true;
            }
        }
        return false;
    }

    // Method to calculate the average grade
    public double calculateAverage() {
        if (students.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (Student student : students) {
            sum += student.getGrade();
        }
        return (double) sum / students.size();
    }

    // Method to find the student with the highest grade
    public Student findHighestGrade() {
        if (students.isEmpty()) {
            return null;
        }
        Student topStudent = students.get(0);
        for (Student student : students) {
            if (student.getGrade() > topStudent.getGrade()) {
                topStudent = student;
            }
        }
        return topStudent;
    }

    // Method to find the student with the lowest grade
    public Student findLowestGrade() {
        if (students.isEmpty()) {
            return null;
        }
        Student lowestStudent = students.get(0);
        for (Student student : students) {
            if (student.getGrade() < lowestStudent.getGrade()) {
                lowestStudent = student;
            }
        }
        return lowestStudent;
    }

    public static void main(String[] args) {
        GradeTracker gradeTracker = new GradeTracker();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nGrade Tracker Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Calculate Average Grade");
            System.out.println("4. Find Highest Grade");
            System.out.println("5. Find Lowest Grade");
            System.out.println("6. Update Student");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            
            switch (choice) {
                case 1:
                    System.out.print("Enter student's name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter grade for " + name + ": ");
                    int grade = scanner.nextInt();
                    gradeTracker.addStudent(name, grade);
                    break;
                case 2:
                    gradeTracker.displayStudents();
                    break;
                case 3:
                    System.out.println("Average grade: " + gradeTracker.calculateAverage());
                    break;
                case 4:
                    Student topStudent = gradeTracker.findHighestGrade();
                    if (topStudent != null) {
                        System.out.println("Highest grade: " + topStudent.getGrade() + " by " + topStudent.getName());
                    } else {
                        System.out.println("No students found.");
                    }
                    break;
                case 5:
                    Student lowestStudent = gradeTracker.findLowestGrade();
                    if (lowestStudent != null) {
                        System.out.println("Lowest grade: " + lowestStudent.getGrade() + " by " + lowestStudent.getName());
                    } else {
                        System.out.println("No students found.");
                    }
                    break;
                case 6:
                    System.out.print("Enter the current name of the student to update: ");
                    String currentName = scanner.nextLine();
                    System.out.print("Enter new name (or press Enter to keep current name): ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new grade (or -1 to keep current grade): ");
                    int newGrade = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    boolean updated = gradeTracker.updateStudent(
                            currentName,
                            newName.isEmpty() ? null : newName,
                            newGrade == -1 ? null : newGrade
                    );
                    if (updated) {
                        System.out.println("Student details updated successfully.");
                    } else {
                        System.out.println("Student with the name \"" + currentName + "\" not found.");
                    }
                    break;
                case 7:
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
