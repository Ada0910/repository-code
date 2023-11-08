package com.ada.software.design.chain.upgrade;

import com.ada.software.design.chain.FilterChain;
import com.ada.software.design.chain.PreparationList;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/6/28 19:57
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class HaveBreakfastFilter implements StudyPrepareFilter {

	@Override
	public void doFilter(PreparationList preparationList, FilterChain filterChain) {
		if (preparationList.isHaveBreakfast()) {
			System.out.println("吃完早饭");
		}

		filterChain.doFilter(preparationList, filterChain);
	}

}
