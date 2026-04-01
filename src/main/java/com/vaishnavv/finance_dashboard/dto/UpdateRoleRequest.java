package com.vaishnavv.finance_dashboard.dto;

import com.vaishnavv.finance_dashboard.model.Role;

public class UpdateRoleRequest {

    private Role role;

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
}
