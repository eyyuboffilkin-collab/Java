package az.company.springbootproject.controller;

import az.company.springbootproject.entity.Course;
import az.company.springbootproject.entity.Student;
import az.company.springbootproject.service.StudentService;
import az.company.springbootproject.repository.CourseRepository;
import az.company.springbootproject.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {


    private final StudentService service;


    private final StudentRepository studentRepository;


    private final CourseRepository courseRepository;

    public StudentController(StudentService service, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.service = service;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @PostMapping("/csv/upload")
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty())
            return ResponseEntity.badRequest().body("File is empty!");
        String filename = file.getOriginalFilename();
        if (filename == null || !filename.toLowerCase().endsWith(".csv"))
            return ResponseEntity.badRequest().body("Only CSV files are allowed!");
        return ResponseEntity.ok(service.processCsv(file));
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentRepository.findAll());
    }

    @PostMapping("/students")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentRepository.save(student));
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setFirstName(updatedStudent.getFirstName());
        student.setLastName(updatedStudent.getLastName());
        student.setStudentNumber(updatedStudent.getStudentNumber());
        return ResponseEntity.ok(studentRepository.save(student));
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        student.getCourses().clear();
        studentRepository.save(student);
        studentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/students/{studentId}/enroll/{courseId}")
    public ResponseEntity<String> enrollStudent(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        student.getCourses().add(course);
        studentRepository.save(student);
        return ResponseEntity.ok("Student successfully enrolled in the course!");
    }

    @GetMapping("/students/{studentId}/courses")
    public ResponseEntity<Set<Course>> getStudentCourses(@PathVariable Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return ResponseEntity.ok(student.getCourses());
    }

    @DeleteMapping("/students/{studentId}/unenroll/{courseId}")
    public ResponseEntity<String> unenrollStudent(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        student.getCourses().remove(course);
        studentRepository.save(student);
        return ResponseEntity.ok("Student successfully removed from the course!");
    }

}