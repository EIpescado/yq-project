package pers.yurwisher.cachestarter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yq
 * @date 2019/01/25 16:13
 * @description
 * @since V1.0.0
 */
@ConfigurationProperties(prefix = "yurwisher.cache")
@Data
public class CacheConfig {

    /**
     * 是否启用 QCache注解
     */
    private boolean enable = true;

}
