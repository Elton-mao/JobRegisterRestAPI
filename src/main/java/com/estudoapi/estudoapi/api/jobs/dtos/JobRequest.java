package com.estudoapi.estudoapi.api.jobs.dtos;

import java.math.BigDecimal;
import java.util.List;

import com.estudoapi.estudoapi.core.enums.JobLevel;
import com.estudoapi.estudoapi.core.enums.JobType;
import com.estudoapi.estudoapi.core.models.Skill;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JobRequest {

    @NotEmpty
    @Size(min = 3, max = 100)
    private String title;

    @NotEmpty
    @Size(min = 3, max = 500)
    private String description;

    @NotEmpty
    @Size(min = 3, max = 100)
    private String company;

    @NotEmpty
    @Size(min = 3, max = 100)
    private String location;

    @NotNull
    private JobType jobType;

    @NotNull
    private JobLevel jobLevel;

    @Positive
    private BigDecimal salary;
    
    @NotNull
    private List<Skill> skills;
}
