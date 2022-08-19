package pres.hw.ems.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

//�˳���ϵͳ���ָ�ϵͳ��ʵʼ״̬
public class exitUI {
	
	public exitUI() {
		// TODO Auto-generated constructor stub
	}
	//��¼�����˳�
	public exitUI(JTextField nameField, JPasswordField passwordField, JLabel errorLabel, SManagerUI sManagerUI, UserUI userUI) {
		nameField.setText("");
		passwordField.setText("");
		errorLabel.setText("���ȵ�¼!");
		errorLabel.setLocation(130, 0);
		//���ж��Ƿ�㿪��,�ٹر�
		if (SManagerUI.jFrame != null) {
			sManagerUI.close();
			sManagerUI.setReOpenFlag(true);
		}
		
		if (UserUI.jFrame != null) {
			userUI.close();
		}
		
	}
	//���ܽ����˳�
	public exitUI(UserUI userUI) {
		if (UserUI.jFrame != null) {
			userUI.close();
		}
	}
	//��¼����ˢ��
	public exitUI(JTextField nameField, JPasswordField passwordField, JLabel errorLabel) {
		nameField.setText("");
		passwordField.setText("");
		errorLabel.setText("���ȵ�¼!");
		errorLabel.setLocation(130, 0);
	}
}
