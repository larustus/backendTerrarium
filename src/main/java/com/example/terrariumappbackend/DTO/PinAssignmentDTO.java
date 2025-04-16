package com.example.terrariumappbackend.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PinAssignmentDTO {
    private Integer terrarium_id;
    private Integer pwm_pin;
    private Integer t1_pin;
    private Integer t2_pin;
    private Integer probe_pin;
    private Integer water_pin;
}
