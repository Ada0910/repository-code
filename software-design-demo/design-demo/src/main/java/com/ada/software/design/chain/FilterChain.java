package com.ada.software.design.chain;

import com.ada.software.design.chain.upgrade.StudyPrepareFilter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2023/6/28 16:43
 * @author xiewn
 * @version 1.0.0.1
 *
 */
public class FilterChain implements StudyPrepareFilter {
	private int pos = 0;

	private Study study;

	private List<StudyPrepareFilter> studyPrepareFilterList;

	public FilterChain(Study study) {
		this.study = study;
	}

	public void addFilter(StudyPrepareFilter studyPrepareFilter) {
		//为空判断
		if (studyPrepareFilterList == null) {
			studyPrepareFilterList = new ArrayList<>();
		}
		studyPrepareFilterList.add(studyPrepareFilter);
	}

	@Override
	public void doFilter(PreparationList preparationList, FilterChain filterChain) {
		// 所有过滤器执行完毕
		if (studyPrepareFilterList.size() == pos) {
			study.study();
		}
		studyPrepareFilterList.get(pos++).doFilter(preparationList, filterChain);
	}
}
