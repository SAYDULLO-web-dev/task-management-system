package web_cybertron.taskmanagementsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import web_cybertron.taskmanagementsystem.entity.template.AbsUUIDEntity;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckListItem extends AbsUUIDEntity {

    @ManyToOne
    @Column(nullable = false)
    private CheckList checkListId;

    @Column(nullable = false)
    private boolean resolved;

    @Column(nullable = false)
    private List<User> assignedUsers;
}
