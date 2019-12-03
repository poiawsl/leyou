package com.leyou.common.Exceptions;

import com.leyou.common.eEnum.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LyException extends RuntimeException {
 private ExceptionEnum exceptionEnum;
}
