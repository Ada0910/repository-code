package com.ada.simple.base.inner;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 内置类的使用
 * <p/>
 *
 * @Date: 2023/8/13 16:47
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class PublicClass {

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "PublicClass{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}

	//加上static就是静态内置类
	static class PrivateClass {
		private String age;
		private String address;

		public String getAge() {
			return age;
		}

		public void setAge(String age) {
			this.age = age;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		@Override
		public String toString() {
			return "PrivateClass{" +
					"age='" + age + '\'' +
					", address='" + address + '\'' +
					'}';
		}
	}

	public static void main(String[] args) {
		PublicClass publicClass = new PublicClass();
		publicClass.setUsername("ada");
		publicClass.setPassword("666");
		System.out.println(publicClass.toString());

		//PrivateClass privateClass = publicClass.new PrivateClass();
		//静态内置类可以用下面这个
		PrivateClass privateClass = new PrivateClass();
		privateClass.setAge("28");
		privateClass.setAddress("广州");
		System.out.println(privateClass.toString());
	}
}
