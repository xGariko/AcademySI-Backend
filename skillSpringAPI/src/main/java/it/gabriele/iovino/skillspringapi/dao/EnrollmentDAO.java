package it.gabriele.iovino.skillspringapi.dao;

import it.gabriele.iovino.skillspringapi.models.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentDAO extends JpaRepository<Enrollment, Integer> {
    Optional<List<Enrollment>> findAllByUserId(int userId);
    Optional<List<Enrollment>> findAllByCourseId(int courseId);
}