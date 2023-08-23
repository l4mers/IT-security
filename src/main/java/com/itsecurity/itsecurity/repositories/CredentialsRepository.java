package com.itsecurity.itsecurity.repositories;

import com.itsecurity.itsecurity.models.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialsRepository extends JpaRepository<Credentials, Long> {
    Boolean existsByUserNameAndPassword(String userName, String password);
    Credentials findByUserName(String userName);
    Credentials findByPassword(String password);
    Boolean existsByUserName(String userName);
}
