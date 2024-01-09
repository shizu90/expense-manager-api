package dev.gabriel.category.projection;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Categories")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class CategoryProjection {
    @Id
    private String id;
    private String name;
    private String ownerId;

    public static CategoryProjection create(String id, String name, String ownerId) {
        return new CategoryProjection(id, name, ownerId);
    }
}
