package com.example.terrariumappbackend.DTO;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReadingDTO {
    private Integer id;
    private Date date;
    private Integer temperature_1;
    private Integer temperature_2;
    private Integer humidity;
    private Integer terrarium_id;
    private Integer hour;

    // public Integer getId() {
    //     return id;
    // }

    // public void setId(Integer id) {
    //     this.id = id;
    // }

    // public Timestamp getDate() {
    //     return date;
    // }

    // public void setDate(Timestamp date) {
    //     this.date = date;
    // }

    // public Integer getTemperature_1() {
    //     return temperature_1;
    // }

    // public void setTemperature_1(Integer temperature_1) {
    //     this.temperature_1 = temperature_1;
    // }

    // public Integer getTemperature_2() {
    //     return temperature_2;
    // }

    // public void setTemperature_2(Integer temperature_2) {
    //     this.temperature_2 = temperature_2;
    // }

    // public Integer getHumidity() {
    //     return humidity;
    // }

    // public void setHumidity(Integer humidity) {
    //     this.humidity = humidity;
    // }

    // public Integer getTerrarium_id() {return terrarium_id;}

    // public void setTerrarium_id(Integer terrarium_id) {this.terrarium_id = terrarium_id;}

}
