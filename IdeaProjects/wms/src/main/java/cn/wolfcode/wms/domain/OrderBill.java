package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//采购单/订单
@Getter @Setter
public class OrderBill extends BaseDomain{
    //未审核状态
    public static final Integer NORMAL = 1;
    //审核状态
    public static final Integer AUDIT = 2;

    //采购单号
    private String sn;
    //业务时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date vdate;

    //单据状态
    private Integer status = NORMAL;
    //采购总金额
    private BigDecimal totalAmount;
    //采购总数量
    private BigDecimal totalNumber;

    //审核时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date auditTime;
    //录入时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputTime;

    //录入人
    private Employee inputUser;
    //审核人
    private Employee auditor;
    //供应商
    private Supplier supplier;

    //订单中的明细
    private List<OrderBillItem> items = new ArrayList<>();


}