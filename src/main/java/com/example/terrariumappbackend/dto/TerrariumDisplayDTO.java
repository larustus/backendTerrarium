package com.example.terrariumappbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TerrariumDisplayDTO {
    private Integer id;
    private String name;
    private Integer temperature_goal;
    private Integer humidity_goal;
    private Integer max_temp;
    private Integer min_temp;
    private Integer max_hum;
    private Integer min_hum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTemperature_goal() {
        return temperature_goal;
    }

    public void setTemperature_goal(Integer temperature_goal) {
        this.temperature_goal = temperature_goal;
    }

    public Integer getHumidity_goal() {
        return humidity_goal;
    }

    public void setHumidity_goal(Integer humidity_goal) {
        this.humidity_goal = humidity_goal;
    }

    public Integer getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(Integer max_temp) {
        this.max_temp = max_temp;
    }

    public Integer getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(Integer min_temp) {
        this.min_temp = min_temp;
    }

    public Integer getMax_hum() {
        return max_hum;
    }

    public void setMax_hum(Integer max_hum) {
        this.max_hum = max_hum;
    }

    public Integer getMin_hum() {
        return min_hum;
    }

    public void setMin_hum(Integer min_hum) {
        this.min_hum = min_hum;
    }
}

