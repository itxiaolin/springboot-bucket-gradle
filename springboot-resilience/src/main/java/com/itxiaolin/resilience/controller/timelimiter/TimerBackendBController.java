package com.itxiaolin.resilience.controller.timelimiter;

import com.itxiaolin.resilience.domain.DemoBDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(value = "/timelimiter/backendB")
public class TimerBackendBController {

    private final Map<String, DemoBDto> map = new HashMap<>();

    @PostConstruct
    private void init() {
        map.put("1", DemoBDto.of("1", "demoB id=1描述"));
        map.put("2", DemoBDto.of("2", "demoB id=2描述"));
        map.put("3", DemoBDto.of("3", "demoB id=3描述"));
        map.put("4", DemoBDto.of("4", "demoB id=4描述"));
        map.put("5", DemoBDto.of("5", "demoB id=5描述"));
        map.put("6", DemoBDto.of("6", "demoB id=6描述"));
    }

    @GetMapping("{aId}")
    public DemoBDto getDemoBDto(@PathVariable String aId) throws InterruptedException {
        Thread.sleep(ThreadLocalRandom.current().nextInt(10, 10000));
        DemoBDto orDefault = this.map.getOrDefault(aId, new DemoBDto());
        return orDefault;
    }

}
