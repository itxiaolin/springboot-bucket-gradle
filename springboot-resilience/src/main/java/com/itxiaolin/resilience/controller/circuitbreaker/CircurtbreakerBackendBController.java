package com.itxiaolin.resilience.controller.circuitbreaker;

import com.itxiaolin.resilience.domain.DemoADto;
import com.itxiaolin.resilience.service.circuitbreaker.CircuitbreakerBackendBServiceClient;
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
@RequestMapping(value = "/circuitbreaker/backendB")
public class CircurtbreakerBackendBController {
    private Map<String, String> map = new HashMap<>();
    @Autowired
    private CircuitbreakerBackendBServiceClient circuitbreakerBackendBServiceClient;

    @PostConstruct
    private void init() {
        map.put("1", "demoA id=1,测试断路器-backendB");
        map.put("2", "demoA id=2,测试断路器-backendB");
        map.put("3", "demoA id=3,测试断路器-backendB");
        map.put("4", "demoA id=4,测试断路器-backendB");
        map.put("5", "demoA id=5,测试断路器-backendB");
    }

    @GetMapping("{aId}")
    public DemoADto getDemoADto(@PathVariable String aId) throws ExecutionException, InterruptedException {
        CompletionStage<DemoADto> demoADtoCompletionStage = this.circuitbreakerBackendBServiceClient.getDemoBDto(aId).thenApply(demoBDto -> DemoADto.of(aId, map.get(aId), demoBDto));
        return demoADtoCompletionStage.toCompletableFuture().get();
    }


    @GetMapping("byCircuitBreakerAndRetry/{aId}")
    public DemoADto getDemoADtoBYCircuitBreakerAndRetry(@PathVariable String aId) throws ExecutionException, InterruptedException {
        CompletionStage<DemoADto> demoADtoCompletionStage = this.circuitbreakerBackendBServiceClient.getDemoBDto(aId).thenApply(demoBDto -> DemoADto.of(aId, map.get(aId), demoBDto));
        return demoADtoCompletionStage.toCompletableFuture().get();
    }

}
