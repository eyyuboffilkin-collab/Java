package az.company.springbootproject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*") //frontend 
public class StudentController {
        @Autowired
        private StudentRepository studentRepository;
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

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
            if (studentRepository.existsById(id)) {
                studentRepository.deleteById(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }

        }
    }
=======
@CrossOrigin(origins = "*") //frontend ucun
public class StudentController {
@CrossOrigin(origins = "*") //frontend u
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
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
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

}
}
