package web_cybertron.taskmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Icon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @ManyToOne
    private Attachment attachmentId;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String initialLetter;

    @Column(nullable = false)
    private Object icon;
}
