package com.had.his.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name="receptionist_schedule",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"day", "start_time","end_time"})})
public class ReceptionistSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Integer scheduleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "day",nullable = false)
    private DayOfWeek day;

    @Column(name = "start_time",nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time",nullable = false)
    private LocalTime endTime;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receptionist_id",referencedColumnName = "receptionist_id",nullable = false)
    private Receptionist receptionist;

    public ReceptionistSchedule() {
    }

    public ReceptionistSchedule(Integer scheduleId, DayOfWeek day, LocalTime startTime, LocalTime endTime, Receptionist receptionist) { // Updated constructor parameter names
        this.scheduleId = scheduleId;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.receptionist = receptionist;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Receptionist getReceptionist() {
        return receptionist;
    }

    public void setReceptionist(Receptionist receptionist) {
        this.receptionist = receptionist;
    }

    @Override
    public String toString() {
        return "ReceptionistSchedule{" +
                "scheduleId=" + scheduleId +
                ", day=" + day +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", receptionist=" + receptionist +
                '}';
    }
}

