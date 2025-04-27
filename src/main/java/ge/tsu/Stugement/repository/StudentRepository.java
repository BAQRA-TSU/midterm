package ge.tsu.Stugement.repository;

import ge.tsu.Stugement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

