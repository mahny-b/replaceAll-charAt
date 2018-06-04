package jp.gr.mahny_conf.replaceall_charat;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

/**
 * @author mahny
 *
 */
public class SpeedCrazyTest {

	/**
	 * {@link jp.gr.mahny_conf.replaceall_charat.SpeedCrazyCharAt#test(java.lang.String)} と
	 * {@link jp.gr.mahny_conf.replaceall_charat.SpeedCrazyReplaceAll#test(java.lang.String)} のためのテスト・メソッド。
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@Test
	public void testTest() throws FileNotFoundException, IOException {
		File f = new File("resources", "test.txt");
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

		// 3つのメソッドは同じ文字列を返すよね！
		String resultOfCharAt = SpeedCrazyCharAt.test(testData);
		assertThat(resultOfCharAt, is(SpeedCrazyReplaceAll.test(testData)));
		assertThat(resultOfCharAt, is(SpeedCrazyPattern.test(testData)));
	}

}
