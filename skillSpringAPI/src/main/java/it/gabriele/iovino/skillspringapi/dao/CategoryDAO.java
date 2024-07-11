package it.gabriele.iovino.skillspringapi.dao;

import it.gabriele.iovino.skillspringapi.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDAO extends JpaRepository<Category, Integer> {
}