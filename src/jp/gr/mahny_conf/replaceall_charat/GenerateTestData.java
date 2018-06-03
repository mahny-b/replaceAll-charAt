package jp.gr.mahny_conf.replaceall_charat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * テストデータ生成用クラス
 * @author mahny
 *
 */
public class GenerateTestData {

	/**
	 * 起動用
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {

		File f = new File("resources", "test.txt");
		if (f.exists()) {
			f.delete();
		}

		try (FileOutputStream out = new FileOutputStream(f)) {
			int dataSize = 1024 * 1024;
			Random random = new Random();
			for (int i = 0; i < dataSize; i++) {
				char c = (char) random.nextInt(128);
				out.write(c);
			}
			out.flush();
		}
	}
}
