package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

@Getter @Setter
public class Product extends BaseDomain{
    //商品名字
    private String name;
    //商品编码
    private String sn;
    //成本价
    private BigDecimal costPrice;
    //销售价
    private BigDecimal salePrice;
    //商品简介
    private String intro;
    //图片服务器路径
    private String imagePath;

    //为了使用时方便,少发sql去单独查询 打破第三范式
    //品牌ID
    private Long BrandId;
    //品牌名字
    private String brandName;

    public String getSmallImage(){
        if(StringUtils.hasLength(imagePath)){
            int i = imagePath.lastIndexOf('.');
            String filename = imagePath.substring(0, i);
            String ext = imagePath.substring(i);
            return filename + "_small" + ext;
        }
        return  null;
    }

}