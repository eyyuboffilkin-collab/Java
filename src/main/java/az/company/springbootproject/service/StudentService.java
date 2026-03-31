package az.company.springbootproject.service;

import az.company.springbootproject.entity.Course;
import az.company.springbootproject.entity.Student;
import az.company.springbootproject.repository.CourseRepository;
import az.company.springbootproject.repository.StudentRepository;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }
    public List<Student>getAllStudents(){
        return studentRepository.findAll();
    }
    @Transactional
    public void enrollStudent(Long studentId, Long courseId){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        student.getCourses().add(course);
        studentRepository.save(student);
    }
    @Transactional
    public String processCsv(MultipartFile file) {
        List<Student> list = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] line;
            boolean isHeader = true;

            while ((line = reader.readNext()) != null) {
                if(isHeader){isHeader = false; continue;}
                if (line.length < 3) continue;



                Student student = new Student();
                student.setFirstName(line[0].trim());
                student.setLastName(line[1].trim());
                student.setStudentNumber(line[2].trim());

                list.add(student);
            }

            studentRepository.saveAll(list);
            return list.size() + " students were successfully added to the DB";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }


}
