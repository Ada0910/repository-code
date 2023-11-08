package com.ada.software.design.chain.upgrade;

import com.ada.software.design.chain.FilterChain;
import com.ada.software.design.chain.PreparationList;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 一个学习的接口
 * <p/>
 * <b>Creation Time:</b> 2023/6/28 16:42
 * @author xiewn
 * @version 1.0.0.1
 *
 */
public interface StudyPrepareFilter {
	public void doFilter(PreparationList preparationList, FilterChain filterChain);
}
