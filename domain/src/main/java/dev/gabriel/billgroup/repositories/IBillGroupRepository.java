package dev.gabriel.billgroup.repositories;

import dev.gabriel.billgroup.models.BillGroup;
import dev.gabriel.shared.repositories.IDomainRepository;

import java.util.List;
import java.util.Optional;

public interface IBillGroupRepository extends IDomainRepository {
    Optional<BillGroup> findById(String id);
    List<BillGroup> findByUserId(String userId);
    BillGroup save(BillGroup billGroup);
    void deleteById(String id);
}
