package cn.wolfcode.wms.query;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter @Getter
public class ProductStockQueryObject extends QueryObject {
   //关键字 商品名称 / 编码
    private String keyword;

    //仓库ID
    private int depotId = 1;

    //品牌ID
    private int brandId = -1;

    //库存阈值
    private BigDecimal warnNum;

    public String getKeyword(){
        return empty2null(keyword);
    }


}
