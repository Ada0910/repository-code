package com.ada.netty.io.nio.buffer;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * 缓冲区的例子
 *
 * <p/>
 *
 * @Date: 2022/10/22 21:36
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class BufferDemo {
	/**
	 * 输出结果
	 *
	 * 记忆技巧：
	 * positition: read/get 0-4
	 *            flip/clear 4-0
	 *
	 * limit :flip 10-4
	 *        clear 4-10
	 *
	 *  	初始化 :
	 * 	capacity: 10, position: 0, limit: 10
	 *
	 * 	调用read() :
	 *
	 * 	capacity: 10, position: 4, limit: 10
	 *
	 *
	 * 	调用flip() :
	 * 	capacity: 10, position: 0, limit: 4
	 *
	 * 	调用get() :
	 * 	capacity: 10, position: 4, limit: 4
	 *
	 * 	调用clear() :
	 * 	capacity: 10, position: 0, limit: 10
	 */


	public static void main(String[] args) throws Exception {
		/*
		 *  这用用的是文件IO处理
		 *  比如，E盘中的test.txt文件中的内容是：【ada/】
		 */
		FileInputStream fin = new FileInputStream("E://test.txt");
		//创建文件的操作管道
		FileChannel fc = fin.getChannel();

		//分配一个10个大小缓冲区，说白了就是分配一个10个大小的byte数组
		ByteBuffer buffer = ByteBuffer.allocate(10);
		output("初始化", buffer);

		//先读一下
		fc.read(buffer);
		output("调用read()", buffer);

		//准备操作之前，先锁定操作范围
		buffer.flip();
		output("调用flip()", buffer);

		//判断有没有可读数据
		while (buffer.remaining() > 0) {
			byte b = buffer.get();
			// System.out.print(((char)b));
		}
		output("调用get()", buffer);

		//可以理解为解锁
		buffer.clear();
		output("调用clear()", buffer);

		//最后把管道关闭
		fin.close();
	}

	//把这个缓冲里面实时状态给答应出来
	public static void output(String step, ByteBuffer buffer) {
		System.out.println(step + " : ");
		//容量，数组大小
		System.out.print("capacity: " + buffer.capacity() + ", ");
		//当前操作数据所在的位置，也可以叫做游标
		System.out.print("position: " + buffer.position() + ", ");
		//锁定值，flip，数据操作范围索引只能在position - limit 之间
		System.out.println("limit: " + buffer.limit());
		System.out.println();
	}


}
