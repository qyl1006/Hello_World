package cn.wolfcode.wms.query;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ProductStockQueryObject extends QueryObject {
   //关键字 商品名称 / 编码
    private String keyword;

    //仓库ID
    private int depotId;

    //品牌ID
    private int brandId;

    public String getKeyword(){
        return empty2null(keyword);
    }
}
