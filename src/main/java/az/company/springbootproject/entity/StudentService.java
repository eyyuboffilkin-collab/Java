package az.company.springbootproject.entity;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public String processCsv(MultipartFile file) {
        List<Student> list = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] line;
            boolean isHeader = true;

            while ((line = reader.readNext()) != null) {
                if (line.length == 0) continue;

                if (isHeader) {
                    isHeader = false;
                    continue;
                }

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
