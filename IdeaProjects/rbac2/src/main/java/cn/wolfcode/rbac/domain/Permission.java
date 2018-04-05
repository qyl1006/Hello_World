package cn.wolfcode.rbac.domain;

import lombok.*;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor @ToString
public class Permission extends Base_domain{
//    private Long id;
    private String name;
    //权限表达式
    private String expression;
}
