package com.example.camelmicroservices.rest;


import com.example.camelmicroservices.beans.EmployeeData;
import java.util.Random;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeDataController {
  @GetMapping("/employee/data/{name}")
  public EmployeeData getInfo(@PathVariable String name) {

    EmployeeData employeeData = new EmployeeData();
    employeeData.setName(name + "-" + UUID.randomUUID());
    employeeData.setSalary(new Random().nextInt((200000 - 100000) + 1) + 100000);
    employeeData.setMarried(Math.random() < 0.5);

    return employeeData;
  }
}
