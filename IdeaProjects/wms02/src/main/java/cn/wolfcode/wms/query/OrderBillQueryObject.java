package cn.wolfcode.wms.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.regex.Pattern;

@Setter @Getter
public class OrderBillQueryObject extends QueryObject {
   //业务时间
    //开始日期
   @DateTimeFormat(pattern = "yyyy-MM-dd")
    private BigDecimal beginDate;

   //结束日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private BigDecimal endDate;

    //供应商
    private int supplierId;

    //订单状态
    private int status;

}
