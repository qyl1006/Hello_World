package cn.wolfcode.wms.query;

import cn.wolfcode.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter
public class StockOutComeBillQueryObject extends QueryObject {
    //业务时间
    //开始日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;

    //结束日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    //仓库ID
    private Long depotId = -1L;
    //客户
    private Long ClientId = -1L;
    //订单状态
    private int status = -1;

    //使用工具类的方法  把结束日期设置为当天的23:59:59 这样合理点
    public Date getEndDate(){
        return DateUtil.getEndDate(endDate);
    }


}
