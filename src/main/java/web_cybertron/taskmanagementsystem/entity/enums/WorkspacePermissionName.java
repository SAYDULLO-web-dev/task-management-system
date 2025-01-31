package web_cybertron.taskmanagementsystem.entity.enums;

import java.util.Arrays;
import java.util.List;

public enum WorkspacePermissionName {

    CAN_ADD_MEMBER("Adding members",
            "Gives the user the permission to add members (employers) to the Workspace",
            Arrays.asList(WorkSpaceRoleName.ROLE_OWNER, WorkSpaceRoleName.ROLE_ADMIN)
    ),
    CAN_REMOVE_MEMBER("Removing members",
            "Gives the user the permission to remove members (employers) to the Workspace",
            Arrays.asList(WorkSpaceRoleName.ROLE_OWNER, WorkSpaceRoleName.ROLE_ADMIN)
    ),
    CAN_EDIT_WORKSPACE("Editing the  Workspace",
            "Gives the user the permission to editing the  Workspace",
            Arrays.asList(WorkSpaceRoleName.ROLE_OWNER, WorkSpaceRoleName.ROLE_ADMIN)
    ),
    CAN_ADD_GUEST("Adding Guests",
            "Gives the user the permission to add members (permanent employers) to the Workspace",
            Arrays.asList(WorkSpaceRoleName.ROLE_OWNER, WorkSpaceRoleName.ROLE_ADMIN)
    ),
    CAN_MANAGE_TAGS("Managing Tags",
            "Gives the user the permission to Managing Tags of the Workspace",
            Arrays.asList(WorkSpaceRoleName.ROLE_OWNER, WorkSpaceRoleName.ROLE_ADMIN, WorkSpaceRoleName.ROLE_MEMBER)
    ),
    CAN_SHARE("Sharing",
            "Gives the user the permission to sharing tasks of the Workspace",
            Arrays.asList(WorkSpaceRoleName.ROLE_OWNER, WorkSpaceRoleName.ROLE_ADMIN, WorkSpaceRoleName.ROLE_MEMBER)
    );


    public final String name;
    public final String description;
    public final List<WorkSpaceRoleName> workSpaceRoleNames;

    WorkspacePermissionName(String name, String description, List<WorkSpaceRoleName> workSpaceRoleNames) {
        this.name = name;
        this.description = description;
        this.workSpaceRoleNames = workSpaceRoleNames;
    }
}
