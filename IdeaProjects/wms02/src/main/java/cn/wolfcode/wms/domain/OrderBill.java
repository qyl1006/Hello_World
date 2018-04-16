package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.patterns.PerFromSuper;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter @Getter
public class OrderBill extends BaseDomain{
    public static final Integer NORMAL = 1; //未审核状态
    public static final Integer AUDIT= 2; //审核状态

    private String sn; //采购单号
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date vdate; //业务时间
    private Integer status = NORMAL; //单据状态
    private BigDecimal totalAmount; //采购金额
    private BigDecimal totalNumber; //采购总数

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date auditTime; //审核时间
     @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputTime; //录入时间

    private Employee inputUser; //录入人
    private Employee auditor; //审核人
    private Supplier supplier; //供应商

    //包含的采购明细 组合关系
    private List<OrderBillItem> items = new ArrayList<>();




}
