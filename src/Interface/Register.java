package Interface;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import impl.UserDaoImpl;

public class Register {

	private JFrame frame;
	private JTextField username;
	private JTextField password;

	/**
	 * ע�����
	 */
	public void Register() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("2048\u6CE8\u518C\u754C\u9762");
		frame.setBounds(600, 300, 450, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
        //�û�����ǩ
		JLabel label = new JLabel("�û���");
		label.setBounds(81, 86, 46, 18);
		frame.getContentPane().add(label);
        //�����ǩ
		JLabel label_1 = new JLabel("��  ��");
		label_1.setBounds(81, 136, 46, 18);
		frame.getContentPane().add(label_1);
        //ȷ�ϰ�ť
		JButton button = new JButton("ȷ��");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonActionPerformed(e);
			}

			private void jButtonActionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/**
				 * 1����ȡ�û���������
				 * 2����������ʽ������У�� 
				 * 3����װ���û����� 
				 * 4�������û�ע������Ĺ��ܽ���ע�� 
				 * 5���ص���½����
				 */
				// ��ȡ�û���������
				String username_1 = username.getText().trim();
				String password_1 = password.getText().trim();

				// ��������ʽ������У��
				// �������
				// �û���������
				String usernameRegex = "^[\\u4e00-\\u9fa5]{2,6}$";
				// �������
				String passwordRegex = "^\\d{6}$";

				UserDao ud = new UserDaoImpl();
				boolean flag=ud.check(username_1);//����Ƿ��ظ�

				if(flag) {
					JOptionPane.showMessageDialog(frame,"���û����ѱ�ʹ��,������ע��");
					username.setText("");
					password.setText("");
					username.requestFocus();
				}else {
					if (!username_1.matches(usernameRegex)) {
						JOptionPane.showMessageDialog(username, "��ʽ����,�û���ֻ�����뺺��(2��6λ)");
						username.setText("");
						password.setText("");
						username.requestFocus();
						return;
					}

					if (!password_1.matches(passwordRegex)) {
						JOptionPane.showMessageDialog(password, "��ʽ����,����ֻ������6λ����");
						username.setText("");
						password.setText("");
						username.requestFocus();
						return;
					}
					
					// ��װ���û�����
					User user = new User();
					user.setUsername(username_1);
					user.setPassword(password_1);

					// �����û�ע������Ĺ��ܽ���ע��
					ud.regist(user);//���û�ע����Ϣ�ϴ����ݿ�

					JOptionPane.showMessageDialog(frame, "�û�ע��ɹ��������ص���¼����...");

					goLogin();//����������
				}
				

			}
		});

		username = new JTextField();
		username.setColumns(10);
		username.setBounds(144, 83, 152, 24);
		frame.getContentPane().add(username);

		password = new JTextField();
		password.setColumns(10);
		password.setBounds(144, 133, 152, 24);
		frame.getContentPane().add(password);
		button.setBounds(14, 213, 113, 27);
		frame.getContentPane().add(button);
        
		
		//���ذ�ť
		JButton button_1 = new JButton("����");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton_1ActionPerformed(e);
			}

			private void jButton_1ActionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				goLogin();
			}

		});
		button_1.setBounds(305, 213, 113, 27);
		frame.getContentPane().add(button_1);

		frame.setVisible(true);
	}
    
	
	//�����ء���¼������
	public void goLogin() {
		frame.dispose();//�ͷŵ�ǰ����
		Log RLJ = new Log();
		RLJ.Log();
	}
}
