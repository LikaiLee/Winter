/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package likailee.demo.controller;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.with;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author likailee.llk
 * @version AopControllerTest.java 2020/12/04 Fri 2:39 PM likai
 */
public class AopControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AopControllerTest.class);

    @BeforeAll
    static void setUp() {
    }

    @Test
    void should_get_aop_jdk_qualifier() {
        Response response = with().when().get("/aop/test_jdk_qualifier?name=myname");
        assertEquals(200, response.getStatusCode());
        LOGGER.info(response.getBody().asString());
        assertTrue(response.getBody().asString().contains("BizPrintServiceImpl: myname test_jdk_qualifier"));
    }

    @Test
    void should_get_aop_cglib() {
        Response response = with().when().get("/aop/test_cglib");
        assertEquals(200, response.getStatusCode());
        LOGGER.info(response.getBody().asString());
    }

}
