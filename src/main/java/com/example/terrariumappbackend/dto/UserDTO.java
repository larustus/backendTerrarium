package com.example.terrariumappbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String username;
    private String password_hash;
    private List<TerrariumDTO> terrariumData;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public List<TerrariumDTO> getTerrariumData() {
        return terrariumData;
    }

    public void setTerrariumData(List<TerrariumDTO> terrariumData) {
        this.terrariumData = terrariumData;
    }
}
