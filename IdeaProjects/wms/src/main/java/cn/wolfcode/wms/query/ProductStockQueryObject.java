package cn.wolfcode.wms.query;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductStockQueryObject extends QueryObject {
    //关键字 商品名/编码
    private String keyword;
    //出库ID
    private Long depotId = -1L;
    //品牌ID
    private Long brandId = -1L;

    //库存值
    private Long warnNum;

    public String getKeyword(){
        return empty2null(keyword);
    }

}
