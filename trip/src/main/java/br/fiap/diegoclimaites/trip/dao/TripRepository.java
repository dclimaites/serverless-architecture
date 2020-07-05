package br.fiap.diegoclimaites.trip.dao;

import br.fiap.diegoclimaites.trip.model.Trip;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class TripRepository {
    private static final DynamoDBMapper mapper = DynamoDBManager.mapper();

    public Trip save(final Trip trip) {
        mapper.save(trip);
        return trip;
    }
}
