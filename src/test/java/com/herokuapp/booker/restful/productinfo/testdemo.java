package com.herokuapp.booker.restful.productinfo;

import com.herokuapp.booker.restful.constants.EndPoints;
import com.herokuapp.booker.restful.testbase.TestBase;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Test;

public class testdemo extends TestBase {
    @Test
    public void getbookid(){
                   SerenityRest.given()
                    .when()
                    .get(EndPoints.GET_BOOKING)
                    .then().log().all();


        }
}
