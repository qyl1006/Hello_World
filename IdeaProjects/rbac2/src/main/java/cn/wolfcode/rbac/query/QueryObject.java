package cn.wolfcode.rbac.query;

import lombok.*;
import org.springframework.util.StringUtils;

@Setter @Getter @ToString @AllArgsConstructor @NoArgsConstructor @Data
public class QueryObject {
    private int currentPage = 1;
    private int pageSize = 3;

    /**分页limlit使用
     * @return int
     */
    public int getStart(){
        return (currentPage - 1) * pageSize;
    }

    /**调用Spring内置的方法---空的转为null
     * @param str String
     * @return 空的转为null
     */
    public String empy2Null(String str){
        return StringUtils.hasLength(str) ? str : null;
    }

}
