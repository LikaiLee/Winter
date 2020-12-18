/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import site.likailee.winter.core.annotation.ioc.Component;

import site.likailee.winter.web.service.PrintService;

/**
 * @author likailee.llk
 * @version BizPrintServiceImpl.java 2020/12/02 Wed 2:47 PM likai
 */
@Component(name = "bizPrintServiceImpl")
public class BizPrintServiceImpl implements PrintService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BizPrintServiceImpl.class);

    @Override
    public String print(String msg) {
        LOGGER.info("### request on BizPrintServiceImpl.print() ###");
        return "BizPrintServiceImpl: " + msg;
    }
}
