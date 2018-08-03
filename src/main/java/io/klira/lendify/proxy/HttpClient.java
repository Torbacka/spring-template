package io.klira.lendify.proxy;

import io.klira.lendify.model.LendifyApplication;
import org.springframework.http.ResponseEntity;

public interface HttpClient {

    ResponseEntity sendApplication(LendifyApplication lendifyApplication);
    ResponseEntity sendAccept(LendifyApplication lendifyApplication);
    ResponseEntity sendReject(LendifyApplication lendifyApplication);

}
