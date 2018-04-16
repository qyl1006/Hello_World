package cn.wolfcode.wms.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

@Setter @Getter
public class PageResult{
    //空白页
    public static final PageResult EMPTY_PAGE = new PageResult(1,1,0, Collections.EMPTY_LIST);

    private int currentPage;
    private int pageSize;

    //查数据库
    private int pageCount;
    private List<?> data;

    //计算
    private int endPage;
    private int prevPage;
    private int nextPage;

    public PageResult(int currentPage, int pageSize, int pageCount, List<?> data) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
        this.data = data;

        if(pageCount <= pageSize){
            endPage = 1;
            prevPage = 1;
            nextPage = 1;
            return;
        }

        endPage = pageCount % pageSize == 0 ? pageCount / pageSize : pageCount / pageSize + 1;
        prevPage = currentPage > 2 ? currentPage - 1 : 1;
        nextPage = currentPage < endPage ? currentPage + 1 : endPage;
    }
}
