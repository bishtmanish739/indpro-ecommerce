package com.indpro.assignment.assignment.repository;

import com.indpro.assignment.assignment.entity.OrderItem;
import com.indpro.assignment.assignment.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByName(String name);
}
