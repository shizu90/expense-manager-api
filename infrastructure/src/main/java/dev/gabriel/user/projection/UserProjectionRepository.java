package dev.gabriel.user.projection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProjectionRepository extends JpaRepository<UserProjection, String> {

}
