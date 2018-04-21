package cn.wolfcode.wms.util;

import java.util.HashMap;
import java.util.Map;

public abstract class MyDictionary {
    public static final Map<String, String> SALE_MAP= new HashMap<>();

    static {
        SALE_MAP.put("e.name", "销售员");
        SALE_MAP.put("p.name", "商品名称");
        SALE_MAP.put("p.brandName", "商品品牌");
        SALE_MAP.put("cname", "客户");
        SALE_MAP.put("DATE_FORMAT(s.vdate,'%Y-%m')", "销售月份");
        SALE_MAP.put("DATE_FORMAT(s.vdate,'%Y-%m-%d')", "销售日期");
    }
}
