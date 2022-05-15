package org.solowev.taskmanager.user.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.solowev.taskmanager.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.HashSet;
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
    @JoinColumn(name = "created_by")
    @ToString.Exclude
    private Profile createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    @ToString.Exclude
    private Profile updatedBy;

    @ManyToMany
    @JoinTable(name = "group_profile",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id"))
    @ToString.Exclude
    private Set<Profile> participants = new HashSet<>();

    public void addParticipant(Profile profile) {
        this.participants.add(profile);
        profile.getGroups().add(this);
    }

    public void removeParticipant(Profile profile) {
        this.participants.remove(profile);
        profile.getGroups().remove(this);
    }

}
