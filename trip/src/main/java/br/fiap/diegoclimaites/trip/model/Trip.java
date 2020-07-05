package br.fiap.diegoclimaites.trip.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Date;

@DynamoDBTable(tableName = "trip")
public class Trip {

    @DynamoDBHashKey(attributeName = "country")
    private String country;
    @DynamoDBIndexRangeKey(attributeName = "city", localSecondaryIndexName = "cityIndex")
    private String city;

    @DynamoDBRangeKey(attributeName = "date")
    private String date;

    @DynamoDBAttribute(attributeName = "reason")
    private String reason;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Trip(String country, String city, String date, String reason) {
        super();
        this.country = country;
        this.city = city;
        this.date = date;
        this.reason = reason;
    }

    public Trip() { super(); }
}
