package az.company.springbootproject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
=======

import org.springframework.web.bind.annotation.GetMapping;

>>>>>>> 647a94d1d229f089bb73f4fa51b6a6199a368756
@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String studentNumber;
}
