package ${controllerPackageName};

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ${superControllerClass};
import ${superControllerClass};

/**
 * @author ${author}
 * @date ${date?string("yyyy-MM-dd HH:mm:ss")}
 * @description ${description}
 * @since V1.0.0
 */
@RestController
@RequestMapping("/${entityName}")
<#if superControllerName??>
public interface ${controllerName} extends ${superControllerName}{
<#else>
public interface ${controllerName}{
</#if>




}
