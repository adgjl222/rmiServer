package com.tian.util;



import com.tian.model.User;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

import java.util.Date;

public class MemCacheManager {

    protected static MemCachedClient mcc = new MemCachedClient();


    protected  static MemCacheManager memCacheManager = new MemCacheManager();

    // 设置与缓存服务器的连接池
    static {
        // 服务器列表和其权重
        String[] servers = {"127.0.0.1:11211"};
        Integer[] weights = { 3 };

        // 获取socke连接池的实例对象
        SockIOPool pool = SockIOPool.getInstance();

        //设置服务器信息
        pool.setServers(servers);
        pool.setWeights(weights);

        //设置容错开关设置为TRUE，当当前socket不可用时，程序会自动查找可用连接并返回，否则返回NULL，默认状态是true，建议保持默认
        pool.setFailover( true );

        // 设置初始连接数、最小和最大连接数以及最大处理时间
        pool.setInitConn(5);
        pool.setMinConn(5);
        pool.setMaxConn(250);
        pool.setMaxIdle(1000 * 60 * 60 * 6);

        /**
         * 设置主线程的睡眠时间
         * 设置连接池维护线程的睡眠时间
         * 设置为0，维护线程不启动
         * 维护线程主要通过log输出socket的运行状况，监测连接数目及空闲等待时间等参数以控制连接创建和关闭。
         *
         */
        pool.setMaintSleep(30);


        // 设置TCP的参数，连接超时等
        // 设置是否使用Nagle算法，因为我们的通讯数据量通常都比较大（相对TCP控制数据）而且要求响应及时，因此该值需要设置为false（默认是true）
        pool.setNagle(false);
        // 设置scoket的读取与等待超时值
        pool.setSocketTO(3000);
        pool.setSocketConnectTO(0);

        /**
         * 设置连接心跳监测开关。
         * 设为true则每次通信都要进行连接是否有效的监测，造成通信次数倍增，加大网络负载，因此该参数应该在对HA要求比较高的场合设为TRUE，默认状态是false。
         */
        pool.setAliveCheck( false );


        // 初始化连接池
        pool.initialize();

        /*// 压缩设置，超过指定大小（单位为K）的数据都会被压缩
        mcc.setCompressEnable(true);
        mcc.setCompressThreshold(64 * 1024);*/

    }

    /**
     * 保护形构造方法，不允许实例化
     */
    protected MemCacheManager(){

    }

    /**
     * 获取唯一实例
     * @return
     */
    public static MemCacheManager getInstance(){
        return memCacheManager;
    }

    /**
     * 使用Memcached的add用于将 value(数据值) 存储在指定的 key(键) 中。
     * 控制台 没有则添加成功并提示STORED，有则失败并提示NOT_STORED 如value过期会更新
     * @param key
     * @param value
     * @return
     */
    public boolean add(String key,Object value){
        return mcc.add(key,value);
    }

    public boolean add(String key, Object value, Date expiry) {
        return mcc.add(key, value, expiry);
    }


    /**
     * 使用Memcached的set用于将 value(数据值) 存储在指定的 key(键) 中。
     * 没有就增加，有就覆盖，相当于更新
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key,Object value){
        return mcc.set(key,value);
    }


    public boolean append(String key,Object value) {
        return mcc.append(key,value);
    }

    /**
     *  replace 命令用于替换已存在的 key(键) 的 value(数据值)
     *  Key值不存在则会操作失败
     * @param key
     * @param value
     * @return
     */
    public boolean replace(String key,Object value){
        return mcc.replace(key, value);
    }

    public boolean replace(String key,Object value,Date expiry){
        return mcc.replace(key, value,expiry);
    }

    /**
     * 删除缓存中一个key的值
     * @param key
     * @return
     */
    public boolean delete(String key){
        return mcc.delete(key);
    }

    /**
     * 根据key获取对象
     * @param key
     * @return
     */
    public Object get(String key){
        return mcc.get(key);
    }


    //测试main方法
    public static void main(String[] args) {
        User user =  new User();
        MemCacheManager memCacheManager1 = MemCacheManager.getInstance();
        memCacheManager1.add("hello","hello是打招呼，你好的意思");
        System.out.println(" get value is "+memCacheManager1.get("hello"));
        memCacheManager1.append("hello","爱心");
        System.out.println(" get value is "+memCacheManager1.get("hello"));


    }


}
