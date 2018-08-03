package io.klira.lendify.service;

import io.klira.lendify.model.LendifyApplication;
import io.klira.lendify.model.mapping.ApplicationAdapter;
import io.klira.lendify.proxy.HttpClient;
import org.openbroker.cloudevents.CloudEvent;
import org.openbroker.events.ApplicationCreated;
import org.openbroker.events.OfferAccepted;
import org.openbroker.events.OfferRejected;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {
    private HttpClient httpClient;

    public EventServiceImpl(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public ResponseEntity handleApplicationCreated(CloudEvent<ApplicationCreated> event) {
        LendifyApplication lendifyApplication = new ApplicationAdapter().transform(event.getData());
        return httpClient.sendAccept(lendifyApplication);
    }

    @Override
    public ResponseEntity handleOfferAccepted(CloudEvent<OfferAccepted> event) {

        return null;
    }

    @Override
    public ResponseEntity handleOfferRejected(CloudEvent<OfferRejected> event) {
        return null;
    }
}
