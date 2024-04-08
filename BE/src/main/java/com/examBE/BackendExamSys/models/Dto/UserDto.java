package com.examBE.BackendExamSys.models.Dto;

import com.examBE.BackendExamSys.utils.Constants;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String name;
    private Boolean gender;
    private String email;
    private String password;
    private String roles;
}
