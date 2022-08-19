package pres.hw.ems.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import pres.hw.ems.dao.UserDao;
import pres.hw.ems.domain.User;

import javax.swing.*;

public class QueryUI implements ActionListener{

	private static String title = "用户详细信息";
	private JFrame jFrame;
	private Container container;
	private JPanel jPanel;
	private JTable table,table2;
	private JScrollPane scrollPane,scrollPane2;
	DefaultTableModel tableModel,tableModel2;
	
	private static String[] columns = { "UserId", "UserName","PassWord", "TelPhone","UserType" };
	private static String[] col = {"CreateTime","UpdateTime" };	
	private static String[][] data = new String[1][5];
	private static String[][] data2 = new String[1][2];
	private static List<User> storeUsers;
	private static UserDao userDao;
	private static int UserId;
	
	public QueryUI(int id) {
		// TODO Auto-generated constructor stub
		QueryUI.setUserId(id);
	}
	
	public String[][] setData() {
		
		storeUsers = new ArrayList<User>();

		userDao = new UserDao();
		storeUsers = userDao.queryAllinfo(getUserId());

		int size = storeUsers.size();
		// System.out.println(storeUsers.size());

		for (int j = 0; j < size; j++) {

			User storeU = storeUsers.get(j);

			data[j][0] = storeU.getUserid() + "";
			data[j][1] = storeU.getUserName();
			data[j][2] = storeU.getPassword();
			data[j][3] = storeU.getPhone();
			data[j][4] = storeU.getType();
			//data[j][5] = storeU.getCreatetime();
			//data[j][6] = storeU.getUpdatetime();

		}
		//test
//		for(int i=0;i<size;i++) {
//			for(int j = 0;j<7;j++) {
//				System.out.println(data[i][j]);
//			}
//		}
		return data;
		
	}
	public String[][] setData2() {
		
		storeUsers = new ArrayList<User>();

		userDao = new UserDao();
		storeUsers = userDao.queryAllinfo(getUserId());

		int size = storeUsers.size();
		// System.out.println(storeUsers.size());

		for (int j = 0; j < size; j++) {

			User storeU = storeUsers.get(j);
			data2[j][0] = storeU.getCreatetime();
			data2[j][1] = storeU.getUpdatetime();
		}
		return data2;
		
	}
	public void show() {
		jFrame = new JFrame(title);
		jFrame.setBounds(510, 382, 543, 135);
		container = jFrame.getContentPane();
		container.setBackground(new Color(209, 242, 246));
		container.setLayout(null);
		
		jPanel = new JPanel();
		jPanel.setLayout(null);
		jPanel.setBounds(0, 0, 600, 100);
		jPanel.setOpaque(false);
		
		tableModel = new DefaultTableModel(setData(), columns);
		table = new JTable(tableModel);
		table.setBounds(0, 0, 400, 100);
		table.setFont(new Font("微软雅黑", Font.BOLD, 13));
		table.setForeground(new Color(255, 83, 139));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		tableModel2 = new DefaultTableModel(setData2(), col);
		table2 = new JTable(tableModel2);
		table2.setBounds(0, 100, 200, 100);
		table2.setFont(new Font("微软雅黑", Font.BOLD, 13));
		table2.setForeground(new Color(255, 83, 139));
		table2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		// 数据居中
		DefaultTableCellRenderer dc = new DefaultTableCellRenderer();
		dc.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, dc);
		table2.setDefaultRenderer(Object.class, dc);
		// 可以滚动的面板,并设置属性
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(15, 10, 500, 40);
		
		scrollPane2 = new JScrollPane(table2);
		scrollPane2.setBounds(15, 50, 500, 40);
		
		jPanel.add(scrollPane);
		jPanel.add(scrollPane2);
		container.add(jPanel);
		jFrame.setResizable(false);
		jFrame.setVisible(true);
		jFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				close();
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public static int getUserId() {
		return UserId;
	}

	public static void setUserId(int userId) {
		UserId = userId;
	}

	public void close(){
		jFrame.dispose();
	}
	
	
	public static void main(String[] args) {
		new QueryUI(1001).show();
	}
}
