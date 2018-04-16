package cn.wolfcode.rbac.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Role extends base_domain{
    private String name;
    private String sn;

    //many-many 主导方
    private List<Permission> permissions;
}
