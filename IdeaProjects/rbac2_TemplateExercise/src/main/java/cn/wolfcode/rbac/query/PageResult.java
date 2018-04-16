package cn.wolfcode.rbac.query;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

//页面结果
@Getter@Setter
public class PageResult {
    public static final PageResult EMPTY_PAGE = new PageResult(1,1,0, Collections.EMPTY_LIST);

    //用户输入的
    private int currentPage;
    private int pageSize;

    //通过数据库查询
    private Integer totalCount;
    private List<?> data;

    //计算
    private int endPage;
    private int prevPage;
    private int nextPage;

    public PageResult(int currentPage, int pageSize, Integer totalCount, List<?> data) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.data = data;

        if(totalCount <= pageSize){
            this.endPage = 1;
            this.prevPage = 1;
            this.nextPage = 1;
            return;
        }

        endPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        prevPage = currentPage > 2 ? currentPage - 1 : 1;
        nextPage = currentPage < endPage ? currentPage + 1 : endPage;
    }
}
