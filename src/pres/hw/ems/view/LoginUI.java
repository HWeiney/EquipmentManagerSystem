package pres.hw.ems.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;


import pres.hw.ems.util.ErrorUtil;

import pres.hw.ems.dao.UserDao;
import pres.hw.ems.domain.User;
import pres.hw.ems.util.QueryUtil;


public class LoginUI implements ActionListener {
	static String title = "欢迎您使用《设备管理系统》";
	private static JFrame jFrame = new JFrame(title);

	// 各功能窗口
	public static JFrame bossFrame, managerFrame, buyFrame, lendFrame, repairFrame, storeFrame;
	
	// 其他功能模块
	public static SManagerUI sManagerUI;
	public static GManagerUI gManagerUI;
	public static UserUI userUI;

	// 用户登录组件
	private static JLabel errorLabel,l1;
	private static JPanel loginPanel, userPanel, rightPanel;

	// 用户名，密码，设置为静态，退出系统时传递值
	public static JTextField nameField;
	public static JPasswordField passwordField;
	public static JComboBox<String> cmb;

	// 登录按钮
	private static JButton loginButton, exitButton;

	// 容器
	private static Container container;

	// 登录标志
	private static boolean login_success = false;
	private static boolean NoExitFlag = true;
	private static boolean isLogin = false;

	public LoginUI(int i) {
		
	}
	
	public LoginUI() {
		// JFrame设置
		jFrame.setSize(400, 400);
		jFrame.setLocation(580, 250);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 对jFrame容器设置
		container = jFrame.getContentPane();
		container.setBackground(new Color(209, 242, 246));
		container.setLayout(null);
		
		//对面板设置
		rightPanel = new JPanel();
		rightPanel.setLayout(null);
		rightPanel.setLocation(0, 0);
		rightPanel.setSize(400, 100);
		rightPanel.setOpaque(false);
		
		userPanel = new JPanel();
		userPanel.setLayout(null);
		userPanel.setLocation(0, 100);
		userPanel.setSize(400, 150);
		userPanel.setOpaque(false);
		
		loginPanel = new JPanel();
		loginPanel.setLayout(null);
		loginPanel.setLocation(0, 250);
		loginPanel.setSize(400, 150);
		loginPanel.setOpaque(false);
		
		//身份选择(超级管理员和设备管理员),定义下拉框组件
		cmb = new JComboBox<String>();

		//添加内容
		cmb.addItem("超级管理员");
		cmb.addItem("设备管理员");
		cmb.setFont(new Font("楷体",Font.PLAIN,32));
		cmb.setBounds(170, 60, 190, 40);
		cmb.setBackground(new Color(209, 242, 246));
		cmb.setOpaque(false);
		cmb.setToolTipText("选择登录时的身份");
		//获取内容
//		String city = cmb.getSelectedItem().toString();
//		System.out.println(city);
		
		//登录错误
		errorLabel = new JLabel("登录后可进入系统!");
		errorLabel.setFont(new Font("楷体",Font.PLAIN,32));
		errorLabel.setForeground(Color.red);
		errorLabel.setBounds(60,0,300,80);
		rightPanel.add(errorLabel);
		
		l1 = new JLabel("用户身份:");
		l1.setFont(new Font("楷体",Font.PLAIN,32));
		l1.setBounds(20, 40, 200, 80);
		//l1.setBounds(null);
		rightPanel.add(l1);
		rightPanel.add(cmb);
		
		//输入框设置
		ImageIcon userIcon=new ImageIcon("image/user.png");
		nameField = new JTextField() {//重绘图标到输入框中
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				userIcon.paintIcon(nameField, g, 3,7);
			}
		};
		nameField.setBorder(new CompoundBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(135, 206, 250)), new EmptyBorder(0, 50, 0, 0)));
		nameField.setBounds(50, 15,290,60);
		nameField.setFont(new Font("Consolas", Font.PLAIN, 32));
		nameField.setToolTipText("填写用户名");
		
		ImageIcon passwordIcon=new ImageIcon("image/password.png");
		passwordField = new JPasswordField(){//重绘图标到输入框中
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				passwordIcon.paintIcon(passwordField, g, 3,7);
			}
		};
		passwordField.setBorder(new CompoundBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(135, 206, 250)), new EmptyBorder(0, 50, 0, 0)));
		passwordField.setBounds(50,80,290, 60);
		passwordField.setFont(new Font("Consolas", Font.PLAIN, 32));
		passwordField.setToolTipText("填写密码");
		
		//进行放置
		container.add(rightPanel);
		userPanel.add(nameField);
		userPanel.add(passwordField);
		container.add(userPanel);
		
		//登录
		loginButton = new JButton("登录系统");
		loginButton.setLocation(50, 10);
		loginButton.setSize(140, 50);
		loginButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBackground(new Color(135, 206, 250));
		loginButton.setToolTipText("点击可登录系统！");
		loginButton.addActionListener(this);
		
		//退出
		exitButton = new JButton("退出系统");
		exitButton.setLocation(200, 10);
		exitButton.setSize(140, 50);
		exitButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		exitButton.setForeground(new Color(255, 255, 255));
		exitButton.setBackground(new Color(135, 206, 250));
		exitButton.setToolTipText("点击退出当前账号！");
		exitButton.addActionListener(this);
		
		loginPanel.add(loginButton);
		loginPanel.add(exitButton);
		container.add(loginPanel);
		
		jFrame.setResizable(false);
		jFrame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String name = nameField.getText();
		String password = new String(passwordField.getPassword());
		String type = cmb.getSelectedItem().toString();
		List<User> users = UserDao.query();
		
		//登录系统
		if (e.getSource() == loginButton) {
			if ((QueryUtil.searchAll(name, password, users, type) && SManagerUI.jFrame == null)
					|| (QueryUtil.searchAll(name, password, users, type) && getLoginFlag())) {
				errorLabel.setText("登录成功!");
				errorLabel.setLocation(130, 0);
				login_success = true;
				NoExitFlag = true;
				
				if(type.equals("超级管理员")) {
					//System.out.println("type is "+type);
					//进入超管页面
					sManagerUI = new SManagerUI(name);
					sManagerUI.show();
					//登录后刷新
					new exitUI(nameField, passwordField, errorLabel);
				}else {
					//进入设备管理页面
					gManagerUI = new GManagerUI(name);
					gManagerUI.show();
					new exitUI(nameField, passwordField, errorLabel);
				}
				
			}else if(SManagerUI.jFrame != null && getLoginFlag()==false) {
				ErrorUtil.LoginError("不可重复登录系统...");
			}else if(!QueryUtil.searchAll(name, password, users, type)){
				login_success = false;
				NoExitFlag = false;
				ErrorUtil.showLogin("请核实您的信息...");
			}
		}
		//退出系统
		if(e.getSource() == exitButton) {
			login_success = false;
			NoExitFlag = false;
			userUI = new UserUI();
			new exitUI(nameField, passwordField, errorLabel,sManagerUI,userUI);
		}
	}

	public static void main(String[] args) {
		new LoginUI();
	}

	public boolean getLoginFlag() {
		return isLogin;
	}

	public void setLoginFlag(boolean isLogin) {
		this.isLogin = isLogin;
	}

}
