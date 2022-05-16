package org.solowev.taskmanager.user.repository;

import org.solowev.taskmanager.user.domain.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {

    @Query(value = "select distinct w from Workspace w " +
            "where w.createdBy.id = :profileId " +
            "or w.group.id in :groupIds ")
    List<Workspace> findAllByProfileIdOrGroupIds(@Param("profileId") Long profileId, @Param("groupIds") List<Long> groupids);

    List<Workspace> findAllByGroup_Id(Long groupId);
}
