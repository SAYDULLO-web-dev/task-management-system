package web_cybertron.taskmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import web_cybertron.taskmanagementsystem.entity.enums.SystemRoleName;
import web_cybertron.taskmanagementsystem.entity.template.AbsUUIDEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class Users extends AbsUUIDEntity {

    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String color;

    private String initialLetter;

    @OneToOne(fetch = FetchType.LAZY)
    private Attachment avatarId;

    @Enumerated(value = EnumType.STRING)
    private SystemRoleName systemRoleName;

    private String emailCode;

    public Users(String fullName, String email, String password, SystemRoleName systemRoleName) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.systemRoleName = systemRoleName;
    }

    public Users(String fullName, String email, String password, String emailCode, SystemRoleName systemRoleName) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.emailCode = emailCode;
        this.systemRoleName = systemRoleName;
    }

    @Override
    public String toString() {
        return "Users{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", color='" + color + '\'' +
                ", initialLetter='" + initialLetter + '\'' +
                ", avatarId=" + avatarId +
                ", systemRoleName=" + systemRoleName +
                ", emailCode='" + emailCode + '\'' +
                '}';
    }
}
