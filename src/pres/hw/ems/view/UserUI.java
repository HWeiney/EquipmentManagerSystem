package pres.hw.ems.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import pres.hw.ems.util.ConfirmUtil;
import pres.hw.ems.util.MsgUtil;
import pres.hw.ems.util.QueryUtil;

import pres.hw.ems.dao.UserDao;
import pres.hw.ems.domain.User;
import pres.hw.ems.util.TextBorderUtlis;

public class UserUI implements ActionListener {

	static JFrame jFrame;

	private static Container container;

	private JPanel storePanel, userPanel;

	private JButton backButton, refreshButton, addButton, deleteButton, searchButton, alterButton;

	// private JScrollPane scrollPane;

	private JTextField idField, nameField, passwordField, repasswordField, phoneField;

	public static JComboBox<String> cmb;

	private TextBorderUtlis borderUtlis;

	// private static JTable table;

	private static List<User> storeUsers;

	private static String[] columns = { "用户ID", "用户NAME", "用户PHONE", "用户TYPE" };

	private static String[][] data = new String[24][4];
	
	private static DefaultTableModel tableModel;

	private TableRowSorter<TableModel> sorter;

	private static UserDao userDao;

	private List<User> users;

	private static Boolean usernameFlag = true;
	private static Boolean useridFlag = true;
	private static Boolean passwordFlag = true;
	private static Boolean delidFlag = false;
	private static int lastSize;
	
	public static int getLastSize() {
		return lastSize;
	}

	public static void setLastSize(int lastSize) {
		UserUI.lastSize = UserUI.lastSize + lastSize;
	}

	public UserUI() {
		
		// TODO Auto-generated constructor stub
	}

	public void TModel() {
		tableModel = new DefaultTableModel(setData(), columns);
	}
	
	public void showdata() {
		
		// 查询数据
		JTable table;
		JScrollPane scrollPane;

		// 数据展示在table中
		TModel();

		table = new JTable(tableModel);
		table.setFont(new Font("微软雅黑", Font.BOLD, 13));
		table.setForeground(new Color(255, 83, 139));
		// 数据居中
		DefaultTableCellRenderer dc = new DefaultTableCellRenderer();
		dc.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, dc);

		// 排序喵
		sorter = new TableRowSorter<TableModel>(table.getModel());
		sorter.setSortable(1, true);
		table.setRowSorter(sorter);
		// 可以滚动的面板,并设置属性
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 387, 200);

		storePanel.add(scrollPane);
		storePanel.add(refreshButton);
		// container.add(storePanel);
	}

	public void show() {
		//用于JTable刷新
		List<User> Users = new ArrayList<User>();
		userDao = new UserDao();
		Users = userDao.queryUsers();
		setLastSize(Users.size());

		jFrame = new JFrame("用户管理");
		jFrame.setBounds(580, 250, 400, 500);
		container = jFrame.getContentPane();
		container.setBackground(new Color(209, 242, 246));
		container.setLayout(null);

		storePanel = new JPanel();
		storePanel.setLayout(null);
		storePanel.setBounds(0, 0, jFrame.getWidth(), 250);
		storePanel.setOpaque(false);

		// 刷新按钮
		refreshButton = new JButton("刷新用户信息");
		refreshButton.setBounds(0, 200, 400, 30);
		refreshButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		refreshButton.setForeground(new Color(255, 255, 255));
		refreshButton.setBackground(new Color(135, 206, 250));
		refreshButton.setToolTipText("可刷新用户列表");
		refreshButton.addActionListener(this);
		
		showdata();
		
		// userPanel
		userPanel = new JPanel();
		userPanel = new JPanel();
		userPanel.setLayout(null);
		userPanel.setBounds(0, 235, jFrame.getWidth(), jFrame.getHeight() / 2);
		userPanel.setOpaque(false);
		users = new UserDao().queryUsers();

		idField = new JTextField("请输入用户数字ID");
		idField.setForeground(new Color(255, 83, 139));
		idField.setBounds(2, 0, 195, 35);
		borderUtlis = new TextBorderUtlis(new Color(135, 206, 250), 1, true);
		idField.setBorder(borderUtlis);
		idField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		idField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				String string = idField.getText();
				int id;
				boolean ch = false;
				if (!"".equals(string)) {// 非空
					// 判断输入是否是数字
					for (int i = 0; i < string.length(); i++) {
						if (string.charAt(i) < '0' || string.charAt(i) > '9') {
							ch = true;
						}
					}
					if (ch == false) {
						delidFlag = true;
					}
					if (ch) {
						idField.setText("请输入用户数字ID");
					} else {
						id = Integer.parseInt(string);
						if (!QueryUtil.searchUserId(id, users)) {
							useridFlag = false;
							// System.out.println("无该用户ID");
						}
					}
				} else {
					idField.setText("请输入用户数字ID");
					useridFlag = true;
				}
			}

			@Override
			// 有作用
			public void focusGained(FocusEvent e) {
				String string = idField.getText();
				if ("请输入用户数字ID".equals(string)) {
					idField.setText("");
				}
			}
		});
		userPanel.add(idField);

		nameField = new JTextField("请输入用户NAME");
		nameField.setBounds(195, 0, 190, 35);
		nameField.setForeground(new Color(255, 83, 139));
		nameField.setBorder(borderUtlis);
		nameField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		nameField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				String string = nameField.getText();
				if (!"".equals(string)) {
					if (!QueryUtil.searchUserName(string, users)) {
						usernameFlag = false;
						// System.out.println("无该用户NAME");
					}
				} else {
					nameField.setText("请输入用户NAME");
					usernameFlag = true;
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				String string = nameField.getText();
				if ("请输入用户NAME".equals(string)) {
					nameField.setText("");
				}
			}
		});
		userPanel.add(nameField);

		passwordField = new JTextField("请输入密码");
		passwordField.setBounds(2, 37, 195, 35);
		passwordField.setForeground(new Color(255, 83, 139));
		passwordField.setBorder(borderUtlis);
		passwordField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		passwordField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				String string = passwordField.getText();
				if ("".equals(string)) {
					passwordField.setText("请输入密码");
				} else if (string.length() < 6) {
					passwordField.setText("输入长度小于6位!");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				String string = passwordField.getText();
				if ("请输入密码".equals(string) || "输入长度小于6位!".equals(string)) {
					passwordField.setText("");
				}
			}
		});
		userPanel.add(passwordField);

		repasswordField = new JTextField("请确认密码");
		repasswordField.setBounds(195, 37, 190, 35);
		repasswordField.setForeground(new Color(255, 83, 139));
		repasswordField.setBorder(borderUtlis);
		repasswordField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		repasswordField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				String psw1 = passwordField.getText();
				String string = repasswordField.getText();
				if ("".equals(string)) {
					repasswordField.setText("请确认密码");
				} else if (!psw1.equals(string)) {
					repasswordField.setText("两次密码不一致!");
				} else {
					passwordFlag = false;
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				String string = repasswordField.getText();
				if ("请确认密码".equals(string) || "两次密码不一致!".equals(string)) {
					repasswordField.setText("");
				}
			}
		});
		userPanel.add(repasswordField);

		phoneField = new JTextField("请输入用户PHONE");
		phoneField.setBounds(2, 74, 195, 35);
		phoneField.setForeground(new Color(255, 83, 139));
		phoneField.setBorder(borderUtlis);
		phoneField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		phoneField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				String string = phoneField.getText();
				boolean ch = false;
				if ("".equals(string)) {
					phoneField.setText("请输入用户PHONE");
				} else {
					for (int i = 0; i < string.length(); i++) {
						if (string.charAt(i) < '0' || string.charAt(i) > '9') {
							ch = true;
						}
					}
					if (ch) {
						phoneField.setText("请输入用户PHONE");
					} else if (string.length() != 11) {
						phoneField.setText("Tel应是11位");
					}
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				String string = phoneField.getText();
				if ("请输入用户PHONE".equals(string) || "Tel应是11位".equals(string)) {
					phoneField.setText("");
				}
			}
		});
		userPanel.add(phoneField);

		cmb = new JComboBox<String>();
		cmb.addItem("设备管理员");
		cmb.addItem("超级管理员");
		cmb.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		cmb.setBounds(195, 74, 190, 35);
		cmb.setBackground(new Color(255, 255, 255));
		cmb.setForeground(new Color(255, 83, 139));
		cmb.setBorder(borderUtlis);
		cmb.setOpaque(false);
		userPanel.add(cmb);

		addButton = new JButton("添加用户");
		addButton.setBounds(12, 118, 200, 30);
		addButton.setBorder(borderUtlis);
		addButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		addButton.setForeground(new Color(255, 255, 255));
		addButton.setBackground(new Color(135, 206, 250));
		userPanel.add(addButton);
		addButton.setToolTipText("添加用户并自动更新列表");
		addButton.addActionListener(this);

		deleteButton = new JButton("删除用户");
		deleteButton.setBounds(217, 118, 157, 30);
		deleteButton.setBorder(borderUtlis);
		deleteButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		deleteButton.setForeground(new Color(255, 255, 255));
		deleteButton.setBackground(new Color(135, 206, 250));
		deleteButton.setToolTipText("根据用户ID和用户NAME删除");
		userPanel.add(deleteButton);
		deleteButton.addActionListener(this);

		alterButton = new JButton("修改用户");
		alterButton.setBounds(12, 157, 157, 30);
		alterButton.setBorder(borderUtlis);
		alterButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		alterButton.setForeground(new Color(255, 255, 255));
		alterButton.setBackground(new Color(135, 206, 250));
		alterButton.setToolTipText("根据ID修改用户信息:密码手机号、类型");
		userPanel.add(alterButton);
		alterButton.addActionListener(this);

		searchButton = new JButton("查询用户");
		searchButton.setBounds(174, 157, 200, 30);
		searchButton.setBorder(borderUtlis);
		searchButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		searchButton.setForeground(new Color(255, 255, 255));
		searchButton.setBackground(new Color(135, 206, 250));
		searchButton.setToolTipText("根据用户ID查询");
		userPanel.add(searchButton);
		searchButton.addActionListener(this);

		backButton = new JButton("返回上一界面");
		backButton.setBounds(0, 197, 400, 30);
		backButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		backButton.setForeground(new Color(255, 255, 255));
		backButton.setBackground(new Color(135, 206, 250));
		backButton.setToolTipText("返回超级管理员界面");
		userPanel.add(backButton);
		backButton.addActionListener(this);

		container.add(userPanel);
		container.add(storePanel);
		jFrame.setResizable(false);
		jFrame.setVisible(true);
		jFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				new SManagerUI().setReOpenFlag(true);
				close();
			}
		});
	}

	public String[][] setData() {

		storeUsers = new ArrayList<User>();

		userDao = new UserDao();
		storeUsers = userDao.queryUsers();

		int size = storeUsers.size();
		// System.out.println(storeUsers.size());

		for (int j = 0; j < size; j++) {

			User storeU = storeUsers.get(j);

			data[j][0] = storeU.getUserid() + "";
			data[j][1] = storeU.getUserName();
			data[j][2] = storeU.getPhone();
			data[j][3] = storeU.getType();

			// System.out.println(storeU.getUserid() + storeU.getUserName() +
			// storeU.getPhone() + storeU.getType());
		}
		//System.out.println(size + "lastSize" + getLastSize());
		for(int i = size; i < getLastSize(); i++) {
			data[i][0] = "";
			data[i][1] = "";
			data[i][2] = "";
			data[i][3] = "";
		}
		return data;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String name = nameField.getText();
		String password = repasswordField.getText();
		String phone = phoneField.getText();
		String type = cmb.getSelectedItem().toString();
		
		if (e.getSource() == refreshButton) {
			storePanel.removeAll();
			showdata();
			storePanel.updateUI();
		}
		if (e.getSource() == addButton) {
			if (!useridFlag && !usernameFlag && !passwordFlag) {
				String idString = idField.getText();
				int id = Integer.parseInt(idString);
				if (phone.equals("请输入用户PHONE") || phone.equals("Tel应是11位")) {
					phone = "";
				}
				MsgUtil.showdb(UserDao.insert(id, name, password, phone, type));
			} else {
				MsgUtil.show(false);
				// System.out.println("no");
			}
			reset();
			showdata();
		}
		if (e.getSource() == deleteButton) {
			// System.out.println(delidFlag);
			if (delidFlag && !"请输入用户NAME".equals(name)) {
				String idString = idField.getText();
				int id = Integer.parseInt(idString);
				if (QueryUtil.searchIdName(id, name, storeUsers)) {
					ConfirmUtil confirmUtil = new ConfirmUtil();
					confirmUtil.show(id + " " + name);
					ConfirmUtil.setId(id);
					ConfirmUtil.setName(name);
					reset();
				} else {
					MsgUtil.Msg(id + " " + name);
				}
			} else {
				MsgUtil.Msg("ID、NAME");
			}
		}
		if (e.getSource() == alterButton) {
			if (delidFlag) {
				String idString = idField.getText();
				int id = Integer.parseInt(idString);
				if(QueryUtil.searchUserId(id, storeUsers)) {
					String text1="",text2="";
					if("两次密码不一致!".equals(password) || "请确认密码".equals(password)) {
						ConfirmUtil.setPassword("无");
					}else {
						ConfirmUtil.setPassword(password);
						text1+="密码";
					}
					if("Tel应是11位".equals(phone) || "请输入用户PHONE".equals(phone)) {
						ConfirmUtil.setTelphone("无");
					}else {
						ConfirmUtil.setTelphone(phone);
						text2 +=" 号码";
					}
					
					ConfirmUtil.setType(type);
					ConfirmUtil confirmUtil = new ConfirmUtil();
					confirmUtil.altershow(id+"的"+text1+text2+" 类型");
					ConfirmUtil.setId(id);
					reset();
				}else {
					MsgUtil.Msg(id+"");
				}
			}else {
				MsgUtil.Msg("用户ID");
			}
		}
		if(e.getSource() == searchButton) {
			if(delidFlag) {
				String idString = idField.getText();
				int id = Integer.parseInt(idString);
				if(QueryUtil.searchUserId(id, storeUsers)) {
					new QueryUI(id).show();
					reset();
				}else {
					MsgUtil.Msg("用户ID");
				}
			}else {
				MsgUtil.Msg("用户ID");
			}
		}
		if(e.getSource() == backButton) {
			new SManagerUI().setReOpenFlag(true);
			close();
		}
	}

	public void test() {
		reset();
	}
	
	public void reset() {
		try {
			idField.setText("请输入用户数字ID");
			nameField.setText("请输入用户NAME");
			passwordField.setText("请输入密码");
			repasswordField.setText("请确认密码");
			phoneField.setText("请输入用户PHONE");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Reset is Error");
		}
		
	}

	public void close() {
		jFrame.dispose();
	}

//	public static void main(String[] args) {
//		new UserUI().show();
//		// new UserUI().setData();
//	}

}
