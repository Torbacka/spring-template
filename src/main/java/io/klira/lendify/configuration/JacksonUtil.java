package io.klira.lendify.configuration;

import java.io.IOException;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openbroker.cloudevents.CloudEvent;
import org.openbroker.meta.EventTypePrivateUnsecuredLoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JacksonUtil {
    private final ObjectMapper objectMapper;

    @Autowired
    public JacksonUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> CloudEvent<T> parseCloudEvent(String data, Class<T> contentClass) throws IOException {
        JavaType type = objectMapper.getTypeFactory().constructParametricType(CloudEvent.class, contentClass);
        return objectMapper.readValue(data, type);
    }

    public JsonNode parse(String data) throws IOException {
        return objectMapper.readValue(data, JsonNode.class);
    }

    public  EventTypePrivateUnsecuredLoan parseEventType(String payload) throws IOException {
        JsonNode node = parse(payload);
        return EventTypePrivateUnsecuredLoan.invoke(node.get("parseEventType").asText());
    }
}
