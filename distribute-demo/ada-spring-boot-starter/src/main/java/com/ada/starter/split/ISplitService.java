package com.ada.starter.split;

import java.util.List;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 以“，”分割字符串 的接口
 * <p/>
 *
 * @Date: 2023/2/26 0:42
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public interface ISplitService {
	List<String> split(String value);
}
