package br.fiap.diegoclimaites.trip.handler;

import br.fiap.diegoclimaites.trip.dao.TripRepository;
import br.fiap.diegoclimaites.trip.model.HandlerRequest;
import br.fiap.diegoclimaites.trip.model.HandlerResponse;
import br.fiap.diegoclimaites.trip.model.Trip;
import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class CreateTripRecord {
    private  final TripRepository repository = new TripRepository();

    public HandlerResponse handleRequest(final HandlerRequest request, final Context context) {
        Trip trip = null;

        try {
            trip = new ObjectMapper().readValue(request.getBody(), Trip.class);
        }
        catch (IOException e) {
            return HandlerResponse.builder().setStatusCode(500).setRawBody("Erro ao tentar salvar Trip").build();
        }

        context.getLogger().log("Criou uma Trip nova" + trip.getCity());
        final Trip tripRecorded = repository.save(trip);
        return HandlerResponse.builder().setStatusCode(201).setObjectBody(tripRecorded).build();
    }
}
