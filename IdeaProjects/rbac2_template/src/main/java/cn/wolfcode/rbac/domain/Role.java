package cn.wolfcode.rbac.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor @ToString
//角色domain
public class Role extends Base_domain {
//    private Long id;
    private String name;

    //角色编码
    private String sn;

    //mony-mong role-permission role可以查询permission
    List<Permission> permissions = new ArrayList<>();

}
