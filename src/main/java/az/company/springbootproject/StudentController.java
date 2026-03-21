package az.company.springbootproject;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*") //frontend ucun
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }
    @GetMapping("/all")
    public List<Student> getAllStudents(){
        return service.getAll();
    }
    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student){
        return service.addStudent(student);

    }
    @GetMapping("/search")
    public List<Student> search(@RequestParam String query){
        return service.searchStudents(query);
    }
}
