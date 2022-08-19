package pres.hw.ems.util;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import pres.hw.ems.dao.BuyDao;
import pres.hw.ems.domain.BuyEm;

public class ConfirmBuyUtil implements ActionListener{

	private String title = "设备购买订单";
	private JFrame jFrame;
	private Container container;
	private JPanel tablePanel,buttonPanel;
	private JTable table1,table2;
	private JScrollPane scrollPane1,scrollPane2;
	private DefaultTableModel tableModel1,tableModel2;
	private JButton yesButton,noButton;
	
	private String Listno;
	private int Eno;
	private String Ename;
	private String username;
	private String Buydate;
	private int Ecount;
	private int BuyPrice;
	private String SaleName;
	private String SalePhone;
	private String Estatus = "在库";
	private List<BuyEm> emList;
	
	private String[][] data1 = new String[1][5];
	private String[][] data2 = new String[1][5];
	private String[] columns1 = { "Listno", "Eno","Ename", "Ecount","BuyPrice" };
	private String[] columns2 = {"SaleName","SalePhone","username","Buydate","Estatus" };
	
	public ConfirmBuyUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public ConfirmBuyUtil(BuyEm buyEm) {
		// TODO Auto-generated constructor stub
		Listno = buyEm.getListno();
		Ename = buyEm.getEname();
		username = buyEm.getUsername();
		Ecount = buyEm.getEcount();
		BuyPrice = buyEm.getBuyPrice();
		SaleName = buyEm.getSaleName();
		SalePhone = buyEm.getSalePhone();
		
		emList = new ArrayList<BuyEm>();
		new BuyDao();
		emList = BuyDao.queryList();
		int size = emList.size();
		for (int i = 0; i < size; i++) {
			BuyEm em = emList.get(i);
			if(em.getEname().equals(Ename)){
				Eno = em.getEno();
			}
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(date);
		Buydate = dateStr;
	}
	
	public void setData() {
		data1[0][0] = Listno;
		data1[0][1] = Eno + "";
		data1[0][2] = Ename;
		data1[0][3] = Ecount + "";
		data1[0][4] = BuyPrice + "";
		
		data2[0][0] = SaleName;
		data2[0][1] = SalePhone;
		data2[0][2] = username;
		data2[0][3] = Buydate;
		data2[0][4] = Estatus;
	}
	
	public void show() {
		jFrame = new JFrame(title);
		jFrame.setBounds(510, 382, 540, 180);
		container = jFrame.getContentPane();
		container.setBackground(new Color(209, 242, 246));
		container.setLayout(null);
		
		//2个JPanel
		tablePanel = new JPanel();
		tablePanel.setBackground(new Color(255, 80, 80));
		tablePanel.setBounds(0, 0, 526, 95);
		tablePanel.setOpaque(true);
		tablePanel.setLayout(null);
		
		buttonPanel = new JPanel(); 
		buttonPanel.setBackground(new Color(255, 80, 80));
		buttonPanel.setBounds(0, 95, 526, 48);
		buttonPanel.setOpaque(true);
		buttonPanel.setLayout(null);
		
		setData();
		// 数据居中
		DefaultTableCellRenderer dc = new DefaultTableCellRenderer();
		dc.setHorizontalAlignment(SwingConstants.CENTER);
		//2个JTable
		tableModel1 = new DefaultTableModel(data1, columns1);
		table1 = new JTable(tableModel1);
		table1.setBounds(0, 0, 400, 100);
		table1.setFont(new Font("微软雅黑", Font.BOLD, 13));
		table1.setForeground(new Color(255, 83, 139));
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table1.setDefaultRenderer(Object.class, dc);
		
		tableModel2 = new DefaultTableModel(data2, columns2);
		table2 = new JTable(tableModel2);
		table2.setBounds(0, 100, 200, 100);
		table2.setFont(new Font("微软雅黑", Font.BOLD, 13));
		table2.setForeground(new Color(255, 83, 139));
		//table2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table2.setDefaultRenderer(Object.class, dc);
		
		//table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table2.getColumnModel().getColumn(0).setPreferredWidth(40);
		table2.getColumnModel().getColumn(1).setPreferredWidth(74);
		table2.getColumnModel().getColumn(2).setPreferredWidth(54);
		table2.getColumnModel().getColumn(3).setPreferredWidth(114);
		table2.getColumnModel().getColumn(4).setPreferredWidth(34);
		
		//2个JScrollPane
		scrollPane1 = new JScrollPane(table1);
		scrollPane1.setBounds(15, 10, 500, 40);
		
		scrollPane2 = new JScrollPane(table2);
		scrollPane2.setBounds(15, 50, 500, 40);
		
		tablePanel.add(scrollPane1);
		tablePanel.add(scrollPane2);
		
		//2个JButton
		yesButton = new JButton("确认购买");
		yesButton.setBounds(75, 10, 185, 30);
		yesButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		yesButton.setForeground(new Color(0, 0, 0));
		yesButton.setBackground(new Color(64, 200, 164));
		yesButton.addActionListener(this);
		
		noButton = new JButton("取消购买");
		noButton.setBounds(270, 10, 185, 30);
		noButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		noButton.setForeground(new Color(0, 0, 0));
		noButton.setBackground(new Color(64, 200, 164));
		noButton.addActionListener(this);
		
		buttonPanel.add(yesButton);
		buttonPanel.add(noButton);
		
		container.add(tablePanel);
		container.add(buttonPanel);
		
		jFrame.setResizable(false);
		jFrame.setVisible(true);
		jFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//TODO Auto-generated method stub
				close();
				//System.exit(0);
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == yesButton) {
			MsgUtil.buyshowdb(BuyDao.insert(Listno, Eno, username, Ecount, Buydate, BuyPrice, SaleName, SalePhone, Estatus));
			close();
		}
		if(e.getSource() == noButton) {
			close();
		}
	}

	public void close(){
		jFrame.dispose();
	}
//	public static void main(String[] args) {
//	// TODO Auto-generated method stub
////	new ConfirmBuyUtil().show();
//	BuyEm buyEm = new BuyEm("20220605005","玻璃棒","Han",4,100,"小悦","15177776666");
//	new ConfirmBuyUtil(buyEm).show();
//	}
}
