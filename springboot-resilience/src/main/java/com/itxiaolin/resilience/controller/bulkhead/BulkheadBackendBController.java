package com.itxiaolin.resilience.controller.bulkhead;

import com.itxiaolin.resilience.domain.DemoADto;
import com.itxiaolin.resilience.service.bulkhead.BulkheadBackendBServiceClient;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequestMapping(value = "/bulkhead/backendB")
public class BulkheadBackendBController {
    private final Map<String, String> map = new HashMap<>();
    @Autowired
    private BulkheadBackendBServiceClient bulkheadBackendBServiceClient;

    @PostConstruct
    private void init() {
        map.put("1", "demoA id=1,测试舱壁模式-backendB");
        map.put("2", "demoA id=2,测试舱壁模式-backendB");
        map.put("3", "demoA id=3,测试舱壁模式-backendB");
        map.put("4", "demoA id=4,测试舱壁模式-backendB");
        map.put("5", "demoA id=5,测试舱壁模式-backendB");
    }

    @GetMapping("{aId}")
    public DemoADto getDemoADto(@PathVariable String aId) throws ExecutionException, InterruptedException {
        log.info("服务A接口|舱壁模式|请求慢接口");
        CompletionStage<DemoADto> demoADtoCompletionStage = this.bulkheadBackendBServiceClient.getDemoBDto(aId).thenApply(demoBDto -> DemoADto.of(aId, map.get(aId), demoBDto));
        return demoADtoCompletionStage.toCompletableFuture().get();
    }


    @GetMapping("getOnlyDemoA/{aId}")
    public DemoADto getDemoADtoBYCircuitBreakerAndRetry(@PathVariable String aId) throws InterruptedException {
        log.info("服务A接口|舱壁模式|请求默认接口");
        Thread.sleep(50);
        return DemoADto.of(aId, map.get(aId), null);
    }

}
