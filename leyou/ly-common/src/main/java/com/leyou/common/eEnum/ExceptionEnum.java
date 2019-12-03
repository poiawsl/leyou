package com.leyou.common.eEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {

    PRICE_CANNOT_BE_NULL(400,"价格不能为空"),
    CATEGORY_CANNOT_BE_NULL(400,"未查到该分类信息"),
    BRAND_CANNOT_BE_NULL(400,"未查询到品牌信息"),
    BRAND_SAVE_ERROR(400,"已有该品牌信息"),
    UPLOAD_TYPE_ERROR(400,"图片格式出错"),
    UPLOAD_ERROR(400,"图片上传失败"),
    CATEGORYBID_SAVE_ERROR(400,"没找到该品牌分类"),
    BRAND_DELETE_ERROR(400,"没有找到相应商品信息"),
    SPECGROUNP_NOT_FOND(400,"找不到该属性信息"),
    SPECGPARAM_NOT_FOND(400,"找不到该属性详细信息"),
    GOODS_NOT_FOND(400,"没有相关分类信息"),
    BRAND_NOT_FOND(400,"没有找到该类目相应的品牌"),

    INVAID_USER_DATA_TYPE(400,"用户账号或密码数据类型出错"),
    GOODS_SAVE_ERROR(400,"添加商品spu出错"),
    ;
    private int code;
    private String msg;


}
