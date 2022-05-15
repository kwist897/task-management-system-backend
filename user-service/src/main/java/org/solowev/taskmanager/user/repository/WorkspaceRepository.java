package org.solowev.taskmanager.user.repository;

import org.solowev.taskmanager.user.domain.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
}
