package com.msb;

import com.msb.bean.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class StartApp {

    /**
     * 1.有两个配置文件 加载解析  什么时候加载的，解析后的内容存储在内存中的哪个对象中？
     * 2.MyBatis是如何处理请求【jdbc操作】
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // 1.获取配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        // 2.加载解析配置文件并获取SqlSessionFactory对象 DefaultSqlSessionFactory
        // SqlSessionFactory 创建SqlSession的工厂对象
        // 1.创建了一个 DefaultSqlSessionFactory 对象
        // 2.解析全局配置文件 并将相关的信息保存在了 Configuration对象中
        //   解析映射文件，将相关的标签信息保存在了 Configuration对象中
         //  其中 select/insert/update/delete 相关的信息封装在了StatementMapper对象中
        //   而StatementMapper 对象保存在了 Configuration 对象中
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 3.根据SqlSessionFactory对象获取SqlSession对象
        // 创建了一个DefaultSqlSession 对象，同时根据指定的类型创建了 Executor 并有可能做了 缓存 和 插件的 增强
        SqlSession sqlSession = factory.openSession();
        // 4.通过SqlSession中提供的 API方法来操作数据库
        // 1级缓存 是session级别 缓存命中率很低
        // 2级缓存 进程级别      缓存命中率很高
        // 查询 1级缓存  再查询 2级缓存  还是没有就查询数据库 1
        // 查询 2级缓存  再查询 1级缓存  还是没有就查询数据库 2
        List<User> list = sqlSession.selectList("com.msb.bean.UserMapper.selectUser");
        for (User user : list) {
            System.out.println(user);
        }
        System.out.println("------------------");
        list = sqlSession.selectList("com.msb.bean.UserMapper.selectUser");
        for (User user : list) {
            System.out.println(user);
        }
        // 5.关闭会话
        sqlSession.close();
        sqlSession = factory.openSession();
        list = sqlSession.selectList("com.msb.bean.UserMapper.selectUser");
        for (User user : list) {
            System.out.println(user);
        }
        sqlSession.close();

    }
}
