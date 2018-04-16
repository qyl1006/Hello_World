package cn.wolfcode.wms.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

//基本分页类
@Setter @Getter
public class QueryObject {
    private int currentPage = 1;
    private int pageSize = 3;

    public int getStart(){
        return (currentPage - 1) * pageSize;
    }

    //空字符串转null
    public String empty2null(String src){
        return StringUtils.hasLength(src) ? src : null;
    }
}
