package pres.hw.ems.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import pres.hw.ems.util.ErrorUtil;

public class GManagerUI implements ActionListener{
	static JFrame jFrame;
	private static Container container;
	private static JPanel panel;
	// ����ģ�鰴ť(�û������豸���򣬽����豸�������豸���豸�ֿ�)
	private static JButton buyButton, lendButton, repairButton, storeButton, backButton;

	private static String title;
	private static JLabel clockLabel;
	
	private static boolean ReOpenFlag = true;
	
	public static UserUI userUI;

	public boolean isReOpenFlag() {
		return ReOpenFlag;
	}

	public void setReOpenFlag(boolean reOpenFlag) {
		ReOpenFlag = reOpenFlag;
	}
	
	public GManagerUI() {
		// TODO Auto-generated constructor stub
	}
	
	public GManagerUI(String title) {
		this.title = title;
		userUI = new UserUI();
	}
	
	// jFrame����
	void show() {

		jFrame = new JFrame("��ӭ�����豸����Ա" + title);
		jFrame.setBounds(580, 250, 400, 480);

		container = jFrame.getContentPane();
		container.setBackground(new Color(209, 242, 246));
		container.setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight());
		panel.setLayout(null);
		panel.setOpaque(false);

		ClockPane();
		
		buyButton = new JButton("�豸����");
		buyButton.setBounds(45, 105, 300, 50);
		buyButton.setFont(new Font("΢���ź�", Font.PLAIN, 30));
		buyButton.setForeground(new Color(255, 255, 255));
		buyButton.setBackground(new Color(135, 206, 250));
		buyButton.setToolTipText("��������豸");
		buyButton.addActionListener(this);
		
		storeButton = new JButton("�豸�ֿ�");
		storeButton.setBounds(45, 165, 300, 50);
		storeButton.setFont(new Font("΢���ź�", Font.PLAIN, 30));
		storeButton.setForeground(new Color(255, 255, 255));
		storeButton.setBackground(new Color(135, 206, 250));
		storeButton.setToolTipText("�������ֿ�");
		storeButton.addActionListener(this);
		
		lendButton = new JButton("�����豸");
		lendButton.setBounds(45, 225, 300, 50);
		lendButton.setFont(new Font("΢���ź�", Font.PLAIN, 30));
		lendButton.setForeground(new Color(255, 255, 255));
		lendButton.setBackground(new Color(135, 206, 250));
		lendButton.setToolTipText("����������");
		lendButton.addActionListener(this);
		
		repairButton = new JButton("�����豸");
		repairButton.setBounds(45, 285, 300, 50);
		repairButton.setFont(new Font("΢���ź�", Font.PLAIN, 30));
		repairButton.setForeground(new Color(255, 255, 255));
		repairButton.setBackground(new Color(135, 206, 250));
		repairButton.setToolTipText("����鿴ά��");
		repairButton.addActionListener(this);
		
		backButton = new JButton("���ص�¼����");
		backButton.setLocation(0, 345);
		backButton.setSize(400,50);
		backButton.setFont(new Font("΢���ź�", Font.PLAIN, 30));
		backButton.setForeground(new Color(127, 127, 127));
		backButton.setBackground(new Color(135, 206, 250));
		backButton.setToolTipText("������ص�¼");
		backButton.addActionListener(this);

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

	public void ClockPane() {
		clockLabel = new JLabel();
		clockLabel.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		clockLabel.setForeground(new Color(127, 127, 127));
		clockLabel.setBackground(new Color(135, 206, 250));
		clockLabel.setBounds(0, 45, 400, 30);
		clockLabel.setOpaque(true);
		clockLabel.setHorizontalAlignment(SwingConstants.CENTER);

		clockLabel.setText("ϵͳʱ��:" + DateFormat.getDateTimeInstance().format(new Date()));
		panel.add(clockLabel);
		Timer timer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clockLabel.setText("ϵͳʱ��:" + DateFormat.getDateTimeInstance().format(new Date()));
			}
		});
		timer.setRepeats(true);
		timer.setCoalesce(true);
		timer.setInitialDelay(0);
		timer.start();
	}
	
	public void judgeBug() {
		if(isReOpenFlag()==false) {
			ErrorUtil.show("һ��һ��ʹ��,���ż�");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
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
//		new GManagerUI("test").show();
//	}

}
