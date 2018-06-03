package jp.gr.mahny_conf.replaceall_charat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * {@link String#charAt(int)}で制御文字を見つけて制御文字を除去した文字列を作るよ！
 * @author mahny
 *
 */
public class SpeedCrazyCharAt {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		File f = new File("resources", "test.txt");
		if (!f.exists()) {
			System.out.println("テストデータが無い！ / file=[" + f.getAbsolutePath() + "]");
			return;
		}

		String testData = null;
		try (FileInputStream in = new FileInputStream(f);
				ByteArrayOutputStream byteArrays = new ByteArrayOutputStream()) {

			byte[] buff = new byte[4096];
			int size = in.read(buff);
			while (size != -1) {
				byteArrays.write(buff, 0, size);
				size = in.read(buff);
			}
			byteArrays.flush();
			testData = new String(byteArrays.toByteArray());
		}

		int trials = 1000;
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < trials; i++) {
			test(testData);
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;

		System.out.println("合計時間 : " + totalTime + "(ms), 平均時間 : " + (totalTime / trials) + "(ms)");
	}

	/**
	 * 指定された文字列から制御文字を除去した文字列を生成する
	 * @param org 指定された文字
	 * @return 制御文字を除去した文字列
	 */
	public static String test(String org) {
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < org.length(); j++) {
			char c = org.charAt(j);
			if (0x1f < c && c != 0x7f) {
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
