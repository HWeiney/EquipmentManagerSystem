package pres.hw.ems.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import pres.hw.ems.dao.UserDao;

public class ConfirmUtil implements ActionListener {
	private static String title = "请确认是否执行";
	private JFrame jFrame;
	private Container container;
	private JButton yesButton, noButton;
	private JLabel jLabel1, jLabel2;
	private JPanel jpanel1, jpanel2;
	private static int id;
	private static String name;
	private static String password;
	private static String telphone;
	private static String type;
	private static int SelectFlag;
	/**
	 * 用户删除
	 * @param text
	 */
	public void show(String text) {
		jFrame = new JFrame(title);
		jFrame.setBounds(630, 400, 300, 200);
		container = new Container();
		container = jFrame.getContentPane();
		container.setBackground(new Color(209, 242, 246));
		container.setLayout(new GridLayout(2, 1));

		jLabel1 = new JLabel();
		jLabel1.setText("请确认是否删除:");
		jLabel1.setForeground(Color.red);
		jLabel1.setFont(new Font("华文中宋", Font.PLAIN, 20));
		jLabel1.setBackground(Color.pink);

		jLabel2 = new JLabel();
		jLabel2.setText(text + "用户?");
		jLabel2.setBounds(50, 10, 200, 25);
		jLabel2.setForeground(Color.red);
		jLabel2.setFont(new Font("华文中宋", Font.PLAIN, 20));
		jLabel2.setBackground(Color.pink);

		jpanel1 = new JPanel();
		jpanel1.setOpaque(false);
		jpanel1.setLayout(new BorderLayout());
		jpanel2 = new JPanel();
		jpanel2.setLayout(null);
		jpanel2.setOpaque(false);

		jpanel1.add(jLabel1, BorderLayout.SOUTH);
		jpanel2.add(jLabel2);

		yesButton = new JButton("确认");
		yesButton.setLocation(40, 45);
		yesButton.setSize(100, 30);
		yesButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		yesButton.setForeground(new Color(255, 255, 255));
		yesButton.setBackground(new Color(135, 206, 250));
		yesButton.setToolTipText("点击确认！");
		yesButton.addActionListener(this);

		noButton = new JButton("取消");
		noButton.setLocation(150, 45);
		noButton.setSize(100, 30);
		noButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		noButton.setForeground(new Color(255, 255, 255));
		noButton.setBackground(new Color(135, 206, 250));
		noButton.setToolTipText("点击取消！");
		noButton.addActionListener(this);

		jpanel2.add(yesButton);
		jpanel2.add(noButton);
		container.add(jpanel1);
		container.add(jpanel2);

		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSelectFlag(1);
	}

	/*
	 * 用户修改提示窗口
	 */
	public void altershow(String text) {
		jFrame = new JFrame(title);
		jFrame.setBounds(630, 400, 300, 200);
		container = new Container();
		container = jFrame.getContentPane();
		container.setBackground(new Color(209, 242, 246));
		container.setLayout(new GridLayout(2, 1));

		jLabel1 = new JLabel();
		jLabel1.setText("请确认是否修改:");
		jLabel1.setForeground(Color.red);
		jLabel1.setFont(new Font("华文中宋", Font.PLAIN, 20));
		jLabel1.setBackground(Color.pink);

		jLabel2 = new JLabel();
		jLabel2.setText(text + "?");
		jLabel2.setBounds(50, 10, 200, 25);
		jLabel2.setForeground(Color.red);
		jLabel2.setFont(new Font("华文中宋", Font.PLAIN, 20));
		jLabel2.setBackground(Color.pink);

		jpanel1 = new JPanel();
		jpanel1.setOpaque(false);
		jpanel1.setLayout(new BorderLayout());
		jpanel2 = new JPanel();
		jpanel2.setLayout(null);
		jpanel2.setOpaque(false);

		jpanel1.add(jLabel1, BorderLayout.SOUTH);
		jpanel2.add(jLabel2);

		yesButton = new JButton("确认");
		yesButton.setLocation(40, 45);
		yesButton.setSize(100, 30);
		yesButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		yesButton.setForeground(new Color(255, 255, 255));
		yesButton.setBackground(new Color(135, 206, 250));
		yesButton.setToolTipText("点击确认！");
		yesButton.addActionListener(this);

		noButton = new JButton("取消");
		noButton.setLocation(150, 45);
		noButton.setSize(100, 30);
		noButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		noButton.setForeground(new Color(255, 255, 255));
		noButton.setBackground(new Color(135, 206, 250));
		noButton.setToolTipText("点击取消！");
		noButton.addActionListener(this);

		jpanel2.add(yesButton);
		jpanel2.add(noButton);
		container.add(jpanel1);
		container.add(jpanel2);

		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSelectFlag(2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == yesButton) {

			if (getSelectFlag() == 1) {
				MsgUtil.updateid(UserDao.delete(getId(), getName()),1);
			} else if (getSelectFlag() == 2) {
				//MsgUtil.Msg(getId() + " " + getPassword() + " " + getTelphone() + " " + getType());
				int num;
				
				if ("无".equals(getPassword()) && "无".equals(getTelphone())) {// 只修改类型
					num = 1;
				} else if ("无".equals(getPassword())) {// 修改电话和类型
					num = 2;
				} else if ("无".equals(getTelphone())) {//修改密码和类型
					num = 3;
				} else {	//修改电话号，密码，和类型
					num = 4;
				}
				MsgUtil.updateid(UserDao.update(getId(), getPassword(), getTelphone(), getType(), num),2);
			}
			
			close();
		}
		if (e.getSource() == noButton) {

			close();
		}
	}

	public void close() {
		jFrame.dispose();
	}
	public static void main(String[] args) {
		ConfirmUtil util = new ConfirmUtil();
//	util.show("111,test");
		util.altershow("1001");
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		ConfirmUtil.id = id;
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		ConfirmUtil.name = name;
	}

	public static int getSelectFlag() {
		return SelectFlag;
	}

	public static void setSelectFlag(int selectFlag) {
		SelectFlag = selectFlag;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		ConfirmUtil.password = password;
	}

	public static String getTelphone() {
		return telphone;
	}

	public static void setTelphone(String telphone) {
		ConfirmUtil.telphone = telphone;
	}

	public static String getType() {
		return type;
	}

	public static void setType(String type) {
		ConfirmUtil.type = type;
	}

}
