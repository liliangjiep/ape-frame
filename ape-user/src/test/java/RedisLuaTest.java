import com.ape.user.UserApplication;
import com.ape.user.redislua.CompareAndSetLua;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/7/11 23:23
 * version :1.0
 **/
@SpringBootTest(classes = {UserApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class RedisLuaTest {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private CompareAndSetLua compareAndSetLua;

    @Test
    public void redisLuaTest() {
        ValueOperations<String, Long> opsForValue = redisTemplate.opsForValue();
        opsForValue.set("qj", 18L);
        log.info("qj的值为：{}", opsForValue.get("qj"));

        boolean result = compareAndSetLua.compareAndSet("qj", 18L, 19L);
        if (result) {
            log.info("修改成功！qj的值为：{}", opsForValue.get("qj"));
        }
    }

}
