/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package likailee.demo.controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import site.likailee.winter.web.entity.User;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author likailee.llk
 * @version UserControllerTest.java 2020/12/01 Tue 3:56 PM likai
 */
public class UserControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerTest.class);

    @BeforeAll
    static void setup() {
        // http://localhost:8080
        RestAssured.baseURI = RestAssured.DEFAULT_URI + ":" + RestAssured.DEFAULT_PORT;
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
        when().get("/user?age=20")
                .then()
                .statusCode(200)
                .body("name", equalTo("default name"))
                .body("age", equalTo(20));
        when().get("/user")
                .then()
                .statusCode(200)
                .body("name", equalTo("default name"))
                .body("age", equalTo(0));
    }

    // test @RequestBody
    @Test
    void should_add_user() {
        User user = new User("test", 100);
        with().body(user)
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
        LOGGER.info(response.getBody().asString());
        assertTrue(response.getBody().asString().contains("BizPrintServiceImpl: hello_from_biz_print"));
    }

    // test @Qualifier
    @Test
    void should_print_from_sys() {
        Response response = with().when().get("/user/sys_print?msg=hello_from_sys_print");
        assertEquals(200, response.statusCode());
        LOGGER.info(response.getBody().asString());
        assertTrue(response.getBody().asString().contains("SysPrintServiceImpl: hello_from_sys_print"));
    }

    // test void response
    @Test
    void should_request_void() {
        Response response = with().when().get("/user/void");
        assertEquals(200, response.statusCode());
        LOGGER.info(response.getBody().asString());
    }

    // test type converter
    @Test
    void should_convert_type() {
        Response response = with().when().get("/user/dataType?int=1&Integer=2&long=3&Long=4&float=1.1&Float=1.0&double=2.0&Double=2.1&boolean=true&Boolean=false&byte=1&Byte=2&char=a&Character=b&short=0&Short=1");
        assertEquals(200, response.statusCode());
        assertTrue(response.getBody().asString().contains("1, 2, 3, 4, 1.1, 1.0, 2.0, 2.1, true, false, 1, 2, a, b, 0, 1"));
    }

    @Test
    void should_convert_list_set() {
        given()
                .contentType("application/json")
                .body("{\"a\": 1, \"b\": 2}")
                .when()
                .get("user/list?list=true,false,false&set=1,1,2,3,3&obj=object")
                .then()
                .statusCode(200)
                .body("list", equalTo("true,false,false"));
    }

}
