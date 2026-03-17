package com.forestchiefmanagementsystem.pojo.dto;

import com.forestchiefmanagementsystem.pojo.Patrol;
import com.forestchiefmanagementsystem.pojo.PersonalInformation;
import lombok.Data;

@Data
public class PatrolDto extends Patrol {

    private Integer patrolCount;
    private Long timeCount;
    private Integer mileageCount;
    private PersonalInformation pI;
}
