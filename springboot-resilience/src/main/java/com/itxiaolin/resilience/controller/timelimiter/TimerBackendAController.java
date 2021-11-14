package com.itxiaolin.resilience.controller.timelimiter;

import com.itxiaolin.resilience.domain.DemoADto;
import com.itxiaolin.resilience.service.timelimiter.TimerBackendBServiceClient;
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
@RequestMapping(value = "/timelimiter/backendA")
public class TimerBackendAController {
    private final Map<String, String> map = new HashMap<>();
    private final TimerBackendBServiceClient timerBackendBServiceClient;
    public TimerBackendAController(TimerBackendBServiceClient timerBackendBServiceClient){
        this.timerBackendBServiceClient=timerBackendBServiceClient;
    }
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
        CompletionStage<DemoADto> demoADtoCompletionStage = this.timerBackendBServiceClient.getDemoBDto(aId).thenApply(demoBDto -> DemoADto.of(aId, map.get(aId), demoBDto));
        return demoADtoCompletionStage.toCompletableFuture().get();
    }
}
