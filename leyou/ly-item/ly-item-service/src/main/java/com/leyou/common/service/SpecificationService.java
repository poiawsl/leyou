package com.leyou.common.service;

import com.leyou.common.Exceptions.LyException;
import com.leyou.common.eEnum.ExceptionEnum;
import com.leyou.common.mapper.SpecGroupMapper;
import com.leyou.common.mapper.SpecParamMapper;
import common.leyou.item.pojo.SpecGroup;
import common.leyou.item.pojo.SpecParam;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SpecificationService {

    @Resource
    private SpecGroupMapper specGroupMapper;

    @Resource
    private SpecParamMapper specParampMapper;


    /**
     * 根据属性id查询属性
     * @param cid
     * @return
     */
    public List<SpecGroup> queryGroupByCid(Long cid){

        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        List<SpecGroup> specGroups = specGroupMapper.select(specGroup);

        if(CollectionUtils.isEmpty(specGroups)){
            throw new LyException(ExceptionEnum.SPECGROUNP_NOT_FOND);
        }

        return specGroups;
    }

    /**
     * 根据属性id查询属性详情
     * @param gid
     * @return
     */
    public List<SpecParam> queryParamByGid(Long gid, Long cid, Boolean searching) {
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        specParam.setCid(cid);
        specParam.setSearching(searching);
        List<SpecParam> list = specParampMapper.select(specParam);

        if (CollectionUtils.isEmpty(list)) {
            throw new LyException(ExceptionEnum.SPECGPARAM_NOT_FOND);
        }
        return list;
    }


}
