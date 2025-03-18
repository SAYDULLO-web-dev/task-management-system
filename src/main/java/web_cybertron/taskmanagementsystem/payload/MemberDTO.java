package web_cybertron.taskmanagementsystem.payload;

import lombok.Data;
import web_cybertron.taskmanagementsystem.entity.enums.MemberActionType;

import java.util.UUID;

/*
 *@project: task-management-system
 *@created on: 3/19/2025
 *@author: SAYDULLO-web-dev
 */
@Data
public class MemberDTO {

    private UUID id;

    private UUID roleId;  // id which as a member or guest

    private MemberActionType memberActionType; // add, edit, remove member
}
