package org.solowev.taskmanager.user.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.solowev.taskmanager.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class Group extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    @EqualsAndHashCode.Include
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private Boolean isPrivate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @ToString.Exclude
    private Profile createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @ToString.Exclude
    private Profile updatedBy;

    @ManyToMany(mappedBy = "groups")
    @ToString.Exclude
    private Set<Profile> participants;
}
