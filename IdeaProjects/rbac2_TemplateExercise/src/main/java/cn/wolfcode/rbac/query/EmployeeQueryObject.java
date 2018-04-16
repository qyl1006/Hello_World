package cn.wolfcode.rbac.query;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class EmployeeQueryObject extends QueryObject {
    //高级查询
    // name 和 email  作为关键字
    private String keyword;

    //所属部门ID
    private Integer deptId;

    public String getKeyword(){
        return Emrty2null(keyword);
    }
}
