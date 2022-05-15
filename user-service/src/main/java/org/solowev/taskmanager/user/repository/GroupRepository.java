package org.solowev.taskmanager.user.repository;

import org.solowev.taskmanager.user.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByParticipants_Id(Long id);
}
