package web_cybertron.taskmanagementsystem.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class WorkspaceDTO {

    @NotBlank
    private String name;

    @NotNull
    private String color;

    private UUID avatarId;
}
