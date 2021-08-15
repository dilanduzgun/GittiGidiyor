package WebServiceAutomation.apiUtility;

import SeleniumWebAutomation.utilities.ConfigurationReader;
import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class Test {

    ApiRequests apiRequests = new ApiRequests();


    private static Response response;
    JsonPath json;
    String api_key = "594553005371b7f05e8432a3880865aa";
    String token = "61c576294cbc16dbdc41416154f5913fe1902a2a2994b28aa05cfb819aa7a490";
    Faker faker = new Faker();
    String boardId;
    String cardId;
    List<String> cardIdList = new ArrayList<>();


    @Before
    public void setUp() {
        baseURI = ConfigurationReader.get("trello_url");


    }

    @org.junit.Test
    public void createBoard() {
        String randomName = faker.funnyName().name();
        response = given()
                .contentType(ContentType.JSON)
                .queryParam("key", api_key)
                .queryParam("token", token)
                .queryParam("name", randomName)
                .when().post("/boards");

        boardId = response.path("id");
        System.out.println(boardId);
        response.prettyPrint();
        json = response.jsonPath();


    }

    @org.junit.Test
    public void createList() {

        apiRequests.createList();


    }

    @org.junit.Test
    public void createCard() {
        String idList = apiRequests.createList();
        Response response = apiRequests.createCard(idList);
        cardId = response.path("id");
        cardIdList.add(cardId);
        System.out.println(cardIdList);
    }

    @org.junit.Test
    public void updateCard() {
        String idList = apiRequests.createList();
        Response response = apiRequests.createCard(idList);
        cardId = response.path("id");
        cardIdList.add(cardId);
        System.out.println(cardIdList);
        String updatedName = "Good Job!";
        Response response2 = apiRequests.updateCard(updatedName, cardIdList.get(0));
        System.out.println(response2.prettyPrint());

    }

    @org.junit.Test
    public void deleteCard() {
        String idList = apiRequests.createList();
        Response response = apiRequests.createCard(idList);
        cardId = response.path("id");
        cardIdList.add(cardId);
        System.out.println(cardIdList);
        String updatedName = "Good Job!";
        Response response2 = apiRequests.updateCard(updatedName, cardIdList.get(0));
        System.out.println(response2.prettyPrint());

        for (int i = 0; i < cardIdList.size(); i++) {
            response = given()
                    .queryParams("key", api_key, "token", token)
                    .when()
                    .delete("cards/" + cardIdList.get(i));
            response.then().assertThat().statusCode(200);
        }

    }

    @org.junit.Test
    public void deleteBoard() {
        String idList = apiRequests.createList();
        Response response = apiRequests.createCard(idList);
        cardId = response.path("id");
        cardIdList.add(cardId);
        System.out.println(cardIdList);
        String updatedName = "Good Job!";
        Response response2 = apiRequests.updateCard(updatedName, cardIdList.get(0));
        System.out.println(response2.prettyPrint());

        for (int i = 0; i < cardIdList.size(); i++) {
            response = given()
                    .queryParams("key", api_key, "token", token)
                    .when()
                    .delete("cards/" + cardIdList.get(i));
            response.then().assertThat().statusCode(200);
        }
        boardId = response2.path("idBoard");
        apiRequests.deleteBoard(boardId);

    }
}
