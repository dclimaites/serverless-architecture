package br.fiap.diegoclimaites.trip.handler;

import br.fiap.diegoclimaites.trip.dao.TripRepository;
import br.fiap.diegoclimaites.trip.model.HandlerRequest;
import br.fiap.diegoclimaites.trip.model.HandlerResponse;
import br.fiap.diegoclimaites.trip.model.Trip;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

public class GetTripRecordByCountryAndCityFunction implements RequestHandler<HandlerRequest, HandlerResponse> {
    private  final TripRepository repository = new TripRepository();

    @Override
    public HandlerResponse handleRequest(final HandlerRequest handlerRequest,final Context context) {
        final String country = handlerRequest.getPathParameters().get("country");

        final String city = getCity(handlerRequest);

        context.getLogger().log("Valor de country \n");
        context.getLogger().log("Valor de country \n");

        List<Trip> trips = null;
        if(city != null)
        {
            context.getLogger().log("Procurando viagens entre por país " + country + " e cidade "  + city);
            trips = this.repository.findByCountryAndCity(country, city);
        }
        else {
            context.getLogger().log("Procurando viagens entre por país " + country);
            trips = this.repository.findByCountry(country);
        }


        if(trips == null || trips.isEmpty()) {
            return HandlerResponse.builder().setStatusCode(404).build();
        }

        return HandlerResponse.builder().setStatusCode(200).setObjectBody(trips).build();
    }

    private String getCity(HandlerRequest request) {
        try {
            return request.getQueryStringParameters().get("city");
        }
        catch (NullPointerException e) {
            return null;
        }
    }
}
