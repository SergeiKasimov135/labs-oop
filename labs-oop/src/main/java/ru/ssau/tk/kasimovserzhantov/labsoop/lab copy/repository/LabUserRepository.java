package ru.ssau.tk.kasimovserzhantov.labsoop.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity.LabUser;

import java.util.Optional;

@Repository
public interface LabUserRepository extends JpaRepository<LabUser, Integer> {

    Optional<LabUser> findByUsername(String username);

}

