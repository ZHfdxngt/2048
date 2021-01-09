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
	 * 注册界面
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
        //用户名标签
		JLabel label = new JLabel("用户名");
		label.setBounds(81, 86, 46, 18);
		frame.getContentPane().add(label);
        //密码标签
		JLabel label_1 = new JLabel("密  码");
		label_1.setBounds(81, 136, 46, 18);
		frame.getContentPane().add(label_1);
        //确认按钮
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
				 * 4、调用用户注册操作的功能进行注册 
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
				boolean flag=ud.check(username_1);//检查是否重复

				if(flag) {
					JOptionPane.showMessageDialog(frame,"该用户名已被使用,请重新注册");
					username.setText("");
					password.setText("");
					username.requestFocus();
				}else {
					if (!username_1.matches(usernameRegex)) {
						JOptionPane.showMessageDialog(username, "格式错误,用户名只能输入汉字(2至6位)");
						username.setText("");
						password.setText("");
						username.requestFocus();
						return;
					}

					if (!password_1.matches(passwordRegex)) {
						JOptionPane.showMessageDialog(password, "格式错误,密码只能输入6位的数");
						username.setText("");
						password.setText("");
						username.requestFocus();
						return;
					}
					
					// 封装成用户对象
					User user = new User();
					user.setUsername(username_1);
					user.setPassword(password_1);

					// 调用用户注册操作的功能进行注册
					ud.regist(user);//将用户注册信息上传数据库

					JOptionPane.showMessageDialog(frame, "用户注册成功，即将回到登录界面...");

					goLogin();//返回主界面
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
    
	
	//“返回”登录主界面
	public void goLogin() {
		frame.dispose();//释放当前窗体
		Log RLJ = new Log();
		RLJ.Log();
	}
}
