package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

//销售账单 流水
@Setter @Getter
public class SaleAccount extends BaseDomain {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date vdate; //记账时间
    private BigDecimal number; //数量
    private BigDecimal costPrice; //成本价
    private BigDecimal costAmount; //成本总额
    private BigDecimal salePrice; //销售价
    private BigDecimal saleAmount; //销售总额

    private Product product; //销售商品
    private Employee saleman; //业务员
    private Client client; //客户
}
