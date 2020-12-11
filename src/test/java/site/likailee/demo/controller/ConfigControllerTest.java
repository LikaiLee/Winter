/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.demo.controller;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.with;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author likailee.llk
 * @version ConfigControllerTest.java 2020/12/11 Fri 3:09 PM likai
 */
@Slf4j
public class ConfigControllerTest {
    @Test
    void should_get_config() {
        Response response = with().when().get("/config");
        assertEquals(200, response.getStatusCode());
        assertEquals("\"Likai Lee, https://likailee.site\"", response.getBody().asString());
    }

    @Test
    void should_get_config_by_name() {
        Response response = with().when().get("/config/name?key=winter.url");
        assertEquals(200, response.getStatusCode());
        assertEquals("\"https://github.com/LikaiLee/Winter\"", response.getBody().asString());
    }
}
