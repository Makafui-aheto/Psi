package com.example.psi.commons.model;

import java.util.List;

public enum AssignedPermissions{

    user_permissions(List.of(EPermission.create, EPermission.read)),
    author_permissions(List.of(EPermission.values())),
    superuser_permissions(List.of(EPermission.values())),
    admin_permissions(List.of(EPermission.read, EPermission.create, EPermission.edit));

    private final List<EPermission> permissions;

    AssignedPermissions(List<EPermission> permissions){
        this.permissions  = permissions;
    }

    public List<EPermission> getPermissions() {
        return this.permissions;
    }
}
