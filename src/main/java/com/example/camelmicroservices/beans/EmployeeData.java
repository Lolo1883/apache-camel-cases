package com.example.camelmicroservices.beans;

import java.util.Objects;

public class EmployeeData {
  private String name;
  private int salary;

  private String to;

  private Long id;

  public EmployeeData(String name, int salary, String to, boolean married, long id) {
    super();
    this.name = name;
    this.salary = salary;
    this.married = married;
    this.id = id;
    this.to = to;
  }

  public EmployeeData(){

  }

  public String getName() {
    return name;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getSalary() {
    return salary;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EmployeeData that = (EmployeeData) o;
    return salary == that.salary && married == that.married && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, salary, married);
  }

  public boolean isMarried() {
    return married;
  }

  public void setMarried(boolean married) {
    this.married = married;
  }

  private boolean married;

  @Override
  public String toString() {
    return "EmployeeData{" +
        "name='" + name + '\'' +
        ", salary=" + salary +
        ", married=" + married +
        '}';
  }
}