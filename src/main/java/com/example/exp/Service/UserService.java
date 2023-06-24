package com.example.exp.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.exp.Entity.User;
import com.example.exp.Mapper.UserMapper;
import com.example.exp.Pojo.UpdateReq;
import com.example.exp.ResponseResult.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Description: UserService
 * @Author: zsp
 * @Date: 2023/6/23 0:49
 */
@Service
@Slf4j
public class UserService {


    /**
     * spring事务失效的场景
     * 1. 方法内的自调用
     * 2. 方法是private的
     * 3. 方法是final的
     * 4. 单独的线程调用方法
     * 5. 异常被吃掉
     * 6. 类没有被spring管理
     */

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Resource
    private CacheManager cacheManager;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 编程式事务该如何去实现？
     */
    public void save(User user) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            userMapper.insert(user);
//            int a = 10/0;
            transactionManager.commit(status);
        } catch (Exception e) {
            log.error(e.getMessage());
            transactionManager.rollback(status);
        }
    }


    @Transactional(propagation= Propagation.REQUIRED,timeout=5)
    public void save2(User user) {
        try {
            userMapper.insert(user);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public ResponseResult<User> search(Integer id){
        Object o = redisTemplate.opsForValue().get("user_" + id);
        if (o != null) {
            return ResponseResult.success((User) o,"缓存命中查找成功") ;
        } else {
            // 如果缓存中没有，则从数据库查询
            User user = userMapper.selectById(id);
            if (user != null) {
                // 将查询结果存储到缓存中，并设置有效期为1天
                redisTemplate.opsForValue().set("user_"+id, user,1, TimeUnit.DAYS);
                return ResponseResult.success(user,"查找成功");
            } else {
                return ResponseResult.fail("查找失败");
            }
        }
    }

    public ResponseResult updateUser(UpdateReq req) {
        User user = new User();
        user.setName(req.getName()).setAge(req.getAge()).setGrade(req.getGrade());
        userMapper.update(user, new QueryWrapper<User>().eq("id",req.getId()));
        // 更新数据库数据后进行缓存更新
//        redisTemplate.opsForValue().set("user_" + req.getId(), user);
        redisTemplate.delete("comment_" + req.getId());
        return ResponseResult.success();
    }



//    @Autowired
//    private TransactionTemplate transactionTemplate;
//
//    public void save(User user) {
//        jdbcTemplate.execute("INSERT INTO user (id, name) VALUES (5, 'Jack5')");
//
//        transactionTemplate.execute((status) -> {
//            jdbcTemplate.execute("INSERT INTO user (id, name) VALUES (6, 'Jack6')");
//            int i = 1 / 0;
//            return Boolean.TRUE;
//        });
//
//    }


}
