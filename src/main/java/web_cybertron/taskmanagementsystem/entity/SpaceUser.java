package web_cybertron.taskmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Member;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpaceUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @Column(nullable = false)
    private Space spaceId;

    @ManyToOne
    @Column(nullable = false)
    private Member memberId;
}
