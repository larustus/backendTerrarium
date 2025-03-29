package com.example.terrariumappbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.sql.Time;
import java.util.List;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "terrariums")
@NoArgsConstructor
public class Terrarium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "temperature_goal")
    private Float temperature_goal;

    @NonNull
    @Column(name = "humidity_goal")
    private Float humidity_goal;

    @NonNull
    @Column(name = "max_temp")
    private Float max_temp;

    @NonNull
    @Column(name = "min_temp")
    private Float min_temp;

    @NonNull
    @Column(name = "max_hum")
    private Float max_hum;

    @NonNull
    @Column(name = "min_hum")
    private Float min_hum;

    @Column(name = "water_time")
    private Time water_time;

    @Column(name = "water_period")
    private Integer water_period;

    @Column(name = "current_temp_1")
    private Float current_temp_1;

    @Column(name = "current_temp_2")
    private Float current_temp_2;

    @Column(name = "current_hum")
    private Float current_hum;

    @Column(name = "temperature_thermostat")
    private Float temperature_thermostat;

    @Column(name = "last_update")
    private LocalDateTime last_update;

    @ManyToOne(fetch = FetchType.LAZY)
    @NonNull
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "terrarium", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Reading> readingList;

    // public Integer getId() {
    //     return id;
    // }

    // public void setId(Integer id) {
    //     this.id = id;
    // }

    // public String getName() {
    //     return name;
    // }

    // public void setName(String name) {
    //     this.name = name;
    // }

    // public Integer getTemperature_goal() {
    //     return temperature_goal;
    // }

    // public void setTemperature_goal(Integer temperature_goal) {
    //     this.temperature_goal = temperature_goal;
    // }

    // public Integer getHumidity_goal() {
    //     return humidity_goal;
    // }

    // public void setHumidity_goal(Integer humidity_goal) {
    //     this.humidity_goal = humidity_goal;
    // }

    // public Integer getMax_temp() {
    //     return max_temp;
    // }

    // public void setMax_temp(Integer max_temp) {
    //     this.max_temp = max_temp;
    // }

    // public Integer getMin_temp() {
    //     return min_temp;
    // }

    // public void setMin_temp(Integer min_temp) {
    //     this.min_temp = min_temp;
    // }

    // public Integer getMax_hum() {
    //     return max_hum;
    // }

    // public void setMax_hum(Integer max_hum) {
    //     this.max_hum = max_hum;
    // }

    // public Integer getMin_hum() {
    //     return min_hum;
    // }

    // public void setMin_hum(Integer min_hum) {
    //     this.min_hum = min_hum;
    // }

    // public User getUser() {
    //     return user;
    // }

    // public void setUser(User user) {
    //     this.user = user;
    // }

    // public List<Reading> getReadingList() {
    //     return readingList;
    // }

    // public void setReadingList(List<Reading> readingList) {
    //     this.readingList = readingList;
    // }

    // public Time getWater_time(){
    //     return water_time;
    // }

    // public void setWater_time(Time water_time){
    //     this.water_time = water_time;
    // }

    // public Integer getWaterPeriod(){
    //     return water_period;
    // }

    // public void setWater_period(Integer water_period){
    //     this.water_period = water_period;
    // }

    // public Float getCurrent_temp1(){
    //     return current_temp_1;
    // }

    // public void setCurrent_temp1(Float current_temp1){
    //     this.current_temp_1 = current_temp1;
    // }

    // public Float getCurrent_temp2(){
    //     return current_temp_2;
    // }

    // public void setCurrent_temp2(Float current_temp2){
    //     this.current_temp_2 = current_temp2;
    // }

}
