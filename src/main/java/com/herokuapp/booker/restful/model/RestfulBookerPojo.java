package com.herokuapp.booker.restful.model;

import java.util.HashMap;

public class RestfulBookerPojo {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private String additionalneeds;

    public HashMap<Object, Object> getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(HashMap<Object, Object> bookingdates) {
        this.bookingdates = bookingdates;
    }

    private HashMap<Object, Object> bookingdates;

    public RestfulBookerPojo() {
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    public static RestfulBookerPojo getRestfulBooker(String firstname, String lastname, int totalprice, boolean depositpaid,
                                                     HashMap<Object,Object> bookingdates) {
        RestfulBookerPojo restfulBookerPojo = new RestfulBookerPojo();
        restfulBookerPojo.setFirstname(firstname);
        restfulBookerPojo.setLastname(lastname);
        restfulBookerPojo.setTotalprice(totalprice);
        restfulBookerPojo.setDepositpaid(depositpaid);
        restfulBookerPojo.setBookingdates(bookingdates);

        return  restfulBookerPojo;
    }
}


