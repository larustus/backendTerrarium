package com.example.terrariumappbackend.DTO;

import java.sql.Time;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddAlarmDTO {
    private Time start_time;
    private String type;
    private Integer terrarium_id;
    private Float highest_offshoot;
    private boolean isActive;
}
