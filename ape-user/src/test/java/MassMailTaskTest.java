import com.alibaba.fastjson.JSON;
import com.ape.user.UserApplication;
import com.ape.user.delayQueue.MassMailTask;
import com.ape.user.delayQueue.MassMailTaskService;
import com.ape.util.RedisShareLockUtil;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.UUID;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/7/11 21:18
 * version :1.0
 **/
@SpringBootTest(classes = {UserApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class MassMailTaskTest {
    @Resource
    private MassMailTaskService massMailTaskService;

    @Resource
    private RedisShareLockUtil redisShareLockUtil;

    @Test
    public void push() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        MassMailTask massMailTask = new MassMailTask();
        massMailTask.setTaskId(2L);
        massMailTask.setStartTime(simpleDateFormat.parse("2024-07-11 22:21:00"));
        massMailTaskService.pushMassMailTaskQueue(massMailTask);
        log.info("定时任务已插入！");
    }

    @Test
    public void deal() {
        String lockKey = "test.delay.task";
        String requestId = UUID.randomUUID().toString();
        try {
            boolean locked = redisShareLockUtil.lock(lockKey, requestId, 5L);
            if (!locked) {
                return;
            }
            Set<Long> taskIdSet = massMailTaskService.poolMassMailTaskQueue();
            log.info("DelayTaskTest.deal.taskIdSet:{}", JSON.toJSON(taskIdSet));
            if (CollectionUtils.isEmpty(taskIdSet)) {
                return;
            }
            // 执行其他的业务逻辑
        } catch (Exception e) {
            log.error("延时任务拉取执行失败：{}", e.getMessage(), e);
        } finally {
            redisShareLockUtil.unLock(lockKey, requestId);
        }
    }
}
