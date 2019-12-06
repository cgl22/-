package test;

/**
 * @author CGL
 */
public class Book {
	public static void main(String[] args) {
		staticFunction();
	}

	static {
		System.out.println("书的静态代码块");
	}

	static final Book book = new Book();

	{
		System.out.println("书的普通代码块");
	}

	Book() {
		System.out.println("书的构造方法");
		System.out.println("price=" + price + ",amount=" + amount);
	}

	public static void staticFunction() {
		System.out.println("书的静态方法");
	}

	int price = 110;
	static String amount = "112";
}
