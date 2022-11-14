package addAnnotation;

/**
 * 引数で渡されたjavaファイルのメソッドにWithSpanアノテーションを追加する
 * JavaParseを使用して、どこにメソッドが存在するかを確認し、その場所にひとつづつアノテーションを追加する
 * JavaParseでパースしたものから生成しないのは、改行位置等の元々のソースコードとの差異が出てしまうため。
 *
 */
public class App {
	public static void main(String[] args) {
		if (args.length <= 0) {
			System.out.println("ファイルが指定されていません。");
			System.exit(1);
		}
		final String path = args[0];

		final Main main = new Main();
		main.exec(path);
	}


}
