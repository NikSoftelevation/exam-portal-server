package com.exam.model;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
@AllArgsConstructor
public class Authority implements GrantedAuthority{
    private String authorit;
    @Override
    public String getAuthority(){
        return authorit;
    }
}