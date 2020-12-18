/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.winter.web.config;


import site.likailee.winter.core.annotation.config.Value;
import site.likailee.winter.core.annotation.ioc.Autowired;
import site.likailee.winter.core.annotation.springmvc.GetMapping;
import site.likailee.winter.core.annotation.springmvc.RequestParam;
import site.likailee.winter.core.annotation.springmvc.RestController;
import site.likailee.winter.core.core.config.ConfigurationManager;

/**
 * @author likailee.llk
 * @version ConfigController.java 2020/12/11 Fri 2:50 PM likai
 */
@RestController("/config")
public class ConfigController {

    @Autowired
    private ConfigurationManager configurationManager;

    @Value("winter.author")
    private String author;

    @Value("winter.site")
    private String site;

    @GetMapping
    public String getConfig() {
        return author + ", " + site;
    }

    @GetMapping("/name")
    public String getConfigByName(@RequestParam("key") String name) {
        return configurationManager.getString(name);
    }
}
