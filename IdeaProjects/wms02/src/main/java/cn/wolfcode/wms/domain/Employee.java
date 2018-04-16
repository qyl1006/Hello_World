package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Employee extends BaseDomain{
    private String name;

    private String password;

    private String email;

    private Integer age;

    private boolean admin;

    //many-one
    private Department dept;

    //many-many
    private List<Role> roles = new ArrayList<>();




}