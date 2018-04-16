package cn.wolfcode.wms.query;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmployeeQueryObject extends QueryObject {
    //关键字 name/email
    private String keyword;
    //部门ID
    private int deptId;

    public String getKeyword(){
        return empty2null(keyword);
    }

}
