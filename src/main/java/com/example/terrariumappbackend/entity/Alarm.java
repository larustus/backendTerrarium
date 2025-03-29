package com.example.terrariumappbackend.entity;

import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@Table(name = "alarms")
@NoArgsConstructor
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(name = "start_time")
    private Time start_time;

    @Column(name = "end_time")
    private Time end_time;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "highest_offshoot")
    private Float highest_offshoot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terrarium_id")
    @JsonBackReference
    @NonNull
    private Terrarium terrarium;
}
