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
public class Status extends AbsUUIDEntity {

    @ManyToOne
    @Column(nullable = false)
    private Space spaceId;

    @ManyToOne
    @Column(nullable = false)
    private Project projectId;

    @ManyToOne
    @Column(nullable = false)
    private Category categoryId;

    @Column(nullable = false)
    private String color;

    @Enumerated
    @Column(nullable = false)
    private String type;
}
