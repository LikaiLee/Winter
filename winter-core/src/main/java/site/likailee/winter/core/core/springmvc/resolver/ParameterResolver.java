/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.core.springmvc.resolver;

import site.likailee.winter.core.core.springmvc.entity.RouteDefinition;

import java.lang.reflect.Parameter;

/**
 * @author likailee.llk
 * @version ParameterResolver.java 2020/11/30 Mon 3:54 PM likai
 */
public interface ParameterResolver {
    /**
     * 解析方法的参数
     *
     * @param routeDefinition
     * @param parameter
     * @return
     */
    Object resolve(RouteDefinition routeDefinition, Parameter parameter);
}
