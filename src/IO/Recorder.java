package IO;

import java.io.*;

public class Recorder {
	// 定义一个变量记录最高分
	private static int maxscores_2048 = 0;

	public static int getMaxscores_2048() {
		return maxscores_2048;
	}

	public static void setMaxscores_2048(int maxscores_2048) {
		Recorder.maxscores_2048 = maxscores_2048;
	}

	// 定义文件输入流变量
	private static FileReader fr = null;
	private static BufferedReader br = null;
	private static FileWriter fw = null;
	private static BufferedWriter bw = null;

	/**
	 * 从文件中读取，记录
	 */
	public static void getRecording() {
		boolean flag = false;// 用于判断文件是否是新创建的
		try {
			File f = new File("src/myRecording.txt");
			if (f.exists()) {
				fr = new FileReader(f);
				br = new BufferedReader(fr);
				String n = br.readLine();
				maxscores_2048 = Integer.parseInt(n);
			} else {
				flag = f.createNewFile();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				// 关闭文件流的顺序，先开的后关闭
				if (!flag) {
					br.close();
					fr.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	/**
	 * 保存最高分纪录
	 */
	public static void keepRecording() {
		// 创建
		try {
			File f = new File("src/myRecording.txt");
			fw = new FileWriter(f);
			bw = new BufferedWriter(fw);
			bw.write(maxscores_2048 + "\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 关闭流
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 */
	public void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myDelFile = new File(filePath);
			myDelFile.delete();
			System.out.println("删除文件操作 成功执行");
		} catch (Exception e) {
			System.out.println("删除文件操作出错");
			e.printStackTrace();
		}
	}

	/**
	 * 修改文件
	 */
	public static void OprFile() {
		try {
			File f = new File("src/myRecording.txt");
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
			byte[] buff = new byte[(int) f.length()];
			bis.read(buff);
			FileOutputStream fos = new FileOutputStream(f);
			String[] lines = (new String(buff)).split("\n");
			for (String line : lines) {
				CharSequence formValue = null;
				CharSequence sourceValue = null;
				fos.write((line.replace(sourceValue, formValue) + "\n").getBytes());
			}
			fos.flush();
			fos.close();
			bis.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}