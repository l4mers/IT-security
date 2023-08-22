package com.itsecurity.itsecurity.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Credentials {

    @Id
    @GeneratedValue
    private Long id;

    private String userName;
    private String password;
    private Date created;
    private Date updated;

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }
    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }
}
