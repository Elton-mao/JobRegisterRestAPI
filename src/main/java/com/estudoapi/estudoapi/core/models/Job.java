package com.estudoapi.estudoapi.core.models;

import java.math.BigDecimal;
import java.util.List;

import com.estudoapi.estudoapi.core.enums.JobLevel;
import com.estudoapi.estudoapi.core.enums.JobType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @EqualsAndHashCode.Include
    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 50)
    private String company;

    @Column(nullable = false, length = 100)
    private String location;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private JobLevel jobLevel;

    @Column(nullable = false, scale = 2 )
    private BigDecimal salary;
    @ManyToMany
    private List<Skill> skills;
}
