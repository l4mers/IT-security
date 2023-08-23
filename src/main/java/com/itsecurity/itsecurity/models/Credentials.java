package com.itsecurity.itsecurity.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Credentials{
    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private String password;
}
