package web_cybertron.taskmanagementsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import web_cybertron.taskmanagementsystem.entity.template.AbsUUIDEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryUser extends AbsUUIDEntity {

    @ManyToOne
    @Column(nullable = false)
    private Category categoryId;

    @ManyToOne
    @Column(nullable = false)
    private User userId;

    @Enumerated
    @Column(nullable = false)
    private String taskPermission;
}
