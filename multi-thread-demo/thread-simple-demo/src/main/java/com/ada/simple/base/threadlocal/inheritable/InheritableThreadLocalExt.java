package com.ada.multi.thread.base.threadlocal.inheritable;

import java.util.Date;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/24 23:26
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class InheritableThreadLocalExt  extends  InheritableThreadLocal{
	@Override
	protected Object initialValue() {
		return new Date().getTime();
	}

	@Override
	protected Object childValue(Object parentValue) {
		return parentValue +"我在子线程中加的哦~";
	}
}
