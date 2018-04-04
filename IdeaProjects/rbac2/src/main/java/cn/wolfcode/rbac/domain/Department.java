package cn.wolfcode.rbac.domain;

import lombok.*;

/**
 * @author Qyuelin
 */
@Setter @Getter @AllArgsConstructor @NoArgsConstructor @ToString
public class Department extends Base_domain {
//    private Long id;
    private String name;
    private String sn;
}
