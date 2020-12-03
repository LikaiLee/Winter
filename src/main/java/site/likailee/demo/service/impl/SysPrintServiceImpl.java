/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import site.likailee.demo.service.PrintService;
import site.likailee.winter.annotation.ioc.Component;

/**
 * @author likailee.llk
 * @version SysPrintServiceImpl.java 2020/12/02 Wed 2:46 PM likai
 */
@Component(name = "sysPrintServiceImpl")
@Slf4j
public class SysPrintServiceImpl implements PrintService {
    @Override
    public void print(String msg) {
        log.info("sys print: {}", msg);
    }
}
