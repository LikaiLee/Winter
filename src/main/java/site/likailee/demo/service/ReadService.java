/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.demo.service;

import lombok.extern.slf4j.Slf4j;
import site.likailee.winter.annotation.Component;

/**
 * @author likailee.llk
 * @version ReadService.java 2020/12/02 Wed 3:13 PM likai
 */
@Component
@Slf4j
public class ReadService {
    public void read() {
        log.info("reading");
    }
}
