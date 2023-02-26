package com.ada.starter.split;


import com.ada.starter.config.AuthorInfoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 以“，”分割字符串的接口实现类
 * <p/>
 *
 * @Date: 2023/2/26 0:43
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class SplitServiceImpl implements ISplitService {

	@Autowired
	private AuthorInfoProperties authorInfo;


	@Override
	public List<String> split(String value) {
		System.out.println("作者是：" + authorInfo.getName() + ",年龄：" + authorInfo.getAge());
		return Stream.of(StringUtils.split(value, ",")).collect(Collectors.toList());
	}
}
