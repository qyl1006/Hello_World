package cn.wolfcode.rbac.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Employee extends Base_domain {
//    private Long id;
    private String name;
    private String password;
    private String email;
    private Integer age;
    private boolean admin;

    private Department dept;

    //many-many关系  单向--查询角色
    private List<Role> roles = new ArrayList<>();
}
