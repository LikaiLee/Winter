/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.web.service.impl;

import site.likailee.winter.core.annotation.ioc.Component;
import site.likailee.winter.web.service.PrintService;

/**
 * @author likailee.llk
 * @version SysPrintServiceImpl.java 2020/12/02 Wed 2:46 PM likai
 */
@Component(name = "sysPrintServiceImpl")
public class SysPrintServiceImpl implements PrintService {
    @Override
    public String print(String msg) {
        return "SysPrintServiceImpl: " + msg;
    }
}
