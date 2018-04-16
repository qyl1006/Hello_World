package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter@Getter
public class Product extends BaseDomain {
    private String name; //商品名称
    private String sn; //商品编码
    private BigDecimal costPrice; //成本价
    private BigDecimal salePrice; //零售价
    private String imagePath; //图片在服务器中的路径
    private String intro; //商品简介
    //打破第三范式
    private Long brandId; //品牌ID
    private String brandName; //品牌名称
}
