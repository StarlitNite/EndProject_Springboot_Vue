package com.endproject.util;

import com.endproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/3/28 13:22
 */
@Component
public class ZeroHealthStatus {
    @Autowired
    UserService userService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void ZeroHealthStatus() throws Exception{
        userService.ZeroHealthStatus();
    }
}
