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

	private static String[] columns = { "�û�ID", "�û�NAME", "�û�PHONE", "�û�TYPE" };

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
		
		// ��ѯ����
		JTable table;
		JScrollPane scrollPane;

		// ����չʾ��table��
		TModel();

		table = new JTable(tableModel);
		table.setFont(new Font("΢���ź�", Font.BOLD, 13));
		table.setForeground(new Color(255, 83, 139));
		// ���ݾ���
		DefaultTableCellRenderer dc = new DefaultTableCellRenderer();
		dc.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, dc);

		// ������
		sorter = new TableRowSorter<TableModel>(table.getModel());
		sorter.setSortable(1, true);
		table.setRowSorter(sorter);
		// ���Թ��������,����������
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 387, 200);

		storePanel.add(scrollPane);
		storePanel.add(refreshButton);
		// container.add(storePanel);
	}

	public void show() {
		//����JTableˢ��
		List<User> Users = new ArrayList<User>();
		userDao = new UserDao();
		Users = userDao.queryUsers();
		setLastSize(Users.size());

		jFrame = new JFrame("�û�����");
		jFrame.setBounds(580, 250, 400, 500);
		container = jFrame.getContentPane();
		container.setBackground(new Color(209, 242, 246));
		container.setLayout(null);

		storePanel = new JPanel();
		storePanel.setLayout(null);
		storePanel.setBounds(0, 0, jFrame.getWidth(), 250);
		storePanel.setOpaque(false);

		// ˢ�°�ť
		refreshButton = new JButton("ˢ���û���Ϣ");
		refreshButton.setBounds(0, 200, 400, 30);
		refreshButton.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		refreshButton.setForeground(new Color(255, 255, 255));
		refreshButton.setBackground(new Color(135, 206, 250));
		refreshButton.setToolTipText("��ˢ���û��б�");
		refreshButton.addActionListener(this);
		
		showdata();
		
		// userPanel
		userPanel = new JPanel();
		userPanel = new JPanel();
		userPanel.setLayout(null);
		userPanel.setBounds(0, 235, jFrame.getWidth(), jFrame.getHeight() / 2);
		userPanel.setOpaque(false);
		users = new UserDao().queryUsers();

		idField = new JTextField("�������û�����ID");
		idField.setForeground(new Color(255, 83, 139));
		idField.setBounds(2, 0, 195, 35);
		borderUtlis = new TextBorderUtlis(new Color(135, 206, 250), 1, true);
		idField.setBorder(borderUtlis);
		idField.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		idField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				String string = idField.getText();
				int id;
				boolean ch = false;
				if (!"".equals(string)) {// �ǿ�
					// �ж������Ƿ�������
					for (int i = 0; i < string.length(); i++) {
						if (string.charAt(i) < '0' || string.charAt(i) > '9') {
							ch = true;
						}
					}
					if (ch == false) {
						delidFlag = true;
					}
					if (ch) {
						idField.setText("�������û�����ID");
					} else {
						id = Integer.parseInt(string);
						if (!QueryUtil.searchUserId(id, users)) {
							useridFlag = false;
							// System.out.println("�޸��û�ID");
						}
					}
				} else {
					idField.setText("�������û�����ID");
					useridFlag = true;
				}
			}

			@Override
			// ������
			public void focusGained(FocusEvent e) {
				String string = idField.getText();
				if ("�������û�����ID".equals(string)) {
					idField.setText("");
				}
			}
		});
		userPanel.add(idField);

		nameField = new JTextField("�������û�NAME");
		nameField.setBounds(195, 0, 190, 35);
		nameField.setForeground(new Color(255, 83, 139));
		nameField.setBorder(borderUtlis);
		nameField.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		nameField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				String string = nameField.getText();
				if (!"".equals(string)) {
					if (!QueryUtil.searchUserName(string, users)) {
						usernameFlag = false;
						// System.out.println("�޸��û�NAME");
					}
				} else {
					nameField.setText("�������û�NAME");
					usernameFlag = true;
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				String string = nameField.getText();
				if ("�������û�NAME".equals(string)) {
					nameField.setText("");
				}
			}
		});
		userPanel.add(nameField);

		passwordField = new JTextField("����������");
		passwordField.setBounds(2, 37, 195, 35);
		passwordField.setForeground(new Color(255, 83, 139));
		passwordField.setBorder(borderUtlis);
		passwordField.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		passwordField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				String string = passwordField.getText();
				if ("".equals(string)) {
					passwordField.setText("����������");
				} else if (string.length() < 6) {
					passwordField.setText("���볤��С��6λ!");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				String string = passwordField.getText();
				if ("����������".equals(string) || "���볤��С��6λ!".equals(string)) {
					passwordField.setText("");
				}
			}
		});
		userPanel.add(passwordField);

		repasswordField = new JTextField("��ȷ������");
		repasswordField.setBounds(195, 37, 190, 35);
		repasswordField.setForeground(new Color(255, 83, 139));
		repasswordField.setBorder(borderUtlis);
		repasswordField.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		repasswordField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				String psw1 = passwordField.getText();
				String string = repasswordField.getText();
				if ("".equals(string)) {
					repasswordField.setText("��ȷ������");
				} else if (!psw1.equals(string)) {
					repasswordField.setText("�������벻һ��!");
				} else {
					passwordFlag = false;
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				String string = repasswordField.getText();
				if ("��ȷ������".equals(string) || "�������벻һ��!".equals(string)) {
					repasswordField.setText("");
				}
			}
		});
		userPanel.add(repasswordField);

		phoneField = new JTextField("�������û�PHONE");
		phoneField.setBounds(2, 74, 195, 35);
		phoneField.setForeground(new Color(255, 83, 139));
		phoneField.setBorder(borderUtlis);
		phoneField.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		phoneField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				String string = phoneField.getText();
				boolean ch = false;
				if ("".equals(string)) {
					phoneField.setText("�������û�PHONE");
				} else {
					for (int i = 0; i < string.length(); i++) {
						if (string.charAt(i) < '0' || string.charAt(i) > '9') {
							ch = true;
						}
					}
					if (ch) {
						phoneField.setText("�������û�PHONE");
					} else if (string.length() != 11) {
						phoneField.setText("TelӦ��11λ");
					}
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				String string = phoneField.getText();
				if ("�������û�PHONE".equals(string) || "TelӦ��11λ".equals(string)) {
					phoneField.setText("");
				}
			}
		});
		userPanel.add(phoneField);

		cmb = new JComboBox<String>();
		cmb.addItem("�豸����Ա");
		cmb.addItem("��������Ա");
		cmb.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		cmb.setBounds(195, 74, 190, 35);
		cmb.setBackground(new Color(255, 255, 255));
		cmb.setForeground(new Color(255, 83, 139));
		cmb.setBorder(borderUtlis);
		cmb.setOpaque(false);
		userPanel.add(cmb);

		addButton = new JButton("����û�");
		addButton.setBounds(12, 118, 200, 30);
		addButton.setBorder(borderUtlis);
		addButton.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		addButton.setForeground(new Color(255, 255, 255));
		addButton.setBackground(new Color(135, 206, 250));
		userPanel.add(addButton);
		addButton.setToolTipText("����û����Զ������б�");
		addButton.addActionListener(this);

		deleteButton = new JButton("ɾ���û�");
		deleteButton.setBounds(217, 118, 157, 30);
		deleteButton.setBorder(borderUtlis);
		deleteButton.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		deleteButton.setForeground(new Color(255, 255, 255));
		deleteButton.setBackground(new Color(135, 206, 250));
		deleteButton.setToolTipText("�����û�ID���û�NAMEɾ��");
		userPanel.add(deleteButton);
		deleteButton.addActionListener(this);

		alterButton = new JButton("�޸��û�");
		alterButton.setBounds(12, 157, 157, 30);
		alterButton.setBorder(borderUtlis);
		alterButton.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		alterButton.setForeground(new Color(255, 255, 255));
		alterButton.setBackground(new Color(135, 206, 250));
		alterButton.setToolTipText("����ID�޸��û���Ϣ:�����ֻ��š�����");
		userPanel.add(alterButton);
		alterButton.addActionListener(this);

		searchButton = new JButton("��ѯ�û�");
		searchButton.setBounds(174, 157, 200, 30);
		searchButton.setBorder(borderUtlis);
		searchButton.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		searchButton.setForeground(new Color(255, 255, 255));
		searchButton.setBackground(new Color(135, 206, 250));
		searchButton.setToolTipText("�����û�ID��ѯ");
		userPanel.add(searchButton);
		searchButton.addActionListener(this);

		backButton = new JButton("������һ����");
		backButton.setBounds(0, 197, 400, 30);
		backButton.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		backButton.setForeground(new Color(255, 255, 255));
		backButton.setBackground(new Color(135, 206, 250));
		backButton.setToolTipText("���س�������Ա����");
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
				if (phone.equals("�������û�PHONE") || phone.equals("TelӦ��11λ")) {
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
			if (delidFlag && !"�������û�NAME".equals(name)) {
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
				MsgUtil.Msg("ID��NAME");
			}
		}
		if (e.getSource() == alterButton) {
			if (delidFlag) {
				String idString = idField.getText();
				int id = Integer.parseInt(idString);
				if(QueryUtil.searchUserId(id, storeUsers)) {
					String text1="",text2="";
					if("�������벻һ��!".equals(password) || "��ȷ������".equals(password)) {
						ConfirmUtil.setPassword("��");
					}else {
						ConfirmUtil.setPassword(password);
						text1+="����";
					}
					if("TelӦ��11λ".equals(phone) || "�������û�PHONE".equals(phone)) {
						ConfirmUtil.setTelphone("��");
					}else {
						ConfirmUtil.setTelphone(phone);
						text2 +=" ����";
					}
					
					ConfirmUtil.setType(type);
					ConfirmUtil confirmUtil = new ConfirmUtil();
					confirmUtil.altershow(id+"��"+text1+text2+" ����");
					ConfirmUtil.setId(id);
					reset();
				}else {
					MsgUtil.Msg(id+"");
				}
			}else {
				MsgUtil.Msg("�û�ID");
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
					MsgUtil.Msg("�û�ID");
				}
			}else {
				MsgUtil.Msg("�û�ID");
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
			idField.setText("�������û�����ID");
			nameField.setText("�������û�NAME");
			passwordField.setText("����������");
			repasswordField.setText("��ȷ������");
			phoneField.setText("�������û�PHONE");
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
