package com.leyou.common.eResult;

import com.leyou.common.eEnum.ExceptionEnum;
import lombok.Data;

@Data
public class ExceptionResult {
    private int status;         //错误码
    private String message;     //错误信息
    private Long timestamp;     //时间
    public ExceptionResult(ExceptionEnum em){
        this.status = em.getCode();
        this.message = em.getMsg();
        this.timestamp = System.currentTimeMillis();
     }

}
