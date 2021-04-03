package com.exadel.team2.sandbox.web.role;

import java.util.ArrayList;
import java.util.List;

public class UpdateRoleDto {
    private String rlName;
    private String rlDescription;
    private List<Long> permissions = new ArrayList<>();
}
