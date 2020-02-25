package com.xinwu.datastructure.chapter5.separateChaining;

public class Employee {
    private String name;
    private Double salary;
    private int seniority;

    @Override
    public int hashCode() {
        //此处直接使用name（String）的hashCode方法
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Employee && ((Employee) obj).name.equals(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }
}