/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import site.likailee.demo.service.PrintService;
import site.likailee.winter.annotation.Component;

/**
 * @author likailee.llk
 * @version BizPrintServiceImpl.java 2020/12/02 Wed 2:47 PM likai
 */
@Component(name = "bizPrintServiceImpl")
@Slf4j
public class BizPrintServiceImpl implements PrintService {
    @Override
    public void print(String msg) {
        log.info("biz print: {}", msg);
    }
}
