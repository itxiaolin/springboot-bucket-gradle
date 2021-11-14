package com.itxiaolin.resilience.controller.retry;

import com.itxiaolin.resilience.domain.DemoBDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


@Slf4j
@RestController
@RequestMapping(value = "/retry/backendB")
public class RetryBackendBController {

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
    public ResponseEntity<DemoBDto> getDemoBDto(@PathVariable String aId) throws InterruptedException {
        DemoBDto orDefault = this.map.getOrDefault(aId, new DemoBDto());
        return this.failRandomly(orDefault);
    }

    private ResponseEntity<DemoBDto> failRandomly(DemoBDto demoBDto){
        int random = ThreadLocalRandom.current().nextInt(1, 4);
        if(random < 2){
            log.info("返回错误码500.....");
            return ResponseEntity.status(500).build();
        }else if(random < 3){
            log.info("返回错误请求.....");
            return ResponseEntity.badRequest().build();
        }
        log.info("请求成功.....");
        return ResponseEntity.ok(demoBDto);
    }
}
