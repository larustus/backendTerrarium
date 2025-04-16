package com.example.terrariumappbackend.DTO;

import java.sql.Time;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TerrariumRaspberry {
    private Integer id;
    private Float temperature_goal;
    private Float humidity_goal;
    private Float max_temp;
    private Float min_temp;
    private Float max_hum;
    private Float min_hum;
    private Time water_time;
    private Integer water_period;
}
