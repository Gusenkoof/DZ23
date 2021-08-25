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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static io.restassured.RestAssured.given;

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
        Response response = given()
                .spec(reSpec)
                .body(pet)
                .post(EndPoints.PET);
    }

    @Test
    @Order(2)
    public void testGet(){
        Response response = given()
                .spec(reSpec)
                .when()
                .get("/" + EndPoints.PET+"/5489");

        response.prettyPrint();
    }

    @Test
    @Order(3)
    public void testDelete(){
        Response response = given()
                .spec(reSpec)
                .when()
                .delete("/" + EndPoints.PET+"/2295");

        response.prettyPrint();
    }



}





