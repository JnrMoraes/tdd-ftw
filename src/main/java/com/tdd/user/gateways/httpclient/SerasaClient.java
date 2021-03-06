package com.tdd.user.gateways.httpclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "${serasa.name}", url = "${serasa.url:}")
public interface SerasaClient {

    @RequestMapping(method = RequestMethod.GET, value = "/users/cpf/status/{document}",
            produces = APPLICATION_JSON_VALUE)
    SerasaIntegrationStatus getStatus(@PathVariable(value = "document") final String document);

}
