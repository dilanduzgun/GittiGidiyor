package WebServiceAutomation.apiUtility;


import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import static io.restassured.RestAssured.*;


public class ApiRequests {


    private static Response response;
    String api_key = "594553005371b7f05e8432a3880865aa";
    String token = "61c576294cbc16dbdc41416154f5913fe1902a2a2994b28aa05cfb819aa7a490";
    Faker faker = new Faker();
    String boardId;
    String listId;
    String cardId;

    public String createBoard() {
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
        return boardId;
    }

    public String createList() {
        String randomName = faker.funnyName().name();
        response = given()
                .contentType(ContentType.JSON)
                .queryParam("key", api_key)
                .queryParam("token", token)
                .queryParam("name", randomName)
                .queryParam("idBoard", createBoard())
                .when().post("/lists");

        response.prettyPrint();
        listId = response.path("id");
        System.out.println(listId);
        return listId;
    }

    public Response createCard(String idList) {
        String randomName = faker.funnyName().name();

        for (int i = 0; i < 2; i++) {

            response = given()
                    .queryParams("key", api_key, "token", token, "idList", idList, "name", randomName)
                    .contentType(ContentType.JSON)
                    .when().post("/cards");

            response.prettyPrint();
            cardId = response.path("id");

        }
        return response;

    }

    public Response updateCard(String updatedName, String choosingCardId) {
        response = given()
                .queryParams("key", api_key, "token", token, "name", updatedName)
                .when().put("cards/" + choosingCardId);

        return response;
    }

    public Response deleteCard() {

        response = given()
                .queryParams("key", api_key, "token", token)
                .when().delete("cards/");

        return response;

    }

    public Response deleteBoard(String boardId) {

        response = given()
                .queryParams("key", api_key, "token", token)
                .when().delete("boards/" + boardId);

        return response;

    }
}
