package com.shiyue.springboot.domain;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor


public class School {


    private Integer classNo;

    private Integer numOfPer;

    private Integer numOfTeacher;

    private String schoolName;

    private BigDecimal teachingFunds;

}
