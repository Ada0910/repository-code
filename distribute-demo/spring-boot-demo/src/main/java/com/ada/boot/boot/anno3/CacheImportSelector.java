package com.ada.boot.boot.anno3;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;


/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/11/6 17:34
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class CacheImportSelector implements ImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata annotationMetadata) {
		Map<String, Object> attributes = annotationMetadata.getAnnotationAttributes(EnableDefineService.class.getName());
		// 先写死
		return new String[]{CacheService.class.getName()};

	}
}
