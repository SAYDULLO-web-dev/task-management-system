package web_cybertron.taskmanagementsystem.entity;

import jakarta.persistence.Column;
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
public class Priority extends AbsUUIDEntity {

    @ManyToOne
    @Column(nullable = false)
    private Icon iconId;
}
