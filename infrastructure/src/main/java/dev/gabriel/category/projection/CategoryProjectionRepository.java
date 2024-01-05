package dev.gabriel.category.projection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryProjectionRepository extends JpaRepository<CategoryProjection, String> {

}
