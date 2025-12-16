package com.bounce.watergram.watergram;

import com.bounce.watergram.watergram.service.WatergramService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/watergram")
@RestController
public class WatergramRestController {

    private WatergramService watergramService;

    public WatergramRestController(WatergramService watergramService) {
        this.watergramService = watergramService;
    }

    // 메인 화면 로딩 기능

}
