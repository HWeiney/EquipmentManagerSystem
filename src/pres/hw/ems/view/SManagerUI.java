package pres.hw.ems.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import pres.hw.ems.util.ErrorUtil;

public class SManagerUI implements ActionListener {

	static JFrame jFrame;
	private static Container container;
	private static JPanel panel;
	// 功能模块按钮(用户管理，设备购买，借用设备，保修设备，设备仓库)
	private static JButton userButton, buyButton, lendButton, repairButton, storeButton, backButton;

	private static String title;
	
	private static boolean ReOpenFlag = true;
	
	public static UserUI userUI;

	public boolean isReOpenFlag() {
		return ReOpenFlag;
	}

	public void setReOpenFlag(boolean reOpenFlag) {
		ReOpenFlag = reOpenFlag;
	}
	
	public SManagerUI() {
		// TODO Auto-generated constructor stub
	}
	
	public SManagerUI(String title) {
		this.title = title;
		userUI = new UserUI();
	}
	
	// jFrame窗口
	void show() {

		jFrame = new JFrame("欢迎您！超级管理员" + title);
		jFrame.setBounds(580, 250, 400, 480);

		container = jFrame.getContentPane();
		container.setBackground(new Color(209, 242, 246));
		container.setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight());
		panel.setLayout(null);
		panel.setOpaque(false);

		userButton = new JButton("用户管理");
		userButton.setBounds(45, 45, 300, 50);
		userButton.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		userButton.setForeground(new Color(255, 255, 255));
		userButton.setBackground(new Color(135, 206, 250));
		userButton.setToolTipText("点击管理用户信息");
		userButton.addActionListener(this);

		buyButton = new JButton("设备购买");
		buyButton.setBounds(45, 105, 300, 50);
		buyButton.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		buyButton.setForeground(new Color(255, 255, 255));
		buyButton.setBackground(new Color(135, 206, 250));
		buyButton.setToolTipText("点击购买设备");
		buyButton.addActionListener(this);
		
		storeButton = new JButton("设备仓库");
		storeButton.setBounds(45, 165, 300, 50);
		storeButton.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		storeButton.setForeground(new Color(255, 255, 255));
		storeButton.setBackground(new Color(135, 206, 250));
		storeButton.setToolTipText("点击进入仓库");
		storeButton.addActionListener(this);
		
		lendButton = new JButton("借用设备");
		lendButton.setBounds(45, 225, 300, 50);
		lendButton.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		lendButton.setForeground(new Color(255, 255, 255));
		lendButton.setBackground(new Color(135, 206, 250));
		lendButton.setToolTipText("点击进入借用");
		lendButton.addActionListener(this);
		
		repairButton = new JButton("报修设备");
		repairButton.setBounds(45, 285, 300, 50);
		repairButton.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		repairButton.setForeground(new Color(255, 255, 255));
		repairButton.setBackground(new Color(135, 206, 250));
		repairButton.setToolTipText("点击查看维修");
		repairButton.addActionListener(this);
		
		backButton = new JButton("返回登录界面");
		backButton.setLocation(0, 345);
		backButton.setSize(400,50);
		backButton.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		backButton.setForeground(new Color(127, 127, 127));
		backButton.setBackground(new Color(135, 206, 250));
		backButton.setToolTipText("点击返回登录");
		backButton.addActionListener(this);

		panel.add(userButton);
		panel.add(buyButton);
		panel.add(storeButton);
		panel.add(lendButton);
		panel.add(repairButton);
		panel.add(backButton);
		
		container.add(panel);
		jFrame.setVisible(true);
		jFrame.setResizable(false);
		//jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		jFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				setReOpenFlag(true);
				new exitUI(userUI);
				close();
			}
		});
	}

	public void judgeBug() {
		if(isReOpenFlag()==false) {
			ErrorUtil.show("一个一个使用,不着急");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == userButton) {
			if(isReOpenFlag()) {
				
				setReOpenFlag(false);
				new UserUI().show();
			}else {
				judgeBug();
			}
			
		}
		
		if(e.getSource() == buyButton) {
			if(isReOpenFlag()) {
				setReOpenFlag(false);
				new BuyUI().show();
			}else {
				judgeBug();
			}
		}
		
		if(e.getSource() == storeButton) {
			if(isReOpenFlag()) {
				setReOpenFlag(false);
				new StoreUI().show();
			}else {
				judgeBug();
			}
		}
		
		if(e.getSource() == lendButton) {
			if(isReOpenFlag()) {
				setReOpenFlag(false);
				new LendUI().show();
			}else {
				judgeBug();
			}
		}
		
		if(e.getSource() == repairButton) {
			if(isReOpenFlag()) {
				setReOpenFlag(false);
				new RepairUI().show();
			}else {
				judgeBug();
			}
		}
		
		if(e.getSource() == backButton) {
			
			new exitUI(userUI);
			setReOpenFlag(true);
			close();	
		}
		
	}
	
	public void close() {
		jFrame.dispose();
		new LoginUI(1).setLoginFlag(true);
	}
	
//	public static void main(String[] args) {
//		new SManagerUI("test").show();
//	}
}
