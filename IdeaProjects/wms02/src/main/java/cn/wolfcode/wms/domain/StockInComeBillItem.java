package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter @Getter
public class StockInComeBillItem extends BaseDomain{
    private BigDecimal costPrice; //成本价
    private BigDecimal number; //采购数量
    private BigDecimal amount; //明细小计
    private String remark; //备注

    private Product product; //采购商品
    private Long billId;      //订单ID
}
