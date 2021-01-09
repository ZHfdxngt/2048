package Interface;

import Interface.Register;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.swing.*;
import IO.Recorder;

public class Game extends JFrame {
	public Game() {
	}
	MyPanel my = null;
	public void Game() {
		my = new MyPanel();

		getContentPane().add(my);

		this.addKeyListener(my);

		this.setTitle("2048游戏");
		this.setLocation(400, 30);
		this.setSize(500, 800);
		
		this.setResizable(false);// 禁止调整窗口大小
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.setLayout(null);// 设置空布局
		
		this.setVisible(true);
	}

}
/**
 * 这是游戏界面的设置
 * @author 27402
 *
 */
class MyPanel extends JPanel implements KeyListener {

	private JButton jb1;
	private static boolean jb1_flag = true;//声音按键
	private boolean jb1_flag2 = true;

	public static boolean isJb1_flag() {
		return jb1_flag;
	}

	public static void setJb1_flag(boolean jb1_flag) {
		MyPanel.jb1_flag = jb1_flag;
	}

	private JPanel scoresPane;// 分数门面
	private JPanel mainPane;// 游戏主界面
	private JLabel labelMaxScores;// "最高分"标签
	private JLabel m2048;// 显示2048
	private JLabel currentScores;// 当前得分
	private JLabel [][] texts;// 创建文本框二维数组
	private int times = 16;// 记录剩余空方块数目
	private int scores = 0;// 记录分数
	private int maxscores = 0;
	private int p1 = 0, p2 = 0, p3 = 0, p4 = 0;// 用于判断是否能继续生成随机数
	private int biaoji = 1; // 标记能否移动
	Font font2 = new Font("", Font.BOLD, 30);// 主面板的数字字体的设置
    
	
	// 游戏界面
	public MyPanel() {
		super();
		// 创建主件
		this.setLayout(null);
		//this.setBackground(new Color(2, 150, 220));
		
		//分数面板
		scoresPane = new JPanel();
		scoresPane.setBackground(Color.white);// 设置分数面板的背景颜色
		scoresPane.setBounds(0, 0, 500, 100);// 设置分数面板的位置和长宽

		this.add(scoresPane);// 添加分数面板设置为空
		scoresPane.setLayout(null);

		// 2048标签
		m2048 = new JLabel("2048");
		m2048.setFont(new Font("楷体", Font.BOLD, 45));
		m2048.setBounds(20, 0, 120, 100);
		scoresPane.add(m2048);
		
		// 分数标签
		currentScores = new JLabel();
		currentScores.setText("<html>分数<BR>" + String.valueOf(scores) + "</html>");
		currentScores.setFont(new Font("楷体", Font.BOLD, 30));
		currentScores.setBounds(170, 0, 140, 100);
		scoresPane.add(currentScores);
		
		// 最高分标签
		Recorder.getRecording();
		maxscores = Recorder.getMaxscores_2048();
		labelMaxScores = new JLabel("<html>最高分<br>" + String.valueOf(maxscores) + "<html>");
		labelMaxScores.setFont(new Font("楷体", Font.BOLD, 30));
		labelMaxScores.setBounds(320, 0, 120, 100);
		scoresPane.add(labelMaxScores);
		
		// 声音按键
		jb1 = new JButton(new ImageIcon("src/声音图标.jpg"));
		jb1.setBounds(433, 120, 45, 30);
		this.add(jb1);
		jb1.setActionCommand("shengyin");
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getActionCommand().equals("shengyin")) {
					if (jb1_flag2) {
						setJb1_flag(false);
						jb1_flag2 = false;
						jb1.setIcon(new ImageIcon("src/静音图标.jpg"));
						jb1.requestFocus(false);// 让按钮失去焦点
					} else {
						setJb1_flag(true);
						jb1_flag2 = true;
						jb1.setIcon(new ImageIcon("src/声音图标.jpg"));
						jb1.requestFocus(false);// 让按钮失去焦点
					}
					jb1.setFocusable(false);// 设置焦点，不能用请求焦点
				}
			}
		});
        
	
		
	    // 游戏面板
		mainPane = new JPanel();
		mainPane.setBounds(18, 150, 460, 460);
		mainPane.setBackground(Color.BLACK);
		this.add(mainPane);
		mainPane.setLayout(null);

		
		
        //文本框二维数组
		texts = new JLabel[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				texts[i][j] = new JLabel();
				texts[i][j].setFont(font2);//数字字体设置
				texts[i][j].setHorizontalAlignment(SwingConstants.CENTER);// 设置标签内容沿X轴的对齐方式,某区域的中心位置
				texts[i][j].setText("");
				texts[i][j].setBounds(110 * j + 15, 110 * i + 15, 100, 100);
				setColor(i, j, texts[i][j].getText());
				texts[i][j].setOpaque(true);// 设置控件不透明
				mainPane.add(texts[i][j]);
			}
		}
		
		// 定义音频
		AePlayWave apw = new AePlayWave("src/模板背景音乐.wav");
		apw.start();
		
		//在随机产生的两个位置上随机产生两个未知数2或4
		Create();
		Create();

	}

	@Override
	/**
	 * 当key被按下时
	 */
	public void keyPressed(KeyEvent e) {
		do_label_keyPressed(e);
	}

	@Override
	/**
	 * 当key被释放时
	 */
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * 当key被键入时
	 */
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	
	
	//设置方块颜色
	public void setColor(int i, int j, String str) {
		if ("".equals(str)) {
			texts[i][j].setBackground(Color.white);//方格中无内容时为白色
			return;
		}

		int result = Integer.parseInt(str);
		//System.out.println(result);//测试
		switch (result) {
		case 2:
			texts[i][j].setBackground(Color.yellow);//黄色
			break;
		case 4:
			texts[i][j].setBackground(Color.pink);//粉色
			break;
		case 8:
			texts[i][j].setBackground(new Color(240, 150, 10));//橙色
			break;
		case 16:
			texts[i][j].setBackground(Color.green);//绿色
			break;
		case 32:
			texts[i][j].setBackground(Color.magenta);//紫红色
			break;
		case 64:
			texts[i][j].setBackground(new Color(155, 10, 190));//紫色
			break;
		case 128:
			texts[i][j].setBackground(Color.blue);//蓝色
			break;
		case 256:
			texts[i][j].setBackground(Color.gray);//灰色
			break;
		case 512:
			texts[i][j].setBackground(Color.cyan);//青色
			break;
		case 1024:
			texts[i][j].setBackground(Color.DARK_GRAY);//深灰色
			break;
		case 2048:
			texts[i][j].setBackground(Color.red);//红色
			break;
		case 4096:
			texts[i][j].setBackground(Color.white);//白色
			break;
		default:
			break;
		}
	}
	
	// 按键输入事件的处理方法
	public void do_label_keyPressed(KeyEvent e) {
		int code = e.getKeyCode();// 获取按键代码
		int a;//防止连加
		String str;//当前方格
		String str1;//左方格
		int num;//当前方格的数字

		switch (code) {
		//左移
		case KeyEvent.VK_LEFT:
			biaoji = 0; // 此时不能左移
			for (int i = 0; i < 4; i++) {
				a = 5;
				for (int k = 0; k < 3; k++) { // 让左边每2个能合并的全合并了
					for (int j = 1; j < 4; j++) {// 遍历16个方块
						str = texts[i][j].getText();// 获取当前方块标签文本字符
						str1 = texts[i][j - 1].getText();// 获取当前左1方块标签文本字符
                       
						//左方格文本字符为空的情况
						if (str1.compareTo("") == 0) {
							if (str.compareTo("") != 0)// 如果左1方块文本为空字符，且当前方格文本不为空字符
								biaoji = 1; // 标记，能向左移
							texts[i][j - 1].setText(str);// 字符左移
							setColor(i, j - 1, str);//颜色改变
							texts[i][j].setText("");// 当前方块字符置空
							setColor(i, j, "");//改颜色为白
						} 
						
						//左、右方格文本字符相同的情况
						else if ((str.compareTo(str1) == 0) && (j != a) && (j != a - 1)) {//保证先合并再左移
							num = Integer.parseInt(str);
							scores += num;//记录成绩
							times++;//当前剩余空格数加1
							str = String.valueOf(2 * num); // 返回 int参数的字符串表示形式
							texts[i][j - 1].setText(str);// 左1方块文本字符变为两方块之和
							setColor(i, j - 1, str);//改变颜色
							texts[i][j].setText("");// 当前方块字符置空
							setColor(i, j, "");//改颜色为白
							a = j;
							biaoji = 1;//标记此时左边能合并
						}
					}
				}
			}
			p1 = 1;//表示还能继续生成随机数
			Create();
			break;

		case KeyEvent.VK_RIGHT:
			biaoji = 0;
			for (int i = 0; i < 4; i++) {
				a = 5;
				for (int k = 0; k < 3; k++) {
					for (int j = 2; j >= 0; j--) {
						str = texts[i][j].getText();
						str1 = texts[i][j + 1].getText();

						if (str1.compareTo("") == 0) {
							if (str.compareTo("") != 0)
								biaoji = 1;
							texts[i][j + 1].setText(str);
							setColor(i, j + 1, str);
							texts[i][j].setText("");
							setColor(i, j, "");
						} else if (str.compareTo(str1) == 0 && j != a && j != a + 1) {
							num = Integer.parseInt(str);
							scores += num;
							times++;
							str = String.valueOf(2 * num);
							texts[i][j + 1].setText(str);
							setColor(i, j + 1, str);
							texts[i][j].setText("");
							setColor(i, j, "");
							a = j;
							biaoji = 1;
						}
					}
				}
			}
			p2 = 1;
			Create();
			break;

		case KeyEvent.VK_UP:
			biaoji = 0;
			for (int j = 0; j < 4; j++) {
				a = 5;
				for (int k = 0; k < 3; k++) {
					for (int i = 1; i < 4; i++) {
						str = texts[i][j].getText();
						str1 = texts[i - 1][j].getText();

						if (str1.compareTo("") == 0) {
							if (str.compareTo("") != 0)
								biaoji = 1;
							texts[i - 1][j].setText(str);
							setColor(i - 1, j, str);
							texts[i][j].setText("");
							setColor(i, j, "");
						} else if (str.compareTo(str1) == 0 && i != a && i != a - 1) {
							num = Integer.parseInt(str);
							scores += num;
							times++;
							str = String.valueOf(2 * num);
							texts[i - 1][j].setText(str);
							setColor(i - 1, j, str);
							texts[i][j].setText("");
							setColor(i, j, "");
							a = i;
							biaoji = 1;
						}
					}
				}
			}
			p3 = 1;
			Create();
			break;

		case KeyEvent.VK_DOWN:
			biaoji = 0;
			for (int j = 0; j < 4; j++) {
				a = 5;
				for (int k = 0; k < 5; k++) {
					for (int i = 2; i >= 0; i--) {
						str = texts[i][j].getText();
						str1 = texts[i + 1][j].getText();

						if (str1.compareTo("") == 0) {
							if (str.compareTo("") != 0)
								biaoji = 1;
							texts[i + 1][j].setText(str);
							setColor(i + 1, j, str);
							texts[i][j].setText("");
							setColor(i, j, "");
						} else if (str.compareTo(str1) == 0 && i != a && i != a + 1) {
							num = Integer.parseInt(str);
							scores += num;
							times++;
							str = String.valueOf(2 * num);
							texts[i + 1][j].setText(str);
							setColor(i + 1, j, str);
							texts[i][j].setText("");
							setColor(i, j, "");
							a = i;
							biaoji = 1;
						}
					}
				}
			}
			p4 = 1;
			Create();
			break;

		default:
			break;
		}
        
		//判断此局游戏分数是否为最高分
		if (maxscores <= scores) {
			maxscores = scores;
			Recorder.setMaxscores_2048(maxscores);
			Recorder.keepRecording();
		}
		currentScores.setText("<html>分数<BR>" + String.valueOf(scores) + "</html>");//当前的分标签
		labelMaxScores.setText("<html>最高分<br>" + String.valueOf(maxscores) + "</html>");// 最高得分标签
	}

	
	
	/**
	 * 随机位置产生随机的2或4，并判断是否结束
	 */
	public void Create() {
		int i, j;
		boolean r = true;
		String str;
		Random random = new Random();
		if (times > 0 && biaoji == 1) {//当前有空余格子，能移动，则生成随机数
			while (r) {
				i = random.nextInt(4);
				j = random.nextInt(4);
				str = texts[i][j].getText();
				//进行一个判断，随机抽取的方格是否否为空
				if ((str.compareTo("") == 0)) {
					int ima = 2 * (int) (1 + Math.random() * 2);
					String imass = String.valueOf(ima);
					texts[i][j].setText(imass);
					setColor(i, j, imass);
					times--;
					r = false; 
					p1 = p2 = p3 = p4 = 0;
				}
			}
		} else if (p1 > 0 && p2 > 0 && p3 > 0 && p4 > 0) {//p不能为0时，则表示不能生成随机数，则游戏结束
			JOptionPane.showMessageDialog(null, "游戏结束!");
            
			Game RLJ=new Game();
			RLJ.setFocusable(true);
			RLJ.Game();
		}
	}
}

/**
 * 播放声音的类
 * @author 27402
 *
 */
class AePlayWave extends Thread {
	private static String filename;

	public AePlayWave(String wavefile) {
		filename = wavefile;
	}
	//多线程
	public void run() {
		while (true) {
			File soundFile = new File(filename);
			AudioInputStream audioInputStream = null;
			try {
				audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}

			AudioFormat format = audioInputStream.getFormat();
			SourceDataLine auline = null;
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

			try {
				auline = (SourceDataLine) AudioSystem.getLine(info);
				auline.open();
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}

			auline.start();
			int nBytesRead = 0;
			byte[] abData = new byte[1024];
			try {
				while (nBytesRead != -1) {
					nBytesRead = audioInputStream.read(abData);
					if (MyPanel.isJb1_flag()) {
						if (nBytesRead >= 0) {
							auline.write(abData, 0, nBytesRead);
						}
					} else {
						while (!MyPanel.isJb1_flag())
							;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return;
			} finally {
				auline.drain();
				auline.drain();
			}
		}
	}
}
