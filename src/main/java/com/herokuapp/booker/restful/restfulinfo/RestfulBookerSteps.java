package com.herokuapp.booker.restful.restfulinfo;


import com.herokuapp.booker.restful.constants.EndPoints;
import com.herokuapp.booker.restful.model.RestfulBookerPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class RestfulBookerSteps {
    @Step("Creating booking with firstname: {0}, lastname: {1}, totalprice: {2}, depositpaid: {3},bookingdates: {4}")
    public ValidatableResponse createbooking(String firstname, String lastname, int totalprice, boolean depositpaid,
                                             HashMap<Object,Object> bookingdates) {

        HashMap<String, Object> dates = new HashMap<>();
        dates.put("checkin", "2023-01-01");
        dates.put("checkout", "2023-01-01");
        RestfulBookerPojo restfulBookerPojo = new RestfulBookerPojo();
        restfulBookerPojo.setFirstname(firstname);
        restfulBookerPojo.setLastname(lastname);
        restfulBookerPojo.setTotalprice(totalprice);
        restfulBookerPojo.setDepositpaid(depositpaid);
        restfulBookerPojo.setBookingdates(bookingdates);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer e367431b1feb1bd")
                .body(restfulBookerPojo)
                .when()
                .post(EndPoints.CREATE_SINGLE_BOOKING)
                .then();
    }

    @Step("Getting the Booking information with ID: {0}")
    public HashMap<String, Object> getbookingById(int bookingId) {
        HashMap<String, Object> bookingMap = SerenityRest.given().log().all()
                .header("Authorization", "Bearer e367431b1feb1bd")
                .when()
                .pathParam("bookingId", bookingId)
                .get(EndPoints.GET_SINGLE_BOOKING_BY_ID)
                .then()
                .statusCode(200)
                .extract().path("");

        return bookingMap;
    }
    @Step("Updating store booking with Id: {0},firstname: {1}, lastname: {2}, totalprice: {3}, depositpaid: {4},bookingdates: {5}")
    public ValidatableResponse updateBooking(int bookingId,String firstname, String lastname, int totalprice, boolean depositpaid,
                                           HashMap<Object,Object> bookingdates) {

        HashMap<Object, Object> booking = new HashMap<>();
        booking.put("checkin", "2022-01-01");
        booking.put("checkout", "2022-01-01");
        RestfulBookerPojo restfulBookerPojo = new RestfulBookerPojo();
        restfulBookerPojo.setFirstname(firstname);
        restfulBookerPojo.setLastname(lastname);
        restfulBookerPojo.setTotalprice(totalprice);
        restfulBookerPojo.setDepositpaid(depositpaid);
        restfulBookerPojo.setBookingdates(bookingdates);

        return SerenityRest.given().log().all()
                .auth().preemptive().basic("admin","password123")
                .header("Content-Type", "application/json")
                .body(restfulBookerPojo)
                .pathParam("bookingId", bookingId)
                .when()
                .patch(EndPoints.UPDATE_BOOKING_BY_ID)
                .then();
    }
    @Step ("Deleting store information with storeID: {0}")
    public ValidatableResponse deleteBooking(int bookingId){
        return SerenityRest.given().log().all()
                .auth().preemptive().basic("admin","password123")
                .pathParam("bookingId", bookingId)
                .when()
                .delete(EndPoints.DELETE_BOOKING_BY_ID)
                .then();
    }
    @Step("Getting -information with ")
    public ValidatableResponse getbookingchangesById(int BookingId) {
        return SerenityRest.given().log().all()
                .auth().preemptive().basic("admin","password123")
                .pathParam("BookingId",BookingId)
                .when()
                .get(EndPoints.CREATE_SINGLE_BOOKING)
                .then();
    }


    }


