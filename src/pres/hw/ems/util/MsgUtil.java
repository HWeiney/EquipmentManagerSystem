package pres.hw.ems.util;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import pres.hw.ems.domain.User;
import pres.hw.ems.view.UserUI;

public class MsgUtil {
	private static JFrame jFrame = new JFrame("操作提示框");
	
	private static JTextArea jText;
	
	private static String errMsg = "请核对用户ID、NAME是否存在!"
			+ "\n要求:"
			+ "\nuserid(用户编号)----不为空"
			+ "\nusername(用户名称)----不为空"
			+ "\npassword(用户输入密码)----不为空"
			+ "\nrepassword(用户确认密码)----不为空";
	
	private static String sucMsg = "操作成功,请继续...";
	
	public static void showdb(boolean flag) {
		JFrame jFrame = new JFrame("操作提示框");
		jFrame.setSize(300, 200);
		
		if (flag) {
			jText = new JTextArea(sucMsg);
			UserUI.setLastSize(1);
		}else {
			jText = new JTextArea("数据库插入操作失败");
		}
		
		jText.setOpaque(false);
		jText.setForeground(Color.red);
		jText.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		jFrame.setLocation(630, 400);
		JScrollPane jScrollPane = new JScrollPane(jText);
		
		Container container = new Container();
		container = jFrame.getContentPane();
		container.setBackground(new Color(209, 242, 246));
		container.add(jScrollPane);

		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(jFrame.HIDE_ON_CLOSE);
	}

	/**
	 * 设备购买
	 * @param flag
	 */
	public static void buyshowdb(boolean flag) {
		JFrame jFrame = new JFrame("操作提示框");
		jFrame.setSize(300, 200);
		
		if (flag) {
			jText = new JTextArea(sucMsg);
		}else {
			jText = new JTextArea("数据库插入操作失败");
		}
		
		jText.setOpaque(false);
		jText.setForeground(Color.red);
		jText.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		jFrame.setLocation(630, 400);
		JScrollPane jScrollPane = new JScrollPane(jText);
		
		Container container = new Container();
		container = jFrame.getContentPane();
		container.setBackground(new Color(209, 242, 246));
		container.add(jScrollPane);

		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(jFrame.HIDE_ON_CLOSE);
	}
	
	public static void show(boolean flag) {
		
		jFrame.setSize(300, 200);
		
		if (flag) {
			jText = new JTextArea(sucMsg);
		}else {
			jText = new JTextArea(errMsg);
		}
		
		jText.setOpaque(false);
		jText.setForeground(Color.red);
		jText.setFont(new Font("微软雅黑", Font.BOLD, 15));
		jFrame.setLocation(630, 400);
		JScrollPane jScrollPane = new JScrollPane(jText);
		Container container = new Container();
		container = jFrame.getContentPane();
		container.setBackground(new Color(209, 242, 246));
		container.add(jScrollPane);

		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(jFrame.HIDE_ON_CLOSE);
	}
	
	public static void updateid(boolean flag,int num) {
		JFrame jFrame = new JFrame("操作提示框");
		jFrame.setSize(300, 200);
		
		if (flag) {
			jText = new JTextArea(sucMsg);
		}else {
			if(num == 1) {
				jText = new JTextArea("数据库删除操作失败");
			}else if(num == 2) {
				jText = new JTextArea("数据库修改操作失败");
			}
		}
		
		jText.setOpaque(false);
		jText.setForeground(Color.red);
		jText.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		jFrame.setLocation(630, 400);
		JScrollPane jScrollPane = new JScrollPane(jText);
		
		Container container = new Container();
		container = jFrame.getContentPane();
		container.setBackground(new Color(209, 242, 246));
		container.add(jScrollPane);

		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(jFrame.HIDE_ON_CLOSE);
	}
	
	public static void Msg(String str) {
		
		JFrame jFrame = new JFrame("操作提示框");
		jFrame.setSize(300, 200);
		jText = new JTextArea(str+"不存在!");
		jText.setOpaque(false);
		jText.setForeground(Color.red);
		jText.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		jFrame.setLocation(630, 400);
		JScrollPane jScrollPane = new JScrollPane(jText);
		
		Container container = new Container();
		container = jFrame.getContentPane();
		container.setBackground(new Color(209, 242, 246));
		container.add(jScrollPane);

		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(jFrame.HIDE_ON_CLOSE);
	}
	/**
	 * buyUI错误弹窗
	 * @param str
	 */
	public static void Errmsg(String str) {
		JFrame jFrame = new JFrame("操作提示框");
		jFrame.setBounds(630,400,300, 200);
		Container container = new Container();
		container = jFrame.getContentPane();
		container.setBackground(new Color(255, 213, 130));
		container.setLayout(null);
		
		JLabel jLabel = new JLabel(str);
		jLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		jLabel.setForeground(new Color(255, 0, 0));
		jLabel.setBounds(0, 60, 285, 30);
		jLabel.setHorizontalAlignment(SwingConstants.CENTER);
		//jLabel.setOpaque(true);
		container.add(jLabel);

		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(jFrame.HIDE_ON_CLOSE);
	}
	
//	public static void main(String[] args) {
//		new MsgUtil();
//		MsgUtil.Errmsg("请选择购买设备名称");
//	}
}