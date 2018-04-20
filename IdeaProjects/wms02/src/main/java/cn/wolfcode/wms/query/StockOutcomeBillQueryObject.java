package cn.wolfcode.wms.query;

import cn.wolfcode.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter @Getter
public class StockOutcomeBillQueryObject extends QueryObject {
   //业务时间
    //开始日期'yyyy-MM-dd'
   @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date beginDate;

   //结束日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    //客户
    private int clientId;

    //订单状态
    private int status = -1;

    public Date getEndDate(){
        return DateUtil.getEndDate(endDate);
    }

}
