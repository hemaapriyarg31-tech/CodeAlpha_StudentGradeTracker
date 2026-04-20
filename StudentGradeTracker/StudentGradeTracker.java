import java.util.*;

class Student {
    String name;
    int marks;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    String getGrade() {
        if (marks >= 90) return "A";
        else if (marks >= 75) return "B";
        else if (marks >= 60) return "C";
        else return "D";
    }

    String getResult() {
        return (marks >= 50) ? "PASS" : "FAIL";
    }
}

public class StudentGradeTracker {

    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n==============================");
            System.out.println("   STUDENT GRADE TRACKER");
            System.out.println("==============================");
            System.out.println("1. Add Student");
            System.out.println("2. View Report");
            System.out.println("3. Search Student");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addStudent(sc);
                    break;
                case 2:
                    viewReport();
                    break;
                case 3:
                    searchStudent(sc);
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void addStudent(Scanner sc) {
        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        int marks;
        while (true) {
            System.out.print("Enter marks (0-100): ");
            marks = sc.nextInt();
            sc.nextLine();

            if (marks >= 0 && marks <= 100) break;
            System.out.println("Invalid input! Enter between 0–100.");
        }

        students.add(new Student(name, marks));
        System.out.println("✔ Student added!");
    }

    static void viewReport() {
        if (students.isEmpty()) {
            System.out.println("No student data available!");
            return;
        }

        int sum = 0;
        int highest = students.get(0).marks;
        int lowest = students.get(0).marks;

        System.out.println("\n----------- STUDENT REPORT -----------");

        for (Student s : students) {
            System.out.printf("Name: %-10s Marks: %-3d Grade: %-2s Result: %s\n",
                    s.name, s.marks, s.getGrade(), s.getResult());

            sum += s.marks;

            if (s.marks > highest) highest = s.marks;
            if (s.marks < lowest) lowest = s.marks;
        }

        double avg = (double) sum / students.size();

        System.out.println("\n----------- SUMMARY -----------");
        System.out.println("Total Students : " + students.size());
        System.out.println("Average Marks  : " + avg);
        System.out.println("Highest Marks  : " + highest);
        System.out.println("Lowest Marks   : " + lowest);
    }

    static void searchStudent(Scanner sc) {
        System.out.print("Enter name to search: ");
        String name = sc.nextLine();

        boolean found = false;

        for (Student s : students) {
            if (s.name.equalsIgnoreCase(name)) {
                System.out.println("Found → Name: " + s.name +
                        ", Marks: " + s.marks +
                        ", Grade: " + s.getGrade() +
                        ", Result: " + s.getResult());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Student not found!");
        }
    }
}