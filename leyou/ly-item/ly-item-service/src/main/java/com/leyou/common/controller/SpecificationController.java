package com.leyou.common.controller;


import com.leyou.common.service.SpecificationService;
import common.leyou.item.pojo.SpecGroup;
import common.leyou.item.pojo.SpecParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("spec")
public class SpecificationController {

    @Resource
    private SpecificationService specificationService;

    /**
     * 根据分类id查询分组
     * @param cid
     * @return
     */
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupByCid(@PathVariable("cid") Long cid) {
        return ResponseEntity.ok(specificationService.queryGroupByCid(cid));
    }


    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> querParamByGid(
            @RequestParam(value = "gid",required = false) Long gid,
            @RequestParam(value = "searching",required = false) Boolean searching,
            @RequestParam(value = "cid",required = false) Long cid){
        return ResponseEntity.ok(specificationService.queryParamByGid(gid,cid,searching));
    }

}
