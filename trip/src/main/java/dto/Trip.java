package dto;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Date;

@DynamoDBTable(tableName = "trip")
public class Trip {
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        city = city;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        reason = reason;
    }

    public Trip(String country, String city, Date date, String reason) {
        country = country;
        city = city;
        this.date = date;
        reason = reason;
    }

    public Trip() {}

    @DynamoDBHashKey(attributeName = "country")
    private String country;
    @DynamoDBAttribute(attributeName = "city")
    private String city;
    @DynamoDBIndexRangeKey(attributeName = "date", localSecondaryIndexName = "dateIndex")
    private Date date;
    @DynamoDBAttribute(attributeName = "reason")
    private String reason;
}
