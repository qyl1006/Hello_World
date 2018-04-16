package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter @Getter
public class Employee extends BaseDomain {
    private String name;

    private String password;

    private String email;

    private Integer age;

    private boolean admin;

    //many-one关系
    private Department dept;

    //many-many关系
    private List<Role> roles = new ArrayList<>();


}