package com.example.camelmicroservices.beans;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmployeeDataProcessor{
   public void transformSalaryTransform(EmployeeData employeeData){
     employeeData.setSalary(employeeData.getSalary() * 1000);
     log.info("Message Processed: " + employeeData.toString());
   }
}
