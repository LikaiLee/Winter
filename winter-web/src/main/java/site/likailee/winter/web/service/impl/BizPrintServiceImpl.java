/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.web.service.impl;

import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.core.annotation.ioc.Component;
import site.likailee.winter.web.service.PrintService;

/**
 * @author likailee.llk
 * @version BizPrintServiceImpl.java 2020/12/02 Wed 2:47 PM likai
 */
@Component(name = "bizPrintServiceImpl")
@Slf4j
public class BizPrintServiceImpl implements PrintService {
    @Override
    public String print(String msg) {
        log.info("### request on BizPrintServiceImpl.print() ###");
        return "BizPrintServiceImpl: " + msg;
    }
}
