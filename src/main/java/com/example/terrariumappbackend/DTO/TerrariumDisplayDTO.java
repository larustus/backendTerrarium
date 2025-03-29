package com.example.terrariumappbackend.DTO;

import java.sql.Time;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TerrariumDisplayDTO {
    private Integer id;
    private String name;
    private Float temperature_goal;
    private Float humidity_goal;
    private Float max_temp;
    private Float min_temp;
    private Float max_hum;
    private Float min_hum;
    private Time water_time;
    private Integer water_period;
    private Float current_temp1;
    private Float current_temp2;
    private Float current_hum;
    private Float temperature_thermostat;
    private Integer user_id;
    private LocalDateTime last_update;

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

    // public Integer getWater_time(){
    //     return water_time;
    // }

    // public void setWater_time(Integer water_time){
    //     this.water_time = water_time;
    // }

    // public Integer getWater_period(){
    //     return water_period;
    // }

    // public void setWater_period(Integer water_period){
    //     this.water_period = water_period;
    // }

    // public Time getDay_start(){
    //     return day_start;
    // }

    // public void setDay_start(Time day_start){
    //     this.day_start = day_start;
    // }

    // public Time getDay_end(){
    //     return day_end;
    // }

    // public void setDay_end(Time day_end){
    //     this.day_end = day_end;
    // }
}
