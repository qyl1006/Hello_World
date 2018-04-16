package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Test;
import java.util.List;

public interface TestMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Test record);

    Test selectByPrimaryKey(Long id);

    List<Test> selectAll();

    int updateByPrimaryKey(Test record);
}