package cn.wolfcode.wms.query;

import cn.wolfcode.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter @Getter
public class OrderChartQueryObject extends QueryObject {
   //业务时间
    //开始日期'yyyy-MM-dd'
   @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date beginDate;

   //结束日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    //关键字
    private String keyword;
    //供应商id
    private Long supplierId = -1L;
    //品牌ID
    private Long brandId = -1L;
    //分组类型
    private String groupType = "e.name";  //默认

    public String getKeyword(){
        return empty2null(keyword);
    }

    public Date getEndDate(){
        return DateUtil.getEndDate(endDate);
    }

}
