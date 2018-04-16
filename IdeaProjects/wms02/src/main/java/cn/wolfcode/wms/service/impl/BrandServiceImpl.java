package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Brand;
import cn.wolfcode.wms.mapper.BrandMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements IBrandService {
    @Autowired
    private BrandMapper brandMapper;


    @Override
    public void insertOrUpdate(Brand entity) {
        if (entity.getId() == null) {
            brandMapper.insert(entity);
        }else{
            brandMapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public void deleteById(Long id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Brand getById(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult queryAll(QueryObject qo) {
        int count = brandMapper.queryCount(qo);
        if(count == 0 ){
            return PageResult.EMPTY_PAGE;
        }
        List<Brand> data = brandMapper.queryAll(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),count,data);
    }


    //查所有品牌给商品表用
    @Override
    public List<Brand> listAll() {
        return brandMapper.listAll();
    }


}
