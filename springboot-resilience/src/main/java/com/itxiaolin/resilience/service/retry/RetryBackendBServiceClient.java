package com.itxiaolin.resilience.service.retry;

import com.itxiaolin.resilience.domain.DemoBDto;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Supplier;

@Service
@Slf4j
public class RetryBackendBServiceClient {
    private final RestTemplate restTemplate = new RestTemplate();

    private final String serviceBApi = "http://localhost:8092/retry/backendB";

    @Retry(name = "backendB", fallbackMethod = "getDefault")
    public CompletionStage<DemoBDto> getDemoBDto(String aId){
        Supplier<DemoBDto> supplier = () -> this.restTemplate.getForEntity(this.serviceBApi + "/"+aId, DemoBDto.class).getBody();
        return CompletableFuture.supplyAsync(supplier);
    }

    private CompletionStage<DemoBDto> getDefault(String aId, Throwable throwable){
        log.info("使用默认方法返回.....");
        return CompletableFuture.supplyAsync(()->DemoBDto.of("0", "无内容"));
    }

}
