package cn.wolfcode.rbac.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter@Setter
//一个基本的分页类
public class QueryObject {
    private int currentPage = 1;
    private int pageSize = 3;

    public int getStart(){
        return (currentPage - 1) * pageSize;
    }

    //把空白字符串转为null
    public String Emrty2null(String str){
        return StringUtils.hasLength(str) ? str : null;
    }
}
