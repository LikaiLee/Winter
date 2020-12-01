/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.ioc;

import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.annotation.Autowired;
import site.likailee.winter.common.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author likailee.llk
 * @version DependencyInjection.java 2020/12/01 Tue 3:04 PM likai
 */
@Slf4j
public class DependencyInjection {
    /**
     * 为 BEANS 内的 Bean 注入属性
     *
     * @param packageName
     */
    public static void inject(String packageName) {
        Map<String, Object> beans = BeanFactory.BEANS;
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            // 遍历所有属性
            for (Field field : fields) {
                if (!field.isAnnotationPresent(Autowired.class)) {
                    continue;
                }
                // 获取 field 对应的实例
                String fileName = field.getType().getName();
                Object fieldInstance = beans.get(fileName);
                if (fieldInstance != null) {
                    ReflectionUtils.setField(entry.getValue(), field, fieldInstance);
                }
            }
        }
    }
}
