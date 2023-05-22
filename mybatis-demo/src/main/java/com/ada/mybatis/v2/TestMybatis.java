package com.ada.mybatis.v2;


import com.ada.mybatis.v1.mapper.Blog;
import com.ada.mybatis.v1.mapper.BlogMapper;
import com.ada.mybatis.v2.session.DefaultSqlSession;
import com.ada.mybatis.v2.session.SqlSessionFactory;

public class TestMybatis {

    public static void main(String[] args) {
        SqlSessionFactory factory = new SqlSessionFactory();
        DefaultSqlSession sqlSession = factory.build().openSqlSession();
        // 获取MapperProxy代理
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = mapper.selectBlogById(1);

        System.out.println("第一次查询: " + blog);
        System.out.println();
        blog = mapper.selectBlogById(1);
        System.out.println("第二次查询: " + blog);
    }
}
