package io.klira.lendify.configuration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.kotlin.KotlinModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openbroker.cloudevents.CloudEvent;
import org.openbroker.events.ApplicationCreated;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class JacksonUtilTests {
    private static JacksonUtil jacksonUtil;

    @BeforeAll
    static void setUpTests() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new KotlinModule());
        jacksonUtil = new JacksonUtil(objectMapper);
    }

    @Test
    void parse() throws IOException {
        byte[] data = Files.readAllBytes(Paths.get("src/test/resources/ApplicationCreated.json"));
        String content = new String(data, StandardCharsets.UTF_8);

        CloudEvent<ApplicationCreated> applicationCreated = jacksonUtil.parseCloudEvent(content, ApplicationCreated.class);
        assertThat(applicationCreated.getData().getApplication().getApplicant().getSsn()).isEqualTo("198712167249");
    }
}
