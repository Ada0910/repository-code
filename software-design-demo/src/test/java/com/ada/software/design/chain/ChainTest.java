package com.ada.software.design.chain;

import com.ada.software.design.chain.upgrade.HaveBreakfastFilter;
import com.ada.software.design.chain.upgrade.StudyPrepareFilter;
import com.ada.software.design.chain.upgrade.WashFaceFilter;
import com.ada.software.design.chain.upgrade.WashHairFilter;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/6/28 19:58
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ChainTest {
	public static void main(String[] args) {
		PreparationList preparationList = new PreparationList();
		preparationList.setWashFace(true);
		preparationList.setWashHair(false);
		preparationList.setHaveBreakfast(true);

		Study study = new Study();

		StudyPrepareFilter washFaceFilter = new WashFaceFilter();
		StudyPrepareFilter washHairFilter = new WashHairFilter();
		StudyPrepareFilter haveBreakfastFilter = new HaveBreakfastFilter();

		FilterChain filterChain = new FilterChain(study);
		filterChain.addFilter(washFaceFilter);
		filterChain.addFilter(washHairFilter);
		filterChain.addFilter(haveBreakfastFilter);

		filterChain.doFilter(preparationList, filterChain);
	}
}
