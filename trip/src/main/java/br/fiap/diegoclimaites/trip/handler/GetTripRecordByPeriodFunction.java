package br.fiap.diegoclimaites.trip.handler;

import br.fiap.diegoclimaites.trip.dao.TripRepository;
import br.fiap.diegoclimaites.trip.model.HandlerRequest;
import br.fiap.diegoclimaites.trip.model.HandlerResponse;
import br.fiap.diegoclimaites.trip.model.Trip;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

public class GetTripRecordByPeriodFunction implements RequestHandler<HandlerRequest, HandlerResponse> {

    private  final TripRepository repository = new TripRepository();

    @Override
    public HandlerResponse handleRequest(HandlerRequest handlerRequest, Context context) {
        final String starts = handlerRequest.getQueryStringParameters().get("starts");
        final String ends = handlerRequest.getQueryStringParameters().get("ends");

        context.getLogger().log("Procurando viagens entre " + starts + " e " + ends);
        final List<Trip> trips = this.repository.findByPeriod(starts, ends);

//        if(trips == null || trips.isEmpty()) {
//            return HandlerResponse.builder().setStatusCode(404).build();
//        }

        return HandlerResponse.builder().setStatusCode(200).setObjectBody(trips).build();
    }
}
