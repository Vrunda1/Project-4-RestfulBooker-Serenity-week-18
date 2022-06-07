package com.herokuapp.booker.restful.productinfo;



import com.herokuapp.booker.restful.model.RestfulBookerPojo;
import com.herokuapp.booker.restful.restfulinfo.RestfulBookerSteps;
import com.herokuapp.booker.restful.testbase.TestBase;
import com.herokuapp.booker.restful.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

/**
 * Created by Jay
 */
@RunWith(SerenityRunner.class)
public class RestfulBookerTestWithSteps extends TestBase {

        static String firstname = "John" + TestUtils.getRandomValue();
        static String lastname = "Brown" + TestUtils.getRandomValue();
        static int totalprice = 2500;
        static boolean depositpaid= true;

        static int bookingId;

        @Steps
        RestfulBookerSteps restfulBookerSteps;

        @Title("This will create new booking")
        @Test
        public void test001(){
                HashMap<Object,Object> bookingdates = new HashMap<>();
                bookingdates.put("checkin","2023-02-23");
                bookingdates.put("checkout","2023-02-25");

                ValidatableResponse response = restfulBookerSteps.createbooking(firstname, lastname, totalprice,
                        depositpaid,bookingdates );
                response.log().all().statusCode(200);

                bookingId = response.log().all().extract().path("id");
                System.out.println(bookingId);

        }
        @Title("Verify if the booking was created in application")
        @Test
        public void test002(){
                HashMap<String, Object>  bookingMap =restfulBookerSteps.getbookingById(bookingId);
                Assert.assertThat(bookingMap, hasValue(firstname));
                System.out.println(bookingId);
        }

        @Title("Update the store information and verify the updated information")
        @Test
        public void test003() {
                HashMap<Object,Object> bookingdates = new HashMap<>();
                bookingdates.put("checkin","2023-02-23");
                bookingdates.put("checkout","2023-02-25");

                firstname = firstname + "_updated";
                restfulBookerSteps.updateBooking(bookingId,firstname, lastname, totalprice,
                        depositpaid,bookingdates).log().all().statusCode(200);
                HashMap<String, Object> bookingMap = restfulBookerSteps.getbookingById(bookingId);
                Assert.assertThat(bookingMap, hasValue(firstname));
                System.out.println(bookingId);

        }
        @Test
        public void test004() {
                restfulBookerSteps.deleteBooking(bookingId).statusCode(200);
               restfulBookerSteps.getbookingchangesById(bookingId).statusCode(404);
        }


}
