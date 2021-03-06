package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

//采购订单中的明细---
@Getter @Setter
public class OrderBillItem extends BaseDomain{
    //成本价
    private BigDecimal costPrice;
    //采购数量
    private BigDecimal number;
    //明细小计
    private BigDecimal amount;
    //备注
    private String remark;

    //采购的商品
    private Product product;

    //当前订单的ID
    private Long billId;


}