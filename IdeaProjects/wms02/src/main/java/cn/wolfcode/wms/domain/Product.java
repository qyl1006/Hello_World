package cn.wolfcode.wms.domain;

import cn.wolfcode.wms.util.JSONUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Setter @Getter
public class Product extends BaseDomain{
    private String name;
    private String sn;
    private BigDecimal costPrice; //成本价
    private BigDecimal salePrice; //零售价
    private String imagePath; //图片在服务器中的路径
    private String intro; //商品简介

    //打破第三范式
    private Long brandId;   //品牌ID
    private String brandName; //品牌名称


    public String getSmallImage(){
        if(StringUtils.hasLength(imagePath)){
            int i = imagePath.lastIndexOf('.');
            String filename = imagePath.substring(0, i);
            String ext = imagePath.substring(i);
            return filename + "_small" + ext;
        }
        return  null;
    }

    //把当前对象中的部分信息转为JSON
    public String getJsonString(){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("costPrice", costPrice);
        map.put("salePrice", salePrice);
        map.put("brandName", brandName);

        return JSONUtil.toJSONString(map);
    }

}
