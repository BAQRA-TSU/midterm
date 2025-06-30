package ge.tsu.Stugement.controller;

import ge.tsu.Stugement.entity.Student;
import ge.tsu.Stugement.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        logger.info("StudentController initialized");
    }

    @GetMapping
    public List<Student> getAllStudents() {
        logger.info("Retrieving all students");
        List<Student> students = studentRepository.findAll();
        logger.info("Found {} students", students.size());
        return students;
    }

    @PostMapping
    public Student createStudent(@Valid @RequestBody Student student) {
        logger.info("Creating new student with name: {}", student.getName());
        Student savedStudent = studentRepository.save(student);
        logger.info("Created student with ID: {}", savedStudent.getId());
        return savedStudent;
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        logger.info("Attempting to delete student with ID: {}", id);
        try {
            studentRepository.deleteById(id);
            logger.info("Successfully deleted student with ID: {}", id);
        } catch (Exception e) {
            logger.error("Failed to delete student with ID: {}", id, e);
            throw e;
        }
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @Valid @RequestBody Student updatedStudent) {
        logger.info("Attempting to update student with ID: {}", id);
        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(updatedStudent.getName());
                    student.setAge(updatedStudent.getAge());
                    Student saved = studentRepository.save(student);
                    logger.info("Successfully updated student with ID: {}", id);
                    return saved;
                })
                .orElseThrow(() -> {
                    logger.error("Student not found with ID: {}", id);
                    return new RuntimeException("Student not found");
                });
    }
}