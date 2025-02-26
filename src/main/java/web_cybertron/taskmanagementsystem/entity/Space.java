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
public class Space extends AbsUUIDEntity {

    @Column(nullable = false)
    private String color;

    @ManyToOne
    @Column(nullable = false)
    private Workspace workspaceId;

    @Column(nullable = false)
    private String initialLetter;

    @ManyToOne
    @Column(nullable = false)
    private Icon iconId;

    @ManyToOne
    @Column(nullable = false)
    private Attachment avatarId;

    @ManyToOne
    @Column(nullable = false)
    private Users ownerId;

    @Column(nullable = false)
    private String accessType;
}
