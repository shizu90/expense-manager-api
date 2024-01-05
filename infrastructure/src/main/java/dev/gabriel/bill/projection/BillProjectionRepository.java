package dev.gabriel.bill.projection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillProjectionRepository extends JpaRepository<BillProjection, String> {

}
