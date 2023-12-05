package com.collegedata.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {
    private String email;
    private String jwtToken;
}
