package pres.hw.ems.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import pres.hw.ems.dao.BuyDao;
import pres.hw.ems.dao.LendDao;
import pres.hw.ems.dao.RepairDao;
import pres.hw.ems.domain.BuyEm;
import pres.hw.ems.domain.LendEm;
import pres.hw.ems.domain.RepairEm;

public class TableUI {
	private JFrame jFrame;
	private Container container;
	private JPanel listPanel;
	private JLabel titleLabel;
	private JTable table1;
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;

	private String jFrameTitle;
	private List<BuyEm> buyList;
	private List<LendEm> lendList;
	private List<RepairEm> repairList;

	private static BuyDao buyDao;
	private static LendDao lendDao;
	private static RepairDao repairDao;
	
	private String[] columns1 = { "订单号", "设备号", "设备名称", "设备状态", "管理人员", "购买日期", "购买数量", "购买价格", "供应商姓名", "供应商联系方式" };
	private String[][] data1 = new String[30][10];
	
	private String[] columns2 = { "借用编号","借用时间", "设备号", "设备名称", "借用数量", "借用人", "联系方式", "归还日期", "管理员" };
	private String[][] data2 = new String[30][9];
	
	private String[] columns3 = {"报修编码","报修时间","设备编号","设备名称","报修数量","报修原因","管理员"};
	private String[][] data3 = new String[30][7];
	private int x, y, width, height;

	/**
	 * 设备购买订单
	 * @param title
	 */
	public TableUI(String title) {
		// TODO Auto-generated constructor stub
		jFrameTitle = "购买订单";
		x = 180;
		y = 400;
		width = 1213;
		height = 345;

		listPanel = new JPanel();
		listPanel.setBackground(new Color(237, 125, 49));
		listPanel.setBounds(0, 0, 1200, 310);
		listPanel.setLayout(null);

		titleLabel = new JLabel(title);
		titleLabel.setBackground(new Color(112, 173, 71));
		titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
		titleLabel.setForeground(new Color(255, 255, 255));
		titleLabel.setBounds(0, 0, 1200, 30);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setOpaque(true);
		listPanel.add(titleLabel);

		// 表格
		tableModel = new DefaultTableModel(setData1(), columns1);
		table1 = new JTable(tableModel);
		table1.setFont(new Font("微软雅黑", Font.BOLD, 13));
		table1.setForeground(new Color(255, 83, 139));
		// 数据居中
		DefaultTableCellRenderer dc = new DefaultTableCellRenderer();
		dc.setHorizontalAlignment(SwingConstants.CENTER);
		table1.setDefaultRenderer(Object.class, dc);
		scrollPane = new JScrollPane(table1);
		scrollPane.setBounds(0, 30, 1200, 280);

		table1.getColumnModel().getColumn(0).setPreferredWidth(40);
		table1.getColumnModel().getColumn(1).setPreferredWidth(15);
		table1.getColumnModel().getColumn(2).setPreferredWidth(30);
		table1.getColumnModel().getColumn(3).setPreferredWidth(10);
		table1.getColumnModel().getColumn(4).setPreferredWidth(10);
		table1.getColumnModel().getColumn(5).setPreferredWidth(85);
		table1.getColumnModel().getColumn(6).setPreferredWidth(5);
		table1.getColumnModel().getColumn(7).setPreferredWidth(20);
		table1.getColumnModel().getColumn(8).setPreferredWidth(10);
		table1.getColumnModel().getColumn(9).setPreferredWidth(10);

		listPanel.add(scrollPane);
	}

	/**
	 * 设备借用清单
	 * @param title
	 * @param i
	 */
	public TableUI(String title, int i) {// i无意义,用来加参数-构造函数重载
		jFrameTitle = "设备借用清单";
		x = 180;
		y = 400;
		width = 1213;
		height = 345;

		listPanel = new JPanel();
		listPanel.setBackground(new Color(237, 125, 49));
		listPanel.setBounds(0, 0, 1200, 310);
		listPanel.setLayout(null);

		titleLabel = new JLabel(title);
		titleLabel.setBackground(new Color(112, 173, 71));
		titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
		titleLabel.setForeground(new Color(255, 255, 255));
		titleLabel.setBounds(0, 0, 1200, 30);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setOpaque(true);
		listPanel.add(titleLabel);
		
		// 表格
		tableModel = new DefaultTableModel(setData2(), columns2);
		table1 = new JTable(tableModel);
		table1.setFont(new Font("微软雅黑", Font.BOLD, 13));
		table1.setForeground(new Color(255, 83, 139));
		// 数据居中
		DefaultTableCellRenderer dc = new DefaultTableCellRenderer();
		dc.setHorizontalAlignment(SwingConstants.CENTER);
		table1.setDefaultRenderer(Object.class, dc);
		scrollPane = new JScrollPane(table1);
		scrollPane.setBounds(0, 30, 1200, 280);
		
		table1.getColumnModel().getColumn(0).setPreferredWidth(10);
		table1.getColumnModel().getColumn(1).setPreferredWidth(85);
		table1.getColumnModel().getColumn(2).setPreferredWidth(10);
		table1.getColumnModel().getColumn(3).setPreferredWidth(10);
		table1.getColumnModel().getColumn(4).setPreferredWidth(10);
		table1.getColumnModel().getColumn(5).setPreferredWidth(10);
		
		listPanel.add(scrollPane);
	}

	/**
	 * 设备借用清单
	 * @param title
	 * @param i
	 */
	public TableUI(String title, int i,int j) {// i,j无意义,用来加参数-构造函数重载
		jFrameTitle = "设备报修清单";
		x = 180;
		y = 400;
		width = 1213;
		height = 345;

		listPanel = new JPanel();
		listPanel.setBackground(new Color(237, 125, 49));
		listPanel.setBounds(0, 0, 1200, 310);
		listPanel.setLayout(null);

		titleLabel = new JLabel(title);
		titleLabel.setBackground(new Color(112, 173, 71));
		titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
		titleLabel.setForeground(new Color(255, 255, 255));
		titleLabel.setBounds(0, 0, 1200, 30);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setOpaque(true);
		listPanel.add(titleLabel);
		
		// 表格
		tableModel = new DefaultTableModel(setData3(), columns3);
		table1 = new JTable(tableModel);
		table1.setFont(new Font("微软雅黑", Font.BOLD, 13));
		table1.setForeground(new Color(255, 83, 139));
		// 数据居中
		DefaultTableCellRenderer dc = new DefaultTableCellRenderer();
		dc.setHorizontalAlignment(SwingConstants.CENTER);
		table1.setDefaultRenderer(Object.class, dc);
		scrollPane = new JScrollPane(table1);
		scrollPane.setBounds(0, 30, 1200, 280);
		
		listPanel.add(scrollPane);
	}
	
	public void show() {
		jFrame = new JFrame(jFrameTitle);
		jFrame.setBounds(x, y, width, height);
		container = jFrame.getContentPane();
		container.setBackground(new Color(209, 242, 246));
		container.setLayout(null);

		container.add(listPanel);
		jFrame.setResizable(false);
		jFrame.setVisible(true);
		jFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				new StoreUI().setTabelReOpenFlag(true);
				close();
				//System.exit(0);
			}
		});
	}

	public String[][] setData1() {

		buyList = new ArrayList<BuyEm>();

		buyDao = new BuyDao();
		buyList = BuyDao.queryBuyList();

		int size = buyList.size();

		for (int i = 0; i < size; i++) {
			BuyEm buyEm = buyList.get(i);
			data1[i][0] = buyEm.getListno();
			data1[i][1] = buyEm.getEno() + "";
			data1[i][2] = buyEm.getEname();
			data1[i][3] = buyEm.getEstatus();
			data1[i][4] = buyEm.getUsername();
			data1[i][5] = buyEm.getBuydate();
			data1[i][6] = buyEm.getEcount() + "";
			data1[i][7] = buyEm.getBuyPrice() + "";
			data1[i][8] = buyEm.getSaleName();
			data1[i][9] = buyEm.getSalePhone();
		}

		return data1;
	}

	public String[][] setData2(){
		lendList = new ArrayList<LendEm>();
		lendDao = new LendDao();
		lendList = LendDao.queryLendList();
		
		int size = lendList.size();
		
		for(int i=0;i<size;i++) {
			LendEm lendEm = lendList.get(i);
			data2[i][0] = lendEm.getLendno();
			data2[i][1] = lendEm.getLenddate();
			data2[i][2] = lendEm.getEno() + "";
			data2[i][3] = lendEm.getEname();
			data2[i][4] = lendEm.getLcount() + "";
			data2[i][5] = lendEm.getLendname();
			data2[i][6] = lendEm.getLendphone();
			data2[i][7] = lendEm.getReturndate() + "";
			data2[i][8] = lendEm.getUsername() + "";
		}
		return data2;
	}
	
	public String[][] setData3(){
		repairList = new ArrayList<RepairEm>();
		repairDao = new RepairDao();
		repairList = RepairDao.queryRepairList();
		
		int size = repairList.size();
		
		for(int i=0;i<size;i++) {
			RepairEm repairEm = repairList.get(i);
			data3[i][0] = repairEm.getRepairno();
			data3[i][1] = repairEm.getRepairdate();
			data3[i][2] = repairEm.getEno() + "";
			data3[i][3] = repairEm.getEname();
			data3[i][4] = repairEm.getRcount() + "";
			data3[i][5] = repairEm.getRreason();
			data3[i][6] = repairEm.getUsername();
		}
		return data3;
	}
	
	public void close() {
		jFrame.dispose();
	}

//	public static void main(String[] args) {
//		TableUI tableUI = new TableUI("skdf",1,2);
//		tableUI.show();
//	}
}
