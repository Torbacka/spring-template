package io.klira.lendify.proxy;

import javax.naming.AuthenticationException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.klira.lendify.model.LendifyApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class LendifyClient implements HttpClient {
    private static final Logger log = LoggerFactory.getLogger(LendifyClient.class);
    private static final String ACCESS_KEY = System.getenv("ACCESS_KEY");
    private static final String URL = System.getenv("URL");
    private static final String CREDENTIALS_URL = System.getenv("CREDENTIALS_URL");
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    @Autowired
    public LendifyClient(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public ResponseEntity sendApplication(LendifyApplication lendifyApplication) {


        return null;
    }

    @Override
    public ResponseEntity sendAccept(LendifyApplication lendifyApplication) {
        return null;
    }

    @Override
    public ResponseEntity sendReject(LendifyApplication lendifyApplication) {
        return null;
    }


    private <T, U> ResponseEntity<U> sendPost(String endpoint, T data) throws JsonProcessingException {
        ResponseEntity<String> credentials = getCredentials();
        if (credentials.getStatusCode().is2xxSuccessful()) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("lendify-session-key", credentials.getBody());
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL + endpoint, data, String.class);
            
            return responseEntity;
        } else {
            log.error("Response to lendify failed, response data: %s; status code %s; error message: $s",
                    objectMapper.writeValueAsString(data),
                    credentials.getStatusCode().toString(),
                    credentials.getBody());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    private String getCredentials() throws AuthenticationException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("lendify-access-key", ACCESS_KEY);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> credentials = restTemplate.exchange(CREDENTIALS_URL, HttpMethod.GET, entity, String.class);
        if (credentials.getStatusCode().is2xxSuccessful()) {
            return credentials.getBody();
        } else {
            log.error("Response to lendify failed, response data: %s; status code %s; error message: $s",
                    credentials.getStatusCode().toString(),
                    credentials.getBody());
            throw new AuthenticationException("Could not receive credentials from Lenidy.");
        }
    }
}
