package com.ada.jvm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 配置以下的参数，打印对应的GC日志出来，进行调优：(JDK8默认parallel)
 *  -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -Xloggc:gc.log
 *
 * 如果要查看CMS的gc日志，可以用下面的命令：-XX:+UseConcMarkSweepGC -Xloggc:cms-gc.log
 *
 *
 *
 *
 */
@SpringBootApplication
public class JvmDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JvmDemoApplication.class, args);
	}

}
