package com.forestchiefmanagementsystem.pojo.dto;

import com.forestchiefmanagementsystem.pojo.Patrol;
import com.forestchiefmanagementsystem.pojo.PersonalInformation;
import lombok.Data;

@Data
public class PatrolPI {
    Patrol patrol;
    PersonalInformation personalInformation;
}
