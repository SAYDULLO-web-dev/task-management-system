package web_cybertron.taskmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @Column(nullable = false)
    private Task taskId;

    @Column(nullable = false)
    private String changeFieldName;

    @Column(nullable = false)
    private String before;

    @Column(nullable = false)
    private String after;

    @Column(nullable = false)
    private Object data;
}
