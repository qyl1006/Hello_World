package cn.wolfcode.wms.query;

import cn.wolfcode.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter
public class OrderBillQueryObject extends QueryObject {
    //业务时间
    //开始日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;

    //结束日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    //供应商
    private Long supplierId = -1L;

    //订单状态
    private int status = -1;

    //使用工具类的方法  把结束日期设置为当天的23:59:59 这样合理点
    public Date getEndDate(){
        return DateUtil.getEndDate(endDate);
    }


}
