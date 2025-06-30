package ge.tsu.Stugement.controller;

import ge.tsu.Stugement.entity.Student;
import ge.tsu.Stugement.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentController studentController;

    private Student testStudent;

    @BeforeEach
    void setUp() {
        testStudent = new Student();
        testStudent.setId(1L);
        testStudent.setName("John Doe");
        testStudent.setAge(20);
    }

    @Test
    void getAllStudents_ShouldReturnListOfStudents() {
        // Arrange
        List<Student> expectedStudents = Arrays.asList(testStudent);
        when(studentRepository.findAll()).thenReturn(expectedStudents);

        // Act
        List<Student> actualStudents = studentController.getAllStudents();

        // Assert
        assertNotNull(actualStudents);
        assertEquals(1, actualStudents.size());
        assertEquals(testStudent.getName(), actualStudents.get(0).getName());
        verify(studentRepository).findAll();
    }

    @Test
    void createStudent_ShouldReturnSavedStudent() {
        // Arrange
        when(studentRepository.save(any(Student.class))).thenReturn(testStudent);

        // Act
        Student createdStudent = studentController.createStudent(testStudent);

        // Assert
        assertNotNull(createdStudent);
        assertEquals(testStudent.getName(), createdStudent.getName());
        assertEquals(testStudent.getAge(), createdStudent.getAge());
        verify(studentRepository).save(any(Student.class));
    }

    @Test
    void updateStudent_WhenStudentExists_ShouldReturnUpdatedStudent() {
        // Arrange
        Student updatedStudent = new Student();
        updatedStudent.setName("Jane Doe");
        updatedStudent.setAge(22);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(testStudent));
        when(studentRepository.save(any(Student.class))).thenReturn(updatedStudent);

        // Act
        Student result = studentController.updateStudent(1L, updatedStudent);

        // Assert
        assertNotNull(result);
        assertEquals(updatedStudent.getName(), result.getName());
        assertEquals(updatedStudent.getAge(), result.getAge());
        verify(studentRepository).findById(1L);
        verify(studentRepository).save(any(Student.class));
    }

    @Test
    void updateStudent_WhenStudentDoesNotExist_ShouldThrowException() {
        // Arrange
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> 
            studentController.updateStudent(1L, testStudent)
        );
        verify(studentRepository).findById(1L);
        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    void deleteStudent_ShouldCallRepositoryDeleteById() {
        // Arrange
        Long studentId = 1L;
        doNothing().when(studentRepository).deleteById(studentId);

        // Act
        studentController.deleteStudent(studentId);

        // Assert
        verify(studentRepository).deleteById(studentId);
    }
}