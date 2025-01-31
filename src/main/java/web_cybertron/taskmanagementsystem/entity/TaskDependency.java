package web_cybertron.taskmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDependency{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @Column(nullable = false)
    private Task taskId;

    @ManyToOne
    private Task dependencyTaskId;

    @Enumerated
    @Column(nullable = false)
    private String dependencyType;
}
