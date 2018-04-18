package cn.wolfcode.wms.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter @Getter
public class StockInComeBillQueryObject extends QueryObject {
   //业务时间
    //开始日期'yyyy-MM-dd'
   @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date beginDate;

   //结束日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    //仓库
    private int depotId;

    //订单状态
    private int status = -1;

}
