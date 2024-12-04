package com.example.terrariumappbackend.entity;

import java.sql.Timestamp;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@Table(name = "readings")
@NoArgsConstructor
public class Reading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp date;

    @NonNull
    @Column(name = "temperature_1", nullable = false)
    private Integer temperature_1;

    @NonNull
    @Column(name = "temperature_2", nullable = false)
    private Integer temperature_2;

    @NonNull
    @Column(name = "humidity", nullable = false)
    private Integer humidity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terrarium_id")
    @JsonBackReference
    @NonNull
    private Terrarium terrarium;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getTemperature_1() {
        return temperature_1;
    }

    public void setTemperature_1(int temperature_1) {
        this.temperature_1 = temperature_1;
    }

    public int getTemperature_2() {
        return temperature_2;
    }

    public void setTemperature_2(int temperature_2) {
        this.temperature_2 = temperature_2;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public Terrarium getTerrarium() {
        return terrarium;
    }

    public void setTerrarium(Terrarium terrarium) {
        this.terrarium = terrarium;
    }

}
