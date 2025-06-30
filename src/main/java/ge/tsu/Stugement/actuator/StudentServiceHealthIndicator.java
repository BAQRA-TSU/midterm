package ge.tsu.Stugement.actuator;

import ge.tsu.Stugement.repository.StudentRepository;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class StudentServiceHealthIndicator implements HealthIndicator {

    private final StudentRepository studentRepository;

    public StudentServiceHealthIndicator(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Health health() {
        try {
            long studentCount = studentRepository.count();
            return Health.up()
                    .withDetail("studentCount", studentCount)
                    .withDetail("status", "Student service is running")
                    .build();
        } catch (Exception e) {
            return Health.down()
                    .withException(e)
                    .build();
        }
    }
}