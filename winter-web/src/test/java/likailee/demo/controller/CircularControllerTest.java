/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package likailee.demo.controller;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.with;

/**
 * @author likailee.llk
 * @version CircularControllerTest.java 2020/12/04 Fri 6:49 PM likai
 */
public class CircularControllerTest {
    @Test
    void should_get_aop_circular() {
        Response response = with().when().get("/circular");
        Assertions.assertEquals(200, response.getStatusCode());
    }
}
