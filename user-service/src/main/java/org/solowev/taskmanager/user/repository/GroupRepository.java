package org.solowev.taskmanager.user.repository;

import org.solowev.taskmanager.user.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
