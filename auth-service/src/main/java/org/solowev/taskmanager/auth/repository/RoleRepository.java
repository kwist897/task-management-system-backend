package org.solowev.taskmanager.auth.repository;

import org.solowev.taskmanager.auth.domain.Role;
import org.solowev.taskmanager.auth.utils.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(RoleEnum role);
}
