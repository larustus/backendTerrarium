package com.example.terrariumappbackend.DTO;

import java.sql.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReadingHourlyDTO {
    private int hour;
    private Integer temperature1;
    private Integer temperature2;
    private Integer humidity;

    public ReadingHourlyDTO(int hour, Integer temperature1, Integer temperature2, Integer humidity){
        this.hour = hour;
        this.temperature1 = temperature1;
        this.temperature2 = temperature2;
        this.humidity = humidity;
    }


}
