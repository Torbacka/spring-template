package io.klira.springtemplate.controller;

import com.fasterxml.jackson.databind.JsonNode;
import io.klira.cloudevents.CloudEvent;
import io.klira.openbroker.meta.EventTypePrivateUnsecuredLoan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/cloudEvents", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OpenBrokerController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity getCloudEvent(CloudEvent<JsonNode> cloudEvent) {
        EventTypePrivateUnsecuredLoan eventType = EventTypePrivateUnsecuredLoan.invoke(cloudEvent.getEventType());
        ResponseEntity responseEntity = null;
        switch (eventType) {
            case APPLICATION_CREATED:
                break;
            case OFFER_ACCEPTED:
                break;
            case OFFER_REJECTED:
                break;
            default:
                responseEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
                break;
        }
        return responseEntity == null ? new ResponseEntity(HttpStatus.BAD_REQUEST) : responseEntity;
    }
}
