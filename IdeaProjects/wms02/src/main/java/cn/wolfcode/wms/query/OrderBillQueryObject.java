package cn.wolfcode.wms.query;

import cn.wolfcode.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.regex.Pattern;

@Setter @Getter
public class OrderBillQueryObject extends QueryObject {
   //业务时间
    //开始日期'yyyy-MM-dd'
   @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date beginDate;

   //结束日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    //供应商
    private int supplierId;

    //订单状态
    private int status = -1;

    public Date getEndDate(){
        return DateUtil.getEndDate(endDate);
    }

}
