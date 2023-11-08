package com.ada.mybatis.v1.mapper;


import com.ada.mybatis.v2.annotation.Entity;
import com.ada.mybatis.v2.annotation.Select;

@Entity(Blog.class)
public interface BlogMapper {
    /**
     * 根据主键查询文章
     * @param bid
     * @return
     */
    @Select("select * from blog where bid = %d")
    public Blog selectBlogById(Integer bid);

}
