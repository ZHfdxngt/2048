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

		this.setTitle("2048��Ϸ");
		this.setLocation(400, 30);
		this.setSize(500, 800);
		
		this.setResizable(false);// ��ֹ�������ڴ�С
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.setLayout(null);// ���ÿղ���
		
		this.setVisible(true);
	}

}
/**
 * ������Ϸ���������
 * @author 27402
 *
 */
class MyPanel extends JPanel implements KeyListener {

	private JButton jb1;
	private static boolean jb1_flag = true;//��������
	private boolean jb1_flag2 = true;

	public static boolean isJb1_flag() {
		return jb1_flag;
	}

	public static void setJb1_flag(boolean jb1_flag) {
		MyPanel.jb1_flag = jb1_flag;
	}

	private JPanel scoresPane;// ��������
	private JPanel mainPane;// ��Ϸ������
	private JLabel labelMaxScores;// "��߷�"��ǩ
	private JLabel m2048;// ��ʾ2048
	private JLabel currentScores;// ��ǰ�÷�
	private JLabel [][] texts;// �����ı����ά����
	private int times = 16;// ��¼ʣ��շ�����Ŀ
	private int scores = 0;// ��¼����
	private int maxscores = 0;
	private int p1 = 0, p2 = 0, p3 = 0, p4 = 0;// �����ж��Ƿ��ܼ������������
	private int biaoji = 1; // ����ܷ��ƶ�
	Font font2 = new Font("", Font.BOLD, 30);// �������������������
    
	
	// ��Ϸ����
	public MyPanel() {
		super();
		// ��������
		this.setLayout(null);
		//this.setBackground(new Color(2, 150, 220));
		
		//�������
		scoresPane = new JPanel();
		scoresPane.setBackground(Color.white);// ���÷������ı�����ɫ
		scoresPane.setBounds(0, 0, 500, 100);// ���÷�������λ�úͳ���

		this.add(scoresPane);// ��ӷ����������Ϊ��
		scoresPane.setLayout(null);

		// 2048��ǩ
		m2048 = new JLabel("2048");
		m2048.setFont(new Font("����", Font.BOLD, 45));
		m2048.setBounds(20, 0, 120, 100);
		scoresPane.add(m2048);
		
		// ������ǩ
		currentScores = new JLabel();
		currentScores.setText("<html>����<BR>" + String.valueOf(scores) + "</html>");
		currentScores.setFont(new Font("����", Font.BOLD, 30));
		currentScores.setBounds(170, 0, 140, 100);
		scoresPane.add(currentScores);
		
		// ��߷ֱ�ǩ
		Recorder.getRecording();
		maxscores = Recorder.getMaxscores_2048();
		labelMaxScores = new JLabel("<html>��߷�<br>" + String.valueOf(maxscores) + "<html>");
		labelMaxScores.setFont(new Font("����", Font.BOLD, 30));
		labelMaxScores.setBounds(320, 0, 120, 100);
		scoresPane.add(labelMaxScores);
		
		// ��������
		jb1 = new JButton(new ImageIcon("src/����ͼ��.jpg"));
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
						jb1.setIcon(new ImageIcon("src/����ͼ��.jpg"));
						jb1.requestFocus(false);// �ð�ťʧȥ����
					} else {
						setJb1_flag(true);
						jb1_flag2 = true;
						jb1.setIcon(new ImageIcon("src/����ͼ��.jpg"));
						jb1.requestFocus(false);// �ð�ťʧȥ����
					}
					jb1.setFocusable(false);// ���ý��㣬���������󽹵�
				}
			}
		});
        
	
		
	    // ��Ϸ���
		mainPane = new JPanel();
		mainPane.setBounds(18, 150, 460, 460);
		mainPane.setBackground(Color.BLACK);
		this.add(mainPane);
		mainPane.setLayout(null);

		
		
        //�ı����ά����
		texts = new JLabel[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				texts[i][j] = new JLabel();
				texts[i][j].setFont(font2);//������������
				texts[i][j].setHorizontalAlignment(SwingConstants.CENTER);// ���ñ�ǩ������X��Ķ��뷽ʽ,ĳ���������λ��
				texts[i][j].setText("");
				texts[i][j].setBounds(110 * j + 15, 110 * i + 15, 100, 100);
				setColor(i, j, texts[i][j].getText());
				texts[i][j].setOpaque(true);// ���ÿؼ���͸��
				mainPane.add(texts[i][j]);
			}
		}
		
		// ������Ƶ
		AePlayWave apw = new AePlayWave("src/ģ�屳������.wav");
		apw.start();
		
		//���������������λ���������������δ֪��2��4
		Create();
		Create();

	}

	@Override
	/**
	 * ��key������ʱ
	 */
	public void keyPressed(KeyEvent e) {
		do_label_keyPressed(e);
	}

	@Override
	/**
	 * ��key���ͷ�ʱ
	 */
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * ��key������ʱ
	 */
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	
	
	//���÷�����ɫ
	public void setColor(int i, int j, String str) {
		if ("".equals(str)) {
			texts[i][j].setBackground(Color.white);//������������ʱΪ��ɫ
			return;
		}

		int result = Integer.parseInt(str);
		//System.out.println(result);//����
		switch (result) {
		case 2:
			texts[i][j].setBackground(Color.yellow);//��ɫ
			break;
		case 4:
			texts[i][j].setBackground(Color.pink);//��ɫ
			break;
		case 8:
			texts[i][j].setBackground(new Color(240, 150, 10));//��ɫ
			break;
		case 16:
			texts[i][j].setBackground(Color.green);//��ɫ
			break;
		case 32:
			texts[i][j].setBackground(Color.magenta);//�Ϻ�ɫ
			break;
		case 64:
			texts[i][j].setBackground(new Color(155, 10, 190));//��ɫ
			break;
		case 128:
			texts[i][j].setBackground(Color.blue);//��ɫ
			break;
		case 256:
			texts[i][j].setBackground(Color.gray);//��ɫ
			break;
		case 512:
			texts[i][j].setBackground(Color.cyan);//��ɫ
			break;
		case 1024:
			texts[i][j].setBackground(Color.DARK_GRAY);//���ɫ
			break;
		case 2048:
			texts[i][j].setBackground(Color.red);//��ɫ
			break;
		case 4096:
			texts[i][j].setBackground(Color.white);//��ɫ
			break;
		default:
			break;
		}
	}
	
	// ���������¼��Ĵ�����
	public void do_label_keyPressed(KeyEvent e) {
		int code = e.getKeyCode();// ��ȡ��������
		int a;//��ֹ����
		String str;//��ǰ����
		String str1;//�󷽸�
		int num;//��ǰ���������

		switch (code) {
		//����
		case KeyEvent.VK_LEFT:
			biaoji = 0; // ��ʱ��������
			for (int i = 0; i < 4; i++) {
				a = 5;
				for (int k = 0; k < 3; k++) { // �����ÿ2���ܺϲ���ȫ�ϲ���
					for (int j = 1; j < 4; j++) {// ����16������
						str = texts[i][j].getText();// ��ȡ��ǰ�����ǩ�ı��ַ�
						str1 = texts[i][j - 1].getText();// ��ȡ��ǰ��1�����ǩ�ı��ַ�
                       
						//�󷽸��ı��ַ�Ϊ�յ����
						if (str1.compareTo("") == 0) {
							if (str.compareTo("") != 0)// �����1�����ı�Ϊ���ַ����ҵ�ǰ�����ı���Ϊ���ַ�
								biaoji = 1; // ��ǣ���������
							texts[i][j - 1].setText(str);// �ַ�����
							setColor(i, j - 1, str);//��ɫ�ı�
							texts[i][j].setText("");// ��ǰ�����ַ��ÿ�
							setColor(i, j, "");//����ɫΪ��
						} 
						
						//���ҷ����ı��ַ���ͬ�����
						else if ((str.compareTo(str1) == 0) && (j != a) && (j != a - 1)) {//��֤�Ⱥϲ�������
							num = Integer.parseInt(str);
							scores += num;//��¼�ɼ�
							times++;//��ǰʣ��ո�����1
							str = String.valueOf(2 * num); // ���� int�������ַ�����ʾ��ʽ
							texts[i][j - 1].setText(str);// ��1�����ı��ַ���Ϊ������֮��
							setColor(i, j - 1, str);//�ı���ɫ
							texts[i][j].setText("");// ��ǰ�����ַ��ÿ�
							setColor(i, j, "");//����ɫΪ��
							a = j;
							biaoji = 1;//��Ǵ�ʱ����ܺϲ�
						}
					}
				}
			}
			p1 = 1;//��ʾ���ܼ������������
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
        
		//�жϴ˾���Ϸ�����Ƿ�Ϊ��߷�
		if (maxscores <= scores) {
			maxscores = scores;
			Recorder.setMaxscores_2048(maxscores);
			Recorder.keepRecording();
		}
		currentScores.setText("<html>����<BR>" + String.valueOf(scores) + "</html>");//��ǰ�ķֱ�ǩ
		labelMaxScores.setText("<html>��߷�<br>" + String.valueOf(maxscores) + "</html>");// ��ߵ÷ֱ�ǩ
	}

	
	
	/**
	 * ���λ�ò��������2��4�����ж��Ƿ����
	 */
	public void Create() {
		int i, j;
		boolean r = true;
		String str;
		Random random = new Random();
		if (times > 0 && biaoji == 1) {//��ǰ�п�����ӣ����ƶ��������������
			while (r) {
				i = random.nextInt(4);
				j = random.nextInt(4);
				str = texts[i][j].getText();
				//����һ���жϣ������ȡ�ķ����Ƿ��Ϊ��
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
		} else if (p1 > 0 && p2 > 0 && p3 > 0 && p4 > 0) {//p����Ϊ0ʱ�����ʾ�������������������Ϸ����
			JOptionPane.showMessageDialog(null, "��Ϸ����!");
            
			Game RLJ=new Game();
			RLJ.setFocusable(true);
			RLJ.Game();
		}
	}
}

/**
 * ������������
 * @author 27402
 *
 */
class AePlayWave extends Thread {
	private static String filename;

	public AePlayWave(String wavefile) {
		filename = wavefile;
	}
	//���߳�
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
