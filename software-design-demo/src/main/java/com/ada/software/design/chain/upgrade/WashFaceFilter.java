package com.ada.software.design.chain.upgrade;

import com.ada.software.design.chain.FilterChain;
import com.ada.software.design.chain.PreparationList;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 洗脸
 * <p/>
 *
 * @Date: 2023/6/28 19:56
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class WashFaceFilter implements StudyPrepareFilter {

	@Override
	public void doFilter(PreparationList preparationList, FilterChain filterChain) {
		if (preparationList.isWashFace()) {
			System.out.println("洗完脸");
		}

		filterChain.doFilter(preparationList, filterChain);
	}

}
