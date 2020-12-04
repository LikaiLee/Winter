/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.demo.controller;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.with;

/**
 * @author likailee.llk
 * @version AopControllerTest.java 2020/12/04 Fri 2:39 PM likai
 */
@Slf4j
public class AopControllerTest {
    @BeforeAll
    static void setUp() {
    }

    @Test
    void should_get_aop_jdk_qualifier() {
        Response response = with().when().get("/aop/test_jdk_qualifier");
        Assertions.assertEquals(200, response.getStatusCode());
        log.info(response.getBody().asString());
        Assertions.assertTrue(response.getBody().asString().contains("BizPrintServiceImpl: test_jdk_qualifier ## this is from WebLogInterceptor"));
    }
    @Test
    void should_get_aop_cglib() {
        Response response = with().when().get("/aop/test_cglib");
        Assertions.assertEquals(200, response.getStatusCode());
        log.info(response.getBody().asString());
        Assertions.assertTrue(response.getBody().asString().contains("reading ## this is from WebLogInterceptor"));
    }
}
