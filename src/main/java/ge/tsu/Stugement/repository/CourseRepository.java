package ge.tsu.Stugement.repository;

import ge.tsu.Stugement.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
