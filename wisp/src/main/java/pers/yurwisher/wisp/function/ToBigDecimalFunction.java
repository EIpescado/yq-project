package pers.yurwisher.wisp.function;

import java.math.BigDecimal;

/**
 * @author yq
 * @date 2019/06/28 09:45
 * @description
 * @since V1.0.0
 */
@FunctionalInterface
public interface  ToBigDecimalFunction<T> {

    BigDecimal toBigDecimal(T value);
}
