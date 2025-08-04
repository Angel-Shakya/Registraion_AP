import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataManager {
    public static Map<String, String> loadCredentials() {
        Map<String, String> credentials = new HashMap<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get("credentials.txt"));
            for (String line : lines) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    credentials.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading credentials.txt: " + e.getMessage());
        }
        return credentials;
    }

    public static Student loadStudent(String username) {
        Student student = new Student(username);
        try {
            List<String> profileLines = Files.readAllLines(Paths.get("profiles.txt"));
            for (String line : profileLines) {
                String[] parts = line.split(":");
                if (parts.length == 5 && parts[0].equals(username)) {
                    student.setName(parts[1]);
                    student.setEmail(parts[2]);
                    student.setProgram(parts[3]);
                    student.setSemester(parts[4]);
                    break;
                }
            }
            List<String> registrationLines = Files.readAllLines(Paths.get("registrations.txt"));
            for (String line : registrationLines) {
                String[] parts = line.split(":");
                if (parts.length == 2 && parts[0].equals(username)) {
                    String[] courses = parts[1].split(",");
                    student.setRegisteredCourses(Arrays.asList(courses));
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading student data: " + e.getMessage());
        }
        return student;
    }

    public static void saveStudent(Student student) {
        try {
            List<String> profileLines = Files.readAllLines(Paths.get("profiles.txt"));
            boolean found = false;
            for (int i = 0; i < profileLines.size(); i++) {
                String[] parts = profileLines.get(i).split(":");
                if (parts[0].equals(student.getUsername())) {
                    profileLines.set(i, student.getUsername() + ":" + student.getName() + ":" +
                            student.getEmail() + ":" + student.getProgram() + ":" + student.getSemester());
                    found = true;
                    break;
                }
            }
            if (!found) {
                profileLines.add(student.getUsername() + ":" + student.getName() + ":" +
                        student.getEmail() + ":" + student.getProgram() + ":" + student.getSemester());
            }
            Files.write(Paths.get("profiles.txt"), profileLines);

            List<String> registrationLines = Files.readAllLines(Paths.get("registrations.txt"));
            found = false;
            for (int i = 0; i < registrationLines.size(); i++) {
                String[] parts = registrationLines.get(i).split(":");
                if (parts[0].equals(student.getUsername())) {
                    registrationLines.set(i, student.getUsername() + ":" +
                            String.join(",", student.getRegisteredCourses()));
                    found = true;
                    break;
                }
            }
            if (!found) {
                registrationLines.add(student.getUsername() + ":" +
                        String.join(",", student.getRegisteredCourses()));
            }
            Files.write(Paths.get("registrations.txt"), registrationLines);
        } catch (IOException e) {
            System.out.println("Error saving student data: " + e.getMessage());
        }
    }

    public static List<String> loadAvailableCourses() {
        try {
            return Files.readAllLines(Paths.get("courses.txt"));
        } catch (IOException e) {
            System.out.println("Error reading courses.txt: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}