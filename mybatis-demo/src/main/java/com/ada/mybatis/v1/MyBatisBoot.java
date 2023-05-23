package com.ada.mybatis.v1;


import com.ada.mybatis.v1.mapper.BlogMapper;

/**
 *  v1版本测试类
 */
public class MyBatisBoot {
    public static void main(String[] args) {
        // 先构建一个会话类，然后获取一个配置类和执行器
        MySqlSession sqlSession = new MySqlSession(new MyConfiguration(), new MyExecutor());
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        blogMapper.selectBlogById(1);
    }
}
