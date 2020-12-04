/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.demo.aop;

import site.likailee.winter.annotation.ioc.Component;

/**
 * @author likailee.llk
 * @version AopServiceImpl.java 2020/12/04 Fri 12:02 PM likai
 */
@Component(name = "AopServiceImpl")
public class AopServiceImpl implements AopService {

    @Override
    public String get() {
        return "AopServiceImpl get()";
    }
}
