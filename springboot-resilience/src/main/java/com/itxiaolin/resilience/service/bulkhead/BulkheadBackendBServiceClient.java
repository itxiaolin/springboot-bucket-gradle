package com.itxiaolin.resilience.service.bulkhead;

import com.itxiaolin.resilience.domain.DemoBDto;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Supplier;


@Service
@Slf4j
public class BulkheadBackendBServiceClient {
    private final RestTemplate restTemplate = new RestTemplate();

    private final String serviceBApi = "http://localhost:8093/bulkhead/backendC";

    @Bulkhead(name = "backendC", fallbackMethod = "getDefault")
    public CompletionStage<DemoBDto> getDemoBDto(String aId){
        log.info("服务A接口|舱壁模式|通过RestTemplate调用API.");
        Supplier<DemoBDto> supplier = () -> this.restTemplate.getForEntity(this.serviceBApi + "/"+aId, DemoBDto.class).getBody();
        return CompletableFuture.supplyAsync(supplier);
    }

    private CompletionStage<DemoBDto> getDefault(String aId, Throwable throwable){
        log.info("服务A接口|舱壁模式|使用默认方法返回.");
        return CompletableFuture.supplyAsync(()->DemoBDto.of("0", "无内容"));
    }
}
