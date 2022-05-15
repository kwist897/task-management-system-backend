package org.solowev.taskmanager.user.repository;

import org.solowev.taskmanager.user.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
