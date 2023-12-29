package dev.gabriel.billgroup.models;

import dev.gabriel.bill.models.Bill;
import dev.gabriel.billgroup.events.*;
import dev.gabriel.billgroup.exceptions.BillItemAlreadyAddedException;
import dev.gabriel.billgroup.valueobjects.BillGroupComment;
import dev.gabriel.billgroup.valueobjects.BillGroupId;
import dev.gabriel.billgroup.valueobjects.BillGroupName;
import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.shared.valueobjects.UpdatedAt;
import dev.gabriel.user.valueobjects.UserId;
import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
public class BillGroup extends AggregateRoot {
    private BillGroupName name;
    private BillGroupComment comment;
    private UserId userId;
    private List<BillGroupItem> bills;

    private BillGroup(String id, String name, String comment, UserId userId) {
        super(BillGroupId.create(id));
        this.name = BillGroupName.create(name);
        this.comment = BillGroupComment.create(comment);
        this.userId = userId;
        this.bills = new ArrayList<>();
    }

    public static BillGroup create(String id, String name, String comment, UserId userId) {
        BillGroup billGroup = new BillGroup(id, name, comment, userId);
        billGroup.raiseEvent(new BillGroupCreatedEvent(billGroup.getId()));
        return billGroup;
    }

    public void rename(String name) {
        this.name = BillGroupName.create(name);
        updatedAt = UpdatedAt.create(Instant.now());
        raiseEvent(new BillGroupRenamedEvent(getId()));
    }

    public void editComment(String comment) {
        this.comment = BillGroupComment.create(comment);
        updatedAt = UpdatedAt.create(Instant.now());
        raiseEvent(new BillGroupCommentEditedEvent(getId()));
    }

    public void addBill(Bill bill) {
        Optional<BillGroupItem> alreadyInBill = bills.stream().filter(item -> item.getBillId().equals(bill.getId())).findFirst();
        if(alreadyInBill.isPresent()) {
            throw new BillItemAlreadyAddedException();
        }

        bills.add(BillGroupItem.create(UUID.randomUUID().toString(), bill.getId(), getId()));
        updatedAt = UpdatedAt.create(Instant.now());
        raiseEvent(new BillItemAddedEvent(getId()));
    }

    public void deleteBill(Bill bill) {
        bills.removeIf(item -> item.getBillId().equals(bill.getId()));
        updatedAt = UpdatedAt.create(Instant.now());
        raiseEvent(new BillItemDeletedEvent(getId()));
    }

    public void clearBills() {
        bills.clear();
    }

    public void delete() {
        clearBills();
        raiseEvent(new BillGroupDeletedEvent(getId()));
    }

    @Override
    public BillGroupId getId() {
        return (BillGroupId) id;
    }
}
