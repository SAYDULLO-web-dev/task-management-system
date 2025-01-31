package web_cybertron.taskmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @Column(nullable = false)
    private Task taskId;

    @ManyToOne
    @Column(nullable = false)
    private Attachment attachmentId;

    @Column(nullable = false)
    private File pinCoverImage;
}
