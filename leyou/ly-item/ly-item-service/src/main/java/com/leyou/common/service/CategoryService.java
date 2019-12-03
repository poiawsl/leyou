package com.leyou.common.service;

import com.leyou.common.Exceptions.LyException;
import com.leyou.common.eEnum.ExceptionEnum;
import com.leyou.common.mapper.CategoryMapper;
import common.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查找分类信息
     * @param pid
     * @return
     */
    public ResponseEntity<List<Category>> queryByParentId(Long pid){

        Category category = new Category();
        category.setParentId(pid);

        List<Category> list = categoryMapper.select(category);
        if(list == null || list.size()<1){
            throw new LyException(ExceptionEnum.CATEGORY_CANNOT_BE_NULL);
        }
        return ResponseEntity.ok(list);
    }


    public ResponseEntity<List<Category>> all() {
        return ResponseEntity.ok(categoryMapper.selectAll());
    }

    /**
     * 根据Brand id查询商品分类
     * 回显商品分类
     * @param bid
     * @return
     */
    public List<Category> queryByBrandId(Long bid) {
        return categoryMapper.queryByBrandId(bid);
    }


    public List<Category> queryByIds(List<Long> ids) {
        return categoryMapper.selectByIdList(ids);
    }


    public List<String> queryNameByIds(List<Long> ids){
        return this.categoryMapper.selectByIdList(ids).stream().map(Category::getName).collect(Collectors.toList());
    }
}
