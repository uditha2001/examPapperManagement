package com.example.examManagementBackend.userManagement.userManagementEntity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;

    @Column(nullable = false,unique = true)
    private String permissionName;

    private String permissionDescription;

    @Column(nullable = false)
    private String permissionType;
    @OneToMany(mappedBy = "rolePermission")
    Set<RolePermission> rolePermissions;

    public PermissionEntity() {
    }

    public PermissionEntity(String permissionName, String permissionDescription, String permissionType, Set<RolePermission> rolePermissions) {
        this.permissionName = permissionName;
        this.permissionDescription = permissionDescription;
        this.permissionType = permissionType;
        this.rolePermissions = rolePermissions;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionDescription() {
        return permissionDescription;
    }

    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }

    public String getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType;
    }

    public Set<RolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(Set<RolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

    public Long getPermissionId() {
        return permissionId;
    }
}
