package cn.wolfcode.wms.query;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class EmployeeQueryObject extends QueryObject {
    private String keyword; // 姓名/邮箱
    private Long deptId = -1L; //部门编号

    public String getKeyword() {
        return empty2Null(keyword);
    }
}
