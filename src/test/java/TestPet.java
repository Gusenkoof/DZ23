import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.Argument;
import io.restassured.specification.RequestSpecification;
import model.Category;
import model.Pet;
import model.TagPet;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.core.IsEqual.equalTo;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("API тесты")
public class TestPet {

    //Создаем спецификацию
    private static RequestSpecification reSpec = SpecificationApi.getRequestSpecification();



    @Test
    @Order(1)
    public void testPost(){
        TagPet tagPet = new TagPet(44444, "British cat");
        Category category = new Category(8, "Cat");
        Pet pet = new Pet(8965, category, "Missi", new ArrayList<>(), new ArrayList<>(Collections.singletonList(tagPet)), "available");
        given()
                .spec(reSpec)
                .contentType(ContentType.JSON)
                .body(pet)
                .post(EndPoints.PET)
                .then()
                .assertThat()
                .body("id", equalTo(8965))
                .assertThat()
                .body("name", equalTo("Missi"));
    }

    @Test
    @Order(2)
    public void testGet(){
         given()
                .spec(reSpec)
                .contentType(ContentType.JSON)
                .when()
                .get("/" + EndPoints.PET+"/42805")
                .then()
                .body("name", equalTo("Cleo"));


    }

    @Test
    @Order(3)
    public void testGet_2() {
        given()
                .spec(reSpec)
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(404);
    }

    @Test
    @Order(4)
    public void testDelete(){
        Response response = given()
                .spec(reSpec)
                .when()
                .delete("/" + EndPoints.PET+"/2295"); // или 42805

        response.prettyPrint();
    }



}





