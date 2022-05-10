package org.solowev.taskmanager.auth.repository;

import org.solowev.taskmanager.auth.domain.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessTokenRepository extends JpaRepository<AccessToken, String> {
}
