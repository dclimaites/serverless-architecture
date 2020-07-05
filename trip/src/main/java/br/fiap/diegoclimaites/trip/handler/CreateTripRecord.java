package br.fiap.diegoclimaites.trip.handler;

import br.fiap.diegoclimaites.trip.dao.TripRepository;
import br.fiap.diegoclimaites.trip.model.HandlerRequest;
import br.fiap.diegoclimaites.trip.model.HandlerResponse;
import br.fiap.diegoclimaites.trip.model.Trip;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class CreateTripRecord implements RequestHandler<HandlerRequest, HandlerResponse> {
    private  final TripRepository repository = new TripRepository();

    @Override
    public HandlerResponse handleRequest(final HandlerRequest request, final Context context) {
        Trip trip = null;

        try {
            context.getLogger().log("Entrou no try \n");
            context.getLogger().log("Valor do body \n" );
            context.getLogger().log(request.getBody() + "\n" );
            trip = new ObjectMapper().readValue(request.getBody(), Trip.class);
            context.getLogger().log("Termino o try \n");
        }
        catch (IOException e) {
            String erro = "Erro ao tentar salvar Trip \n";
            context.getLogger().log("Stack \n");
            context.getLogger().log(e.getStackTrace().toString() + "\n");
            return HandlerResponse.builder().setStatusCode(500).setRawBody(erro + e.getMessage()).build();
        }

        context.getLogger().log("Verificando entidade \n");
        context.getLogger().log( trip.getCountry() + "\n");
        context.getLogger().log( trip.getCity() +"\n");
        context.getLogger().log(trip.getDate().toString() + "\n");
        context.getLogger().log(trip.getReason() + "\n");
        final Trip tripRecorded = repository.save(trip);
        context.getLogger().log("Criou uma Trip nova" + trip.getCity());
        return HandlerResponse.builder().setStatusCode(201).setObjectBody(tripRecorded).build();
    }
}
