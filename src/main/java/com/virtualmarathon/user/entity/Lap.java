package com.virtualmarathon.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Lap {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User athlete;

    @ManyToOne(fetch = FetchType.EAGER)
    private Marathon marathon;

    private BigDecimal distance;

    private Long lapCompletedInSeconds;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timeOfSubmission;

    private Long leaderboardPosition;

    private Long points;

    public Lap() {
    }

    public Lap(User athlete, Marathon marathon, LocalDateTime timeOfSubmission) {
        this.athlete = athlete;
        this.marathon = marathon;
        this.timeOfSubmission=timeOfSubmission;
    }

    public Long getId() {
        return id;
    }

    public User getAthlete() {
        return athlete;
    }

    public Marathon getMarathon() {
        return marathon;
    }

    public Long getLeaderboardPosition() {
        return leaderboardPosition;
    }

    public Long getPoints() {
        return points;
    }

    public Long getLapCompletedInSeconds() {
        return lapCompletedInSeconds;
    }

    public LocalDateTime getTimeOfSubmission() {
        return timeOfSubmission;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public void setTimeOfSubmission(LocalDateTime timeOfSubmission) {
        this.timeOfSubmission = timeOfSubmission;
    }

    public void setLeaderboardPosition(Long leaderboardPosition) {
        this.leaderboardPosition = leaderboardPosition;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public void setLapCompletedInSeconds(Long lapCompletedInSeconds) {
        this.lapCompletedInSeconds = lapCompletedInSeconds;
    }

    @Override
    public String toString() {
        return "Lap{" +
                "id=" + id +
                ", athlete=" + (athlete==null?"null":athlete) +
                ", marathon=" + (marathon==null?"null":marathon) +
                ", distance=" + (distance==null?"null":distance) +
                ", lapCompletedInSeconds=" + (lapCompletedInSeconds==null?"null":lapCompletedInSeconds) +
                ", timeOfSubmission=" + (timeOfSubmission==null?"null":timeOfSubmission) +
                ", leaderboardPosition=" + (leaderboardPosition==null?"null":leaderboardPosition) +
                ", points=" + (points==null?"null":points)+
                '}';
    }
}
