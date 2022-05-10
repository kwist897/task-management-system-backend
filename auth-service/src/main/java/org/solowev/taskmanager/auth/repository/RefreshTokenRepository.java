package org.solowev.taskmanager.auth.repository;

import org.solowev.taskmanager.auth.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
}
