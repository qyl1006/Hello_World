package cn.wolfcode.wms.query;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ProductQueryObject extends QueryObject {
   //关键字 商品名称/编码
    private String keyword;

    //商品品牌 D
    private int brandId = -1;

    public String getKeyword(){
        return empty2null(keyword);
    }
}
