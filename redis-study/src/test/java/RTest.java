import com.liang.redis.utils.RClient;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class RTest {
    @Resource
    private Redisson redisson;
    @Test
    void contextLoads() {
        RLock lock = redisson.getLock("lock");
        System.out.println("lock");
        boolean b = lock.tryLock();
        lock.unlock();
    }
}
