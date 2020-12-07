/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.core.aop.cglib;

import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.core.aop.BeanPostProcessor;
import site.likailee.winter.core.aop.Interceptor;

import java.util.List;

/**
 * @author likailee.llk
 * @version CglibAopProxyBeanPostProcessor.java 2020/12/04 Fri 4:33 PM likai
 */
@Slf4j
public class CglibAopProxyBeanPostProcessor implements BeanPostProcessor {
    private List<Interceptor> interceptors;

    public CglibAopProxyBeanPostProcessor(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean) {
        // 链式代理
        Object wrapperProxyBean = bean;
        for (Interceptor interceptor : interceptors) {
            if (interceptor.supports(bean)) {
                // log.info("cglib proxy: {}", bean.getClass().getSimpleName());
                wrapperProxyBean = CglibMethodInterceptor.getProxy(wrapperProxyBean, interceptor);
            }
        }
        return wrapperProxyBean;
    }

}