package com.leyou.common.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.Exceptions.LyException;
import com.leyou.common.eEnum.ExceptionEnum;
import com.leyou.common.mapper.BrandMapper;
import com.leyou.common.pojo.PageResult;
import common.leyou.item.pojo.Brand;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;


    public ResponseEntity<List<Brand>> queryByParentId() {
        List<Brand> list = brandMapper.selectAll();

        if (list == null || list.size() < 1) {
            throw new LyException(ExceptionEnum.CATEGORY_CANNOT_BE_NULL);
        }
        return ResponseEntity.ok(list);
    }

    /**
     * 分页
     * @param page    当前页码
     * @param rows    每页条数
     * @param sortBy  排序字段
     * @param desc    升序降序
     * @param key     搜索条件
     * @return
     */
    public PageResult<Brand> queryByParentPage(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        //分页
        PageHelper.startPage(page, rows);
        /*
         * WHERE 'name' LIKE "%x%" OR letter =='x'
         * ORDER BY id DESC
         */
        //过滤
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        //过滤条件
        if (StringUtils.isNotBlank(key)) {
            if (key.matches("[a-zA-Z]+") && key.length() == 1) {
                criteria.orEqualTo("letter", key.toUpperCase());
            } else {
                criteria.andLike("name", "%" + key + "%");
            }
        }
        //排序
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));

        }
        //查询
        List<Brand> list = brandMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            throw new LyException(ExceptionEnum.BRAND_CANNOT_BE_NULL);
        }
        //解析分页结果
        PageInfo<Brand> info = new PageInfo<>(list);
        return new PageResult<>(info.getTotal(), list);
    }


    /**
     * 添加品牌信息 中间表
     * @param brand
     * @param cids
     */
    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {
        //新增品牌
        int count = brandMapper.insert(brand);
        if (count != 1) {
            throw new LyException(ExceptionEnum.BRAND_SAVE_ERROR);
        }
        addCategoryBrand(brand.getId(), cids);
    }

    /**
     * 添加中间表
     * @param bid
     * @param cids
     */
    public void addCategoryBrand(Long bid, List<Long> cids) {
        int count = 0;
        for (Long cid : cids) {
            count = brandMapper.insertCategoryBrand(cid, bid);
            if (count != 1) {
                throw new LyException(ExceptionEnum.BRAND_SAVE_ERROR);
            }
        }
    }

    /**
     * 修改品牌
     * @param brand
     * @param cids
     */
    @Transactional
    public void updateBrand(Brand brand, List<Long> cids) {
        //修改品牌
        int count = brandMapper.updateByPrimaryKey(brand);
        if (count != 1) {
            throw new LyException(ExceptionEnum.BRAND_SAVE_ERROR);
        }

        count = brandMapper.deleteByCategoryBrand(brand.getId());
        addCategoryBrand(brand.getId(), cids);

    }


    @Transactional
    public void deleteBrand(Long id) {
        if(id == null){
            throw new LyException(ExceptionEnum.BRAND_DELETE_ERROR);
        }
        brandMapper.deleteByPrimaryKey(id);
        brandMapper.deleteByCategoryBrand(id);
    }

    public Brand queryById(Long id) {

        return brandMapper.selectByPrimaryKey(id);
    }

    public List<Brand> queryBrandByCid(Long cid) {
        List<Brand> list = brandMapper.queryByCatoryId(cid);
        for (Brand brand: list){
            System.out.println(brand);
        }

        if(CollectionUtils.isEmpty(list)){
            throw  new LyException(ExceptionEnum.BRAND_NOT_FOND);
        }
        return list;
    }

}
