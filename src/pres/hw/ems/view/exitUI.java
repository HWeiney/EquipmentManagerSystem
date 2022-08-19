package pres.hw.ems.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

//退出本系统，恢复系统的实始状态
public class exitUI {
	
	public exitUI() {
		// TODO Auto-generated constructor stub
	}
	//登录界面退出
	public exitUI(JTextField nameField, JPasswordField passwordField, JLabel errorLabel, SManagerUI sManagerUI, UserUI userUI) {
		nameField.setText("");
		passwordField.setText("");
		errorLabel.setText("请先登录!");
		errorLabel.setLocation(130, 0);
		//先判断是否点开了,再关闭
		if (SManagerUI.jFrame != null) {
			sManagerUI.close();
			sManagerUI.setReOpenFlag(true);
		}
		
		if (UserUI.jFrame != null) {
			userUI.close();
		}
		
	}
	//超管界面退出
	public exitUI(UserUI userUI) {
		if (UserUI.jFrame != null) {
			userUI.close();
		}
	}
	//登录界面刷新
	public exitUI(JTextField nameField, JPasswordField passwordField, JLabel errorLabel) {
		nameField.setText("");
		passwordField.setText("");
		errorLabel.setText("请先登录!");
		errorLabel.setLocation(130, 0);
	}
}
