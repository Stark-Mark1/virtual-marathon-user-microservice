package com.virtualmarathon.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Entity
public class Marathon {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal distance;

    @ManyToOne(fetch = FetchType.EAGER)
    private User organizer;

    private String description;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime startTime;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime endTime;

    @Column(nullable = false)
    private Long maxParticipantCount;

    @Column(nullable = false)
    private Long currentParticipantCount;

    @Column(nullable = false)
    private boolean isPrivate;

    private String privateParticipantSet;

    @Column(nullable = false)
    private boolean resultDeclared;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "marathon")
    @JsonIgnore
    private List<Lap> participantSubmissions;

    public Marathon() {
    }

    public Marathon(String name, BigDecimal distance, User organizer, String description, LocalDateTime startTime, LocalDateTime endTime, Long maxParticipantCount, boolean isPrivate, String privateParticipantSet) {
        this.name = name;
        this.organizer = organizer;
        this.distance=distance;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxParticipantCount = maxParticipantCount;
        this.isPrivate = isPrivate;
        this.privateParticipantSet = privateParticipantSet;
        this.currentParticipantCount=0L;
        this.resultDeclared=false;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getOrganizer() {
        return organizer;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Long getMaxParticipantCount() {
        return maxParticipantCount;
    }

    public Long getCurrentParticipantCount() {
        return currentParticipantCount;
    }

    public List<Lap> getParticipantSubmissions() {
        return participantSubmissions;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }


    public void addParticipantSubmission(Lap participantSubmission) {
        this.participantSubmissions.add(participantSubmission);
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public String getPrivateParticipantSet() {
        return privateParticipantSet;
    }

    public boolean isResultDeclared() {
        return resultDeclared;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setMaxParticipantCount(Long maxParticipantCount) {
        this.maxParticipantCount = maxParticipantCount;
    }

    public void setCurrentParticipantCount(Long currentParticipantCount) {
        this.currentParticipantCount = currentParticipantCount;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public void setPrivateParticipantSet(String privateParticipantSet) {
        this.privateParticipantSet = privateParticipantSet;
    }

    public void setResultDeclared(boolean resultDeclared) {
        this.resultDeclared = resultDeclared;
    }
}
