package cn.wolfcode.rbac.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Permission extends base_domain{
    private String name;
    private String expression;
}
