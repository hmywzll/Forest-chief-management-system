package com.forestchiefmanagementsystem.pojo.dto;

import com.forestchiefmanagementsystem.pojo.PersonalInformation;
import com.forestchiefmanagementsystem.pojo.Task;
import lombok.Data;

@Data
public class TaskPI {
    Task task;
    PersonalInformation personalInformation;
}
