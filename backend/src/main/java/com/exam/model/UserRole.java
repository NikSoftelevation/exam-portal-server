package com.exam.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRole{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long userRoleId;
    @ManyToOne(fetch=FetchType.EAGER)
    private User user;
    @ManyToOne
    private Role role;
}