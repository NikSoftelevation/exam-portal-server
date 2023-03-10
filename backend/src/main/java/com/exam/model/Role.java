package com.exam.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long roleId;
    @NotNull(message="Role name can't be null")
    private String roleName;
    @OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="role")
    private Set<UserRole> userRoles=new HashSet<>();
}