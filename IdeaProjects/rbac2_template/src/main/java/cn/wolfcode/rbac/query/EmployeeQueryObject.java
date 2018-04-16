package cn.wolfcode.rbac.query;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmployeeQueryObject extends QueryObject {
    private String keyword;
    private Integer deptId = -1;

    public String getKeyword(){
        return empy2Null(keyword);
    }

}
