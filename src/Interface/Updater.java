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

public class Updater {

	private JFrame frame;
	private JTextField username;
	private JTextField password;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void Updater() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("2048修改密码界面");
		frame.setBounds(600, 300, 450, 300);
		//frame.setBackground(Color.BLUE);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
        //用户名标签
		JLabel label = new JLabel("用户名");
		label.setBounds(81, 86, 46, 18);
		frame.getContentPane().add(label);
        //新密码标签
		JLabel label_1 = new JLabel("新密码");
		label_1.setBounds(81, 136, 46, 18);
		frame.getContentPane().add(label_1);
        //确定按钮
		JButton button = new JButton("确定");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonActionPerformed(e);
			}

			private void jButtonActionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/**
				 * 1、获取用户名和密码
				 * 2、用正则表达式做数据校验 
				 * 3、封装成用户对象
				 * 4、调用用户操作的功能进行修改
				 * 5、回到登陆界面
				 */
				// 获取用户名和密码
				String username_1 = username.getText().trim();
				String password_1 = password.getText().trim();

				// 用正则表达式做数据校验
				// 定义规则
				// 用户命名规则
				String usernameRegex = "^[\\u4e00-\\u9fa5]{2,6}$";
				// 密码规则
				String passwordRegex = "^\\d{6}$";

				UserDao ud = new UserDaoImpl();
			    

				
				if (!username_1.matches(usernameRegex)) {
					JOptionPane.showMessageDialog(username, "用户名只能输入汉字(2至6位)");
					username.setText("");
					password.setText("");
					username.requestFocus();
					return;
				}

				if (!password_1.matches(passwordRegex)) {
					JOptionPane.showMessageDialog(password, "密码只能输入6位的数");
					username.setText("");
					password.setText("");
					username.requestFocus();
					return;
				}

				// 封装成用户对象
				User user = new User();
				user.setUsername(username_1);
				user.setPassword(password_1);

				// 调用用户操作的功能进行修改
				try {
					System.out.println(ud.update(user));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(frame, "用户修改密码成功，即将回到登录界面...");
				goLogin();
				
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

		
		//返回按钮
		JButton button_1 = new JButton("返回");
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

	
	public void goLogin() {
		frame.dispose();
		Log RLJ = new Log();
		RLJ.Log();
	}
}
