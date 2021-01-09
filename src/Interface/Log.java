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
	 * 创建应用程序 
	 * @wbp.parser.entryPoint
	 */
	public void Log() {
		initialize();
	}

	/**
	 * 初始化框架的内容
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("2048登陆界面");
		frame.setBounds(600, 300, 450, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton button = new JButton("登陆");
		button.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				jButtonActionListener(e);
				
			} 

			private void jButtonActionListener(ActionEvent e) {
				// TODO Auto-generated method stub
				
				/**
				 * 1、获取用户名和密码
				 * 2、正则表达式校验用户名和密码
				 * 3、创建对象调用功能，返回一个Boolean值
				 * 4、根据Boolean给出提示
				 */
				
				//获取用户名和密码
				String username_1=username.getText().trim();
				
				String password_1=password.getText().trim();
				
				//用正则表达式做数据校验
				//定义规则
				//用户命名规则
				String usernameRegex = "^[\\u4e00-\\u9fa5]{2,6}$";
				//密码规则
				String passwordRegex = "^\\d{6}$";
				
				//校验
				if(!username_1.matches(usernameRegex)) {
					JOptionPane.showMessageDialog(username, "用户名只能输入汉字(2至6位)");
					username.setText("");
					username.requestFocus();
					return;
				}
				if(!password_1.matches(passwordRegex)) {
					JOptionPane.showMessageDialog(password, "密码只能输入6位的数");
					password.setText("");
					password.requestFocus();
					return;
				}
				
				//创建对象调用功能，返回一个Boolean值
				UserDao ud = new UserDaoImpl();
				boolean flag=ud.login(username_1, password_1);//把用户信息传至数据库
				
				if(flag) {
					JOptionPane.showMessageDialog(frame,"恭喜你登录成功！");
					frame.dispose();
					//进入游戏界面
					Game RLJ=new Game();
					RLJ.setFocusable(true);
					RLJ.Game();
				}else {
					JOptionPane.showMessageDialog(frame, "用户名或者密码有误，请重新输入");
					username.setText("");
					password.setText("");
					username.requestFocus();
				}
			}
		});
		button.setBounds(14, 213, 113, 27);
		frame.getContentPane().add(button);

		
		
		//注册功能
		JButton button_1 = new JButton("注册");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonActionPerformed(e);
			}
			
			private void jButtonActionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				Register RLJ = new Register();//注册界面
				RLJ.Register();
			}
		});
		button_1.setBounds(305, 213, 113, 27);
		frame.getContentPane().add(button_1);

		
		
		//修改密码功能
		JButton button_2 = new JButton("忘记密码");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonActionPerformed(e);
			}
			
			private void jButtonActionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				Updater RLJ = new Updater();//修改密码界面
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

		JLabel label = new JLabel("用户名");
		label.setBounds(81, 86, 46, 18);
		frame.getContentPane().add(label);

		JLabel label_1 = new JLabel("密  码");
		label_1.setBounds(81, 136, 46, 18);
		frame.getContentPane().add(label_1);

		frame.setVisible(true);
	}
}
