package pres.hw.ems.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import pres.hw.ems.dao.BuyDao;
import pres.hw.ems.domain.BuyEm;
import pres.hw.ems.util.ErrorUtil;

public class StoreUI implements ActionListener{

	private JFrame jFrame;
	private Container container;
	private JPanel listPanel, funPanel;
	private JLabel titleLabel;
	private JTable table;
	private JButton buyListButton,lendListButton,repairListButton,backButton;
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	
	private List<BuyEm> storeList;
	private static BuyDao buyDao;
	private TableUI tableUI;
	private String[] columns = { "�豸��", "�豸����", "�ڿ���","������","������" };
	private String[][] data = new String[30][5];
	
	private static boolean TabelReOpenFlag = true;
	
	public boolean isTabelReOpenFlag() {
		return TabelReOpenFlag;
	}

	public void setTabelReOpenFlag(boolean tabelReOpenFlag) {
		TabelReOpenFlag = tabelReOpenFlag;
	}

	public StoreUI() {
		// TODO Auto-generated constructor stub
	}
	
	public void show() {
		jFrame = new JFrame("�豸�ֿ�");
		jFrame.setBounds(580, 250, 400, 500);
		container = jFrame.getContentPane();
		container.setBackground(new Color(209, 242, 246));
		container.setLayout(null);
		
		// 2��JPanel
		listPanel = new JPanel();
		listPanel.setBackground(new Color(237, 125, 49));
		listPanel.setBounds(0, 0, 400, 310);
		listPanel.setLayout(null);
		
		funPanel = new JPanel();
		funPanel.setBackground(new Color(64, 200, 164));
		funPanel.setBounds(0,310,400,155);
		funPanel.setLayout(null);
		
		titleLabel = new JLabel("������豸��Ϣ��");
		titleLabel.setBackground(new Color(112, 173, 71));
		titleLabel.setFont(new Font("΢���ź�", Font.BOLD, 20));
		titleLabel.setForeground(new Color(255, 255, 255));
		titleLabel.setBounds(0, 0, 400, 30);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setOpaque(true);
		listPanel.add(titleLabel);
		
		//���
		tableModel = new DefaultTableModel(setData(), columns);
		table = new JTable(tableModel);
		table.setFont(new Font("΢���ź�", Font.BOLD, 13));
		table.setForeground(new Color(255, 83, 139));
		// ���ݾ���
		DefaultTableCellRenderer dc = new DefaultTableCellRenderer();
		dc.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, dc);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 30, 390, 280);
		listPanel.add(scrollPane);
		
		//4��JButton
		buyListButton = new JButton("��ѯ�����豸-����");
		buyListButton.setBounds(7, 10, 373, 30);
		buyListButton.setFont(new Font("΢���ź�", Font.BOLD, 20));
		buyListButton.setForeground(new Color(0, 0, 0));
		buyListButton.setBackground(new Color(255, 80, 80));
		buyListButton.setToolTipText("��ѯ�豸������Ϣ");
		buyListButton.addActionListener(this);
		funPanel.add(buyListButton);
		
		lendListButton = new JButton("��ѯ�����豸-����");
		lendListButton.setBounds(7, 45, 373, 30);
		lendListButton.setFont(new Font("΢���ź�", Font.BOLD, 20));
		lendListButton.setForeground(new Color(0, 0, 0));
		lendListButton.setBackground(new Color(255, 80, 80));
		lendListButton.setToolTipText("��ѯ�豸������Ϣ");
		lendListButton.addActionListener(this);
		funPanel.add(lendListButton);
		
		repairListButton = new JButton("��ѯ�����豸-����");
		repairListButton.setBounds(7, 80, 373, 30);
		repairListButton.setFont(new Font("΢���ź�", Font.BOLD, 20));
		repairListButton.setForeground(new Color(0, 0, 0));
		repairListButton.setBackground(new Color(255, 80, 80));
		repairListButton.setToolTipText("��ѯ�豸������Ϣ");
		repairListButton.addActionListener(this);
		funPanel.add(repairListButton);
		
		backButton = new JButton("�˳�ϵͳ");
		backButton.setBounds(7, 115, 373, 30);
		backButton.setFont(new Font("΢���ź�", Font.BOLD, 20));
		backButton.setForeground(new Color(0, 0, 0));
		backButton.setBackground(new Color(255, 80, 80));
		backButton.setToolTipText("�˳���ǰϵͳ");
		backButton.addActionListener(this);
		funPanel.add(backButton);
		
		container.add(listPanel);
		container.add(funPanel);

		jFrame.setResizable(false);
		jFrame.setVisible(true);
		jFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				new SManagerUI().setReOpenFlag(true);
				new GManagerUI().setReOpenFlag(true);
				close();
				//System.exit(0);
			}
		});
	}
	
	public String[][] setData() {

		storeList = new ArrayList<BuyEm>();

		buyDao = new BuyDao();
		storeList = BuyDao.queryStoreList();

		int size = storeList.size();

		for (int i = 0; i < size; i++) {
			BuyEm buyEm = storeList.get(i);
			data[i][0] = buyEm.getEno() + "";
			data[i][1] = buyEm.getEname();
			data[i][2] = buyEm.getEcount() + "";
			data[i][3] = buyEm.getLcount() + "";
			data[i][4] = buyEm.getRcount() + "";
		}

		return data;
	}
	
	public void judgeBug() {
		if(isTabelReOpenFlag()==false) {
			ErrorUtil.show("һ��һ��ʹ��,���ż�");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == buyListButton) {
			if(isTabelReOpenFlag()) {
				setTabelReOpenFlag(false);
				tableUI = new TableUI("���豸���򶩵���Ϣ��");
				tableUI.show();
			}else {
				judgeBug();
			}
		}
		if(e.getSource() == lendListButton) {
			if(isTabelReOpenFlag()) {
				setTabelReOpenFlag(false);
				tableUI = new TableUI("���豸������Ϣ��",1);
				tableUI.show();
			}else {
				judgeBug();
			}
		}
		if(e.getSource() == repairListButton) {
			if(isTabelReOpenFlag()) {
				setTabelReOpenFlag(false);
				tableUI = new TableUI("���豸������Ϣ��",1,2);
				tableUI.show();
			}else {
				judgeBug();
			}
		}
		if(e.getSource() == backButton) {
			new SManagerUI().setReOpenFlag(true);
			new GManagerUI().setReOpenFlag(true);
			close();
		}
	}

	public void close() {
		jFrame.dispose();
	}
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		StoreUI storeUI = new StoreUI();
//		storeUI.show();
//	}
	
}
