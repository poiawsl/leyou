package com.leyou.common.controller;


import com.leyou.common.pojo.PageResult;
import com.leyou.common.service.BrandService;
import common.leyou.item.pojo.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 分页显示
     * @param page   当期页码
     * @param rows   每页条数
     * @param sortBy 排序字段
     * @param desc   升序降序
     * @param key    搜索条件
     * @return
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryByParentPage(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "rows",defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy",required = false) String sortBy,
            @RequestParam(value = "desc",defaultValue = "false") Boolean desc,
            @RequestParam(value = "key",required = false) String key
    ){
        return ResponseEntity.ok(brandService.queryByParentPage(page,rows,sortBy,desc,key));
    }


    /**+
     * 添加品牌信息
     * @param brand
     * @param cids
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids")List<Long> cids){
        brandService.saveBrand(brand,cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 修改品牌信息
     * @param brand
     * @param cids
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateBrand(Brand brand, @RequestParam("cids")List<Long> cids){
        System.out.println("brand"+brand);
        System.out.println("cids"+cids);

        brandService.updateBrand(brand,cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 删除品牌
     * @param id
     * @return
     */
    @GetMapping("delete")
    public ResponseEntity<Void> deleteBrand(@RequestParam("id")Long id){
        brandService.deleteBrand(id);
        return ResponseEntity.status(HttpStatus.MULTI_STATUS).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Brand> queryBrandById(@PathVariable Long id){
        return ResponseEntity.ok(brandService.queryById(id));
    }

    /**
     * 根据cid查询品牌
     * @param cid
     * @return
     */
    @GetMapping("/cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandByCid(@PathVariable("cid") Long cid){
        return ResponseEntity.ok(brandService.queryBrandByCid(cid));
    }


}
