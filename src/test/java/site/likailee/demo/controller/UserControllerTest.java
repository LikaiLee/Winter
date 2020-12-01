/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.demo.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import site.likailee.demo.entity.User;
import site.likailee.winter.serialize.impl.JacksonSerializer;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author likailee.llk
 * @version UserControllerTest.java 2020/12/01 Tue 3:56 PM likai
 */
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
        when().get("/user/{id}", 0)
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


}
