package com.backend.adoption.entities;

import java.util.Date;

import com.backend.adoption.entities.enuns.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column( nullable = false )
    private String name;

    @Column( nullable = false, unique = true)
    private String email;

    @Column( nullable = false )
    private String password;

    @Column( nullable = false )
    private String cpf;

    @Column( nullable = false )
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Temporal(TemporalType.TIMESTAMP)
    @Column()
    private Date createdAt = new Date();

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }
}
