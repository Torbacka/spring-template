package io.klira.lendify.service;

import org.openbroker.cloudevents.CloudEvent;
import org.openbroker.events.ApplicationCreated;
import org.openbroker.events.OfferAccepted;
import org.openbroker.events.OfferRejected;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface EventService {

    ResponseEntity handleApplicationCreated(CloudEvent<ApplicationCreated> event);
    ResponseEntity handleOfferAccepted(CloudEvent<OfferAccepted> event);
    ResponseEntity handleOfferRejected(CloudEvent<OfferRejected> event);

}
