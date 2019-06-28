package pers.yurwisher.wisp.function;

import java.math.BigDecimal;
import java.util.stream.Collector;

/**
 * @author yq
 * @date 2019/06/28 10:02
 * @description Collector自定义实现
 * @since V1.0.0
 */
public class QCollectors {

    private QCollectors() {
    }

    /**
     * 汇总BigDecimal
     */
    public static <T> Collector<T, ?, BigDecimal> summingBigDecimal(ToBigDecimalFunction<? super T> function) {
        return Collector.of(() -> new BigDecimal[1], (a, t) -> {
            if (a[0] == null) {
                a[0] = BigDecimal.ZERO;
            }
            a[0] = a[0].add(function.toBigDecimal(t));
        }, (a, b) -> {
            a[0] = a[0].add(b[0]);
            return a;
        }, a -> a[0]);
    }
}
