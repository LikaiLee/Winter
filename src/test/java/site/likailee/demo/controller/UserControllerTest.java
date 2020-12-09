/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.demo.controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import site.likailee.demo.entity.User;
import site.likailee.winter.serialize.impl.JacksonSerializer;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author likailee.llk
 * @version UserControllerTest.java 2020/12/01 Tue 3:56 PM likai
 */
@Slf4j
public class UserControllerTest {
    private static JacksonSerializer serializer;

    @BeforeAll
    static void setup() {
        // http://localhost:8080
        RestAssured.baseURI = RestAssured.DEFAULT_URI + ":" + RestAssured.DEFAULT_PORT;
        serializer = new JacksonSerializer();
    }

    // test @PathVariable
    @Test
    void should_get_user_by_id() {
        when().get("/user/{id}", 1)
                .then()
                .statusCode(200)
                .body("name", equalTo("name0"));
    }

    // test @RequestParam
    @Test
    void should_get_user() {
        when().get("/user?name=name111&age=20")
                .then()
                .statusCode(200)
                .body("name", equalTo("name111"))
                .body("age", equalTo(20));
    }

    // test @RequestBody
    @Test
    void should_add_user() {
        User user = new User("test", 100);
        with().body(serializer.serialize(user))
                .header("Content-Type", "application/json")
                .when()
                .post("/user")
                .then()
                .statusCode(200);
    }

    // test @Qualifier
    @Test
    void should_print_from_biz() {
        Response response = with().when().get("/user/biz_print?msg=hello_from_biz_print");
        assertEquals(200, response.statusCode());
        log.info(response.getBody().asString());
        assertTrue(response.getBody().asString().contains("BizPrintServiceImpl: hello_from_biz_print"));
    }

    // test @Qualifier
    @Test
    void should_print_from_sys() {
        Response response = with().when().get("/user/sys_print?msg=hello_from_sys_print");
        assertEquals(200, response.statusCode());
        log.info(response.getBody().asString());
        assertTrue(response.getBody().asString().contains("SysPrintServiceImpl: hello_from_sys_print"));
    }

    // test configuration
    @Test
    void should_get_configuration() {
        Response response = with().when().get("/user/config?name=winter.url");
        assertEquals(200, response.getStatusCode());
        log.info(response.getBody().asString());
        assertEquals("\"https://github.com/LikaiLee/Winter\"", response.getBody().asString());
    }

}
