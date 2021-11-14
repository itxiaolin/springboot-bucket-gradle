package com.itxiaolin.resilience.controller.retry;

import com.itxiaolin.resilience.domain.DemoADto;
import com.itxiaolin.resilience.service.retry.RetryBackendBServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping(value = "/retry/backendA")
public class RetryBackendAController {
    private Map<String, String> map = new HashMap<>();
    @Autowired
    private RetryBackendBServiceClient retryBackendBServiceClient;

    @PostConstruct
    private void init() {
        map.put("1", "demoA1描述");
        map.put("2", "demoA2描述");
        map.put("3", "demoA3描述");
        map.put("4", "demoA4描述");
        map.put("5", "demoA5描述");
        map.put("6", "demoA6描述");
    }

    @GetMapping("{aId}")
    public DemoADto getDemoADto(@PathVariable String aId) throws ExecutionException, InterruptedException {
        CompletionStage<DemoADto> demoADtoCompletionStage = this.retryBackendBServiceClient.getDemoBDto(aId).thenApply(demoBDto -> DemoADto.of(aId, map.get(aId), demoBDto));
        return demoADtoCompletionStage.toCompletableFuture().get();
    }

}
