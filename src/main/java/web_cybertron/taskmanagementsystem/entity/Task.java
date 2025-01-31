package web_cybertron.taskmanagementsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import web_cybertron.taskmanagementsystem.entity.template.AbsUUIDEntity;

import java.sql.Time;
import java.util.Date;
import java.util.Timer;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task extends AbsUUIDEntity {

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @Column(nullable = false)
    private Status statusId;

    @ManyToOne
    @Column(nullable = false)
    private Category categoryId;

    @ManyToOne
    @Column(nullable = false)
    private Priority priorityId;

    @ManyToOne
    @Column(nullable = false)
    private Task parentTaskId;

    @Column(nullable = false)
    private Date startedDate;

    private Time startTimeHas;

    @Column(nullable = false)
    private Date completedDate;

    private Time completeTimeHas;

    @Column(nullable = false)
    private Timer estimateTime;

    @Column(nullable = false)
    private Date activatedDate;
}
