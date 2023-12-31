package dev.gabriel.billgroup.repositories;

import dev.gabriel.billgroup.models.BillGroup;
import dev.gabriel.billgroup.valueobjects.BillGroupId;
import dev.gabriel.shared.repositories.IDomainRepository;
import dev.gabriel.user.valueobjects.UserId;

import java.util.List;
import java.util.Optional;

public interface IBillGroupRepository extends IDomainRepository {
    Optional<BillGroup> findById(BillGroupId billGroupId);
    List<BillGroup> findByUserId(UserId userId);
    BillGroup save(BillGroup billGroup);
    void deleteById(BillGroupId billGroupId);
}
