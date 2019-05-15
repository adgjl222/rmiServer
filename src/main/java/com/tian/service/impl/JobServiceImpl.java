package com.tian.service.impl;

import com.tian.dao.JobMapper;
import com.tian.model.Job;
import com.tian.service.JobService;
import com.tian.util.MemCacheManager;
import com.tian.util.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class JobServiceImpl implements JobService {
    private  static final Logger log = LoggerFactory.getLogger(JobServiceImpl.class);

    @Resource
    private JobMapper jobMapper;

    @Resource
    private RedisCache redisCache;

    MemCacheManager memCacheManager = MemCacheManager.getInstance();

    /**
     * 使用MemCache
     * @return
     */
    /*@Override
    public List<Job> select() {


        List<Job> JobList;
        if (memCacheManager.get("JobList") == null ){
            JobList = jobMapper.select();
            memCacheManager.set("JobList",JobList);
            log.info("本次查询从数据库中查询........");
        }else {
            JobList = (List <Job>) memCacheManager.get("JobList");
            log.info("本次查询从缓存中查询");
        }

        return JobList;
    }*/





    /**
     * 使用Redis进行缓存
     * @return
     */
    @Override
    public List<Job> select() {
        List<Job> JobList ;
        Object jobRedis = redisCache.lGet("jobRedis",0,-1);
        log.info(""+jobRedis);

        if ( jobRedis == (null) || ((List) jobRedis).size() == 0 ){
            JobList = jobMapper.select();
            //设置缓存2秒过期
            for (int i = 0; i < JobList.size();i++){
                redisCache.lSet("jobRedis",JobList.get(i));
            }


            log.info("本次查询从数据库中查询........");
        }else {
            JobList = (List<Job>) jobRedis;
            log.info("本次查询从redis缓存中查询");

        }

        return JobList;
    }

}
