package com.itxiaolin.resilience.service.circuitbreaker;

import com.itxiaolin.resilience.domain.DemoBDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Supplier;


@Service
@Slf4j
public class CircuitbreakerBackendBServiceClient {
    private final RestTemplate restTemplate = new RestTemplate();

    private final String serviceBApi = "http://localhost:8093/circuitbreaker/backendC";

    @CircuitBreaker(name = "backendC", fallbackMethod = "getDefault")
    public CompletionStage<DemoBDto> getDemoBDto(String aId){
        Supplier<DemoBDto> supplier = () -> this.restTemplate.getForEntity(this.serviceBApi + "/"+aId, DemoBDto.class).getBody();
        return CompletableFuture.supplyAsync(supplier);
    }

    private CompletionStage<DemoBDto> getDefault(String aId, Throwable throwable){
        log.info("断路器|使用默认方法返回.");
        return CompletableFuture.supplyAsync(()->DemoBDto.of("0", "无内容"));
    }


    @CircuitBreaker(name = "backendC", fallbackMethod = "getDefault")
    @Retry(name = "backendB", fallbackMethod = "getDefault")
    public CompletionStage<DemoBDto> getDemoBDtoBYCircuitBreakerAndRetry(String aId){
        Supplier<DemoBDto> supplier = () -> this.restTemplate.getForEntity(this.serviceBApi + "/"+aId, DemoBDto.class).getBody();
        return CompletableFuture.supplyAsync(supplier);
    }

}
