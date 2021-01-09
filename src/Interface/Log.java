package Interface;

import Interface.Register;

import impl.UserDaoImpl;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Log {

	private JFrame frame;
	private JPasswordField password;
	private JTextField username;

	/**
	 * ����Ӧ�ó��� 
	 * @wbp.parser.entryPoint
	 */
	public void Log() {
		initialize();
	}

	/**
	 * ��ʼ����ܵ�����
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("2048��½����");
		frame.setBounds(600, 300, 450, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton button = new JButton("��½");
		button.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				jButtonActionListener(e);
				
			} 

			private void jButtonActionListener(ActionEvent e) {
				// TODO Auto-generated method stub
				
				/**
				 * 1����ȡ�û���������
				 * 2��������ʽУ���û���������
				 * 3������������ù��ܣ�����һ��Booleanֵ
				 * 4������Boolean������ʾ
				 */
				
				//��ȡ�û���������
				String username_1=username.getText().trim();
				
				String password_1=password.getText().trim();
				
				//��������ʽ������У��
				//�������
				//�û���������
				String usernameRegex = "^[\\u4e00-\\u9fa5]{2,6}$";
				//�������
				String passwordRegex = "^\\d{6}$";
				
				//У��
				if(!username_1.matches(usernameRegex)) {
					JOptionPane.showMessageDialog(username, "�û���ֻ�����뺺��(2��6λ)");
					username.setText("");
					username.requestFocus();
					return;
				}
				if(!password_1.matches(passwordRegex)) {
					JOptionPane.showMessageDialog(password, "����ֻ������6λ����");
					password.setText("");
					password.requestFocus();
					return;
				}
				
				//����������ù��ܣ�����һ��Booleanֵ
				UserDao ud = new UserDaoImpl();
				boolean flag=ud.login(username_1, password_1);//���û���Ϣ�������ݿ�
				
				if(flag) {
					JOptionPane.showMessageDialog(frame,"��ϲ���¼�ɹ���");
					frame.dispose();
					//������Ϸ����
					Game RLJ=new Game();
					RLJ.setFocusable(true);
					RLJ.Game();
				}else {
					JOptionPane.showMessageDialog(frame, "�û�������������������������");
					username.setText("");
					password.setText("");
					username.requestFocus();
				}
			}
		});
		button.setBounds(14, 213, 113, 27);
		frame.getContentPane().add(button);

		
		
		//ע�Ṧ��
		JButton button_1 = new JButton("ע��");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonActionPerformed(e);
			}
			
			private void jButtonActionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				Register RLJ = new Register();//ע�����
				RLJ.Register();
			}
		});
		button_1.setBounds(305, 213, 113, 27);
		frame.getContentPane().add(button_1);

		
		
		//�޸����빦��
		JButton button_2 = new JButton("��������");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonActionPerformed(e);
			}
			
			private void jButtonActionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				Updater RLJ = new Updater();//�޸��������
				RLJ.Updater();
			}
		});
		button_2.setBounds(305, 132, 113, 27);
		frame.getContentPane().add(button_2);
		
		
		
		password = new JPasswordField();
		password.setBounds(144, 133, 152, 24);
        
		frame.getContentPane().add(password);
		password.setColumns(10);

		username = new JTextField();
		username.setBounds(144, 83, 152, 24);
		username.setColumns(10);
		frame.getContentPane().add(username);

		JLabel label = new JLabel("�û���");
		label.setBounds(81, 86, 46, 18);
		frame.getContentPane().add(label);

		JLabel label_1 = new JLabel("��  ��");
		label_1.setBounds(81, 136, 46, 18);
		frame.getContentPane().add(label_1);

		frame.setVisible(true);
	}
}
