package com.example.gifthavenbackend.gift;

import com.example.gifthavenbackend.entity.GiftsEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author 黎锦斌
 * * @date 2023/5/26
 */
public class GiftCrud {
    private static final Session session;

    /*
      创建session连接
     */
    static  {
        //加载 Hibernate 核心配置文件
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        //创建一个 SessionFactory 用来获取 Session 连接对象
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        //获取session 连接对象
        session = sessionFactory.openSession();
    }

    /**
     * 测试添加小礼品数据
     */
    @Test
    public void testInsert() {
        //开始事务
        Transaction transaction = session.beginTransaction();
        //创建实体类对象
        GiftsEntity giftsEntity = new GiftsEntity();
        giftsEntity.setName("测试3");
        giftsEntity.setDescription("这是测试数据");
        giftsEntity.setPrice(BigDecimal.valueOf(100.00));
        giftsEntity.setStock("4");
        giftsEntity.setTitle("测试数据的title");
        giftsEntity.setDeleted("0");
        //向 user 表中插入数据,返回值为新增数据的主键 id
        Serializable save = session.save(giftsEntity);
        System.out.println("新增数据的主键 id:"+save);
        //提交事务
        transaction.commit();
    }

    /**
     * 测试修改礼品数据
     */
    @Test
    public void testUpdate(){
        //开始事务
        Transaction transaction = session.beginTransaction();
        //查找实体对象
        GiftsEntity giftsEntity = session.get(GiftsEntity.class, 1);
        giftsEntity.setName("测试2");
        //直接调用 update() 方法进行修改
        session.update(giftsEntity);
        //提交事务
        transaction.commit();
    }

    /**
     * 查询礼品名称是测试的模糊查询
     */
    @Test
    public void testHqlQuery() {
        //创建 HQL 语句，语法与 SQL 类似，但操作的是实体类及其属性
        Query query = session.createQuery("from GiftsEntity where name like ?1 and deleted = '0'");
        query.setParameter(1, "%测试%");
        //获取结果集
        List<GiftsEntity> resultList = query.getResultList();
        //遍历结果集
        for (GiftsEntity giftsEntity : resultList) {
            System.out.println(giftsEntity);
        }
    }

    /**
     * 测试删除数据
     */
    @Test
    public void testDelete(){
        //开始事务
        Transaction transaction = session.beginTransaction();
        //查找对象
        GiftsEntity giftsEntity = session.get(GiftsEntity.class, 10);
        //设置删除
        giftsEntity.setDeleted("1");
        //保存
        session.update(giftsEntity);
        //提交事务
        transaction.commit();

    }
}
