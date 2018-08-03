package io.klira.lendify.controller;

import java.io.IOException;

import io.klira.lendify.configuration.JacksonUtil;
import io.klira.lendify.service.EventService;
import org.openbroker.events.ApplicationCreated;
import org.openbroker.events.OfferAccepted;
import org.openbroker.events.OfferRejected;
import org.openbroker.meta.EventTypePrivateUnsecuredLoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/cloudEvents", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OpenBrokerController {
    private final JacksonUtil jacksonUtil;
    private final EventService eventService;

    @Autowired
    public OpenBrokerController(JacksonUtil jacksonUtil, EventService eventService) {
        this.jacksonUtil = jacksonUtil;
        this.eventService = eventService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity getCloudEvent(@RequestBody String payload) throws IOException {
        EventTypePrivateUnsecuredLoan eventType = jacksonUtil.parseEventType(payload);

        switch (eventType) {
            case APPLICATION_CREATED:
                return eventService.handleApplicationCreated(jacksonUtil.parseCloudEvent(payload, ApplicationCreated.class));
            case OFFER_ACCEPTED:
                return eventService.handleOfferAccepted(jacksonUtil.parseCloudEvent(payload, OfferAccepted.class));
            case OFFER_REJECTED:
                return eventService.handleOfferRejected(jacksonUtil.parseCloudEvent(payload, OfferRejected.class));
            default:
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
