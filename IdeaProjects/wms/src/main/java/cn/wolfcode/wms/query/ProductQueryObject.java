package cn.wolfcode.wms.query;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductQueryObject extends QueryObject {
    //关键字 name/sn
    private String keyword;
    //商品品牌ID
    private Long brandId = -1L;

    public String getKeyword(){
        return empty2null(keyword);
    }

}
