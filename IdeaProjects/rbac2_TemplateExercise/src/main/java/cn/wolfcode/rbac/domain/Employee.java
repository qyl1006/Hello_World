package cn.wolfcode.rbac.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Employee extends base_domain{
    private String name;
    private String password;
    private String email;
    private Integer age;
    private boolean admin;

    //many-one  主导方
    private Department dept;

    //many-many 主导方
    private List<Role> roles;

}
