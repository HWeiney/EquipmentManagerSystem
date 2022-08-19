package pres.hw.ems.util;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class ErrorUtil {
	
	private static JFrame jFrame1,jFrame2,jFrame3;

	private static JLabel jLabel;
	//一个一个使用
	public static void show(String msg) {
		jFrame1 = new JFrame("信息提示");
		jFrame1.setSize(400, 300);
		jFrame1.getContentPane().setBackground(new Color(135, 206, 250));
		jFrame1.setLocation(580, 300);
		jFrame1.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		jFrame1.setResizable(false);
		jFrame1.setVisible(true);
		jLabel = new JLabel(msg,jLabel.CENTER);
		jLabel.setFont(new Font("楷体",Font.PLAIN,32));
		jLabel.setForeground(Color.red);
		jFrame1.add(jLabel);
	}
	//登录信息错误
	public static void showLogin(String msg) {
		jFrame2 = new JFrame("信息提示");
		jFrame2.setSize(400, 300);
		jFrame2.getContentPane().setBackground(new Color(135, 206, 250));
		jFrame2.setLocation(580, 300);
		jFrame2.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		jFrame2.setResizable(false);
		jFrame2.setVisible(true);
		jLabel = new JLabel(msg,jLabel.CENTER);
		jLabel.setFont(new Font("楷体",Font.PLAIN,32));
		jLabel.setForeground(Color.red);
		jFrame2.add(jLabel);
	}
	//不可重复登录系统
	public static void LoginError(String msg) {
		jFrame3 = new JFrame("信息提示");
		jFrame3.setSize(400, 300);
		jFrame3.getContentPane().setBackground(new Color(135, 206, 250));
		jFrame3.setLocation(580, 300);
		jFrame3.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		jFrame3.setResizable(false);
		jFrame3.setVisible(true);
		jLabel = new JLabel(msg,jLabel.CENTER);
		jLabel.setFont(new Font("楷体",Font.PLAIN,32));
		jLabel.setForeground(Color.red);
		jFrame3.add(jLabel);
	}
//	public static void main(String[] args) {
//		new ErrorUtil();
//		ErrorUtil.show("请选择购买设备名称");
//	}
}
