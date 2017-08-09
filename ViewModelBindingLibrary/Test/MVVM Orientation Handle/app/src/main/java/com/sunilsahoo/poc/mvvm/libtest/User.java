package com.sunilsahoo.poc.mvvm.libtest;

import com.sunilsahoo.viewmodelbinding.common.ViewModel;


/**
 * Created by sunilkumarsahoo on 12/19/16.
 */

public class User extends ViewModel {
    private String name;
    private String id;
    private String salary;

    public User(int variableId) {
        super(variableId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
