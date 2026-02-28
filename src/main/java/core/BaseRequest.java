package core;

import auth.TokenManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseRequest {

    protected RequestSpecification request;

    public BaseRequest(){
        request = RestAssured.given().
                contentType(ContentType.JSON).
                header("Authorization", "Bearer " + TokenManager.getToken("admin")).
                log().all();
    }

    protected Response post(String endpoint, Object body){
        return request.body(body)
                .post(endpoint)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    protected Response get(String endpoint){
        return request.
                get(endpoint).
                then().
                log().
                all().
                extract().
                response();
    }



    protected Response put(Object body, String endpoint ){
        return request.body(body)
                .put(endpoint)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    protected Response delete(String endpoint ){
        return request
                .delete(endpoint)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }



    protected <T> T handleResponse(Response response,
                                   Class<T> successClass,
                                   Class<?> errorClass) {

        int statusCode = response.getStatusCode();

        if (statusCode >= 200 && statusCode < 300) {
            return response.as(successClass);
        } else {
            Object error = response.as(errorClass);
            throw new RuntimeException(
                    "API failed with status " + statusCode + " -> " + error
            );
        }
    }

}
