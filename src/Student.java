public class Student {
    private String username;
    private String name;
    private String email;
    private String program;
    private String semester;
    private List<String> registeredCourses;

    public Student(String username) {
        this.username = username;
        this.registeredCourses = new ArrayList<>();
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }
    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }
    public List<String> getRegisteredCourses() { return registeredCourses; }
    public void setRegisteredCourses(List<String> registeredCourses) {
        this.registeredCourses = new ArrayList<>(registeredCourses);
    }
}