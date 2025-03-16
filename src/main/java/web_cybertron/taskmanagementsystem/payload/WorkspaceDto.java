package web_cybertron.taskmanagementsystem.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class WorkspaceDto {

    private String name;

    private String color;

    private UUID avatarId;
}
