package br.fiap.diegoclimaites.trip.dao;

import br.fiap.diegoclimaites.trip.model.Trip;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripRepository {
    private static final DynamoDBMapper mapper = DynamoDBManager.mapper();

    public Trip save(final Trip trip) {
        mapper.save(trip);
        return trip;
    }

    public List<Trip> findByPeriod(final String starts, final String ends) {

        final Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withS(starts));
        eav.put(":val2", new AttributeValue().withS(ends));

        final Map<String, String> expression = new HashMap<>();
        expression.put("#date", "date");

        //Adicionado o # antes de date, pois, date é uma palavra reservada do Dynamodb
        final DynamoDBScanExpression queryExpression = new DynamoDBScanExpression()
                .withFilterExpression("#date between :val1 and :val2")
                .withExpressionAttributeValues(eav)
                .withExpressionAttributeNames(expression);

        final List<Trip> trips = mapper.scan(Trip.class, queryExpression);

        return trips;
    }

    public List<Trip> findByCountry(final String country) {
        final Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withS(country));

        final DynamoDBQueryExpression<Trip> queryExpression = new DynamoDBQueryExpression<Trip>()
        .withKeyConditionExpression("country = :val1").withExpressionAttributeValues(eav);

        List<Trip> trips = mapper.query(Trip.class, queryExpression);

        return trips;
    }
}
