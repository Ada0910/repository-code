package com.ada.spring.anno.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 通过实现ImportSelector 接口进行导入 
 * <p/>
 * <b>Creation Time:</b> 2023/4/4 17:59
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class ImportSelectorConfig  implements ImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[]{"com.ada.spring.anno.selector.ImportSelectorClass"};
	}
}
