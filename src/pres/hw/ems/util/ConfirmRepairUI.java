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
import pres.hw.ems.dao.RepairDao;
import pres.hw.ems.domain.BuyEm;
import pres.hw.ems.domain.RepairEm;
import pres.hw.ems.view.RepairUI;

public class ConfirmRepairUI implements ActionListener{

	private String title = "设备报修订单";
	private JFrame jFrame;
	private Container container;
	private JPanel tablePanel,buttonPanel;
	private JTable table1,table2;
	private JScrollPane scrollPane1,scrollPane2;
	private DefaultTableModel tableModel1,tableModel2;
	private JButton yesButton,noButton;
	
	private String Repairno;
	private String Repairdate;
	private int Eno;
	private String Ename;
	private int Rcount;
	private String Rreason;
	private String username;
	private List<RepairEm> repairList;
	
	private String[][] data1 = new String[1][5];
	private String[][] data2 = new String[1][2];
	private String[] columns1 = { "报修编号", "设备号","设备名称", "报修数量","管理员" };
	private String[] columns2 = {"报修日期","报修原因" };
	
	public ConfirmRepairUI() {
		// TODO Auto-generated constructor stub
	}
	
	public ConfirmRepairUI(RepairEm repairEm) {
		// TODO Auto-generated constructor stub
		Repairno = repairEm.getRepairno();
		Ename = repairEm.getEname();
		username = repairEm.getUsername();
		Rcount = repairEm.getRcount();
		Rreason = repairEm.getRreason();
		
		repairList = new ArrayList<RepairEm>();
		new RepairDao();
		repairList = RepairDao.queryList();
		int size = repairList.size();
		for (int i = 0; i < size; i++) {
			RepairEm em = repairList.get(i);
			if(em.getEname().equals(Ename)){
				Eno = em.getEno();
			}
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(date);
		Repairdate = dateStr;
	}
	
	public void setData() {
		data1[0][0] = Repairno;
		data1[0][1] = Eno + "";
		data1[0][2] = Ename;
		data1[0][3] = Rcount + "";
		data1[0][4] = username;
		
		data2[0][0] = Repairdate;
		data2[0][1] = Rreason;
	}
	
	public void show() {
		jFrame = new JFrame(title);
		jFrame.setBounds(510, 570, 540, 180);
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
	
		
		//2个JScrollPane
		scrollPane1 = new JScrollPane(table1);
		scrollPane1.setBounds(15, 10, 500, 40);
		
		scrollPane2 = new JScrollPane(table2);
		scrollPane2.setBounds(15, 50, 500, 40);
		
		tablePanel.add(scrollPane1);
		tablePanel.add(scrollPane2);
		
		//2个JButton
		yesButton = new JButton("确认报修");
		yesButton.setBounds(75, 10, 185, 30);
		yesButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		yesButton.setForeground(new Color(0, 0, 0));
		yesButton.setBackground(new Color(64, 200, 164));
		yesButton.addActionListener(this);
		
		noButton = new JButton("取消报修");
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
			MsgUtil.buyshowdb(RepairDao.insert(Repairno, Repairdate,Eno, Rcount, Rreason, username));
			close();
			new RepairUI().reset();
		}
		if(e.getSource() == noButton) {
			close();
		}
	}

	public void close(){
		jFrame.dispose();
	}
//	public static void main(String[] args) {
//		RepairEm repairEm = new RepairEm("20220615002","试管",1,"试管碎裂","root");
//		new ConfirmRepairUI(repairEm).show();
//	}

}
