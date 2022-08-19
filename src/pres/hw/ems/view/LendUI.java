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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import pres.hw.ems.dao.BuyDao;
import pres.hw.ems.dao.LendDao;
import pres.hw.ems.dao.UserDao;
import pres.hw.ems.domain.BuyEm;
import pres.hw.ems.domain.LendEm;
import pres.hw.ems.domain.User;
import pres.hw.ems.util.ConfirmBackUtil;
import pres.hw.ems.util.ConfirmBuyUtil;
import pres.hw.ems.util.ConfirmLendUtil;
import pres.hw.ems.util.MsgUtil;
import pres.hw.ems.util.TextBorderUtlis;

public class LendUI implements ActionListener {

	private JFrame jFrame;
	private Container container;
	private JPanel listPanel, numPanel, lendPanel;
	private JLabel titleLabel, numLabel, clockLabel, emLabel, cntLabel, returnLabel, lendLabel, managerLabel;
	private JComboBox<String> emCmb, numCmb, managerCmb;
	private JTextField dateField, nameField, phoneField;
	private JButton lendButton, backButton, refreshButton, returnButton;
	private TextBorderUtlis borderUtlis;

	private boolean dateFlag, nameFlag, phoneFlag;

	private String Listno;
	private String Year;
	private String[][] data = new String[30][3];
	private List<BuyEm> emList;
	private List<User> userList;
	private static String returndate;
	private static BuyDao buyDao;

	public LendUI() {
		// TODO Auto-generated constructor stub
	}

	public void show() {

		jFrame = new JFrame("设备借用");
		jFrame.setBounds(580, 250, 400, 500);
		container = jFrame.getContentPane();
		container.setBackground(new Color(209, 242, 246));
		container.setLayout(null);

		// 3个JPanel
		listPanel = new JPanel();
		listPanel.setBackground(new Color(237, 125, 49));
		listPanel.setBounds(0, 0, 400, 190);
		listPanel.setLayout(null);

		numPanel = new JPanel();
		numPanel.setBackground(new Color(237, 125, 49));
		numPanel.setBounds(0, 190, 400, 70);
		numPanel.setLayout(null);

		lendPanel = new JPanel();
		lendPanel.setBackground(Color.yellow);
		lendPanel.setBounds(0, 260, 400, 203);
		lendPanel.setLayout(null);
		lendPanel.setOpaque(false);

		// 边框美化
		borderUtlis = new TextBorderUtlis(new Color(112, 48, 160), 1, true);

		// 设备名称
		emLabel = new JLabel("设备名称:");
		emLabel.setBorder(borderUtlis);
		emLabel.setBounds(5, 10, 105, 30);
		emLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		emLabel.setForeground(new Color(0, 0, 0));
		emLabel.setBackground(new Color(223, 183, 148));
		emLabel.setHorizontalAlignment(SwingConstants.CENTER);
		emLabel.setOpaque(true);
		lendPanel.add(emLabel);
		emCmb();

		// 购买数量和下拉框
		cntLabel = new JLabel("设备数量:");
		cntLabel.setBorder(borderUtlis);
		cntLabel.setBounds(5, 50, 105, 30);
		cntLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		cntLabel.setForeground(new Color(0, 0, 0));
		cntLabel.setBackground(new Color(223, 183, 148));
		cntLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cntLabel.setOpaque(true);

		numCmb = new JComboBox<String>();
		for (int i = 1; i <= 10; i++) {
			numCmb.addItem(i + "");
		}
		numCmb.setBounds(120, 50, 50, 30);
		numCmb.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		numCmb.setBackground(new Color(135, 206, 250));
		numCmb.setForeground(new Color(0, 0, 0));
		numCmb.setMaximumRowCount(4);
		lendPanel.add(cntLabel);
		lendPanel.add(numCmb);

		// 价格和文本域
		returnLabel = new JLabel("归还日期:");
		returnLabel.setBorder(borderUtlis);
		returnLabel.setBounds(180, 50, 90, 30);
		returnLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		returnLabel.setForeground(new Color(0, 0, 0));
		returnLabel.setBackground(new Color(223, 183, 148));
		returnLabel.setHorizontalAlignment(SwingConstants.CENTER);
		returnLabel.setOpaque(true);

		Date date = new Date();
		SimpleDateFormat ford = new SimpleDateFormat("MMdd");
		returndate = ford.format(date);
		dateField = new JTextField(returndate);
		dateField.setForeground(new Color(255, 83, 139));
		dateField.setBounds(275, 50, 107, 30);
		dateField.setBorder(borderUtlis);
		dateField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		dateField.setHorizontalAlignment(SwingConstants.CENTER);
		dateField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				String string = dateField.getText();
				boolean ch = false;
				if (!"".equals(string)) {// 非空
					int res = returndate.compareTo(string);
					if (res > 0) {
						dateField.setText("日期错误");
						dateFlag = false;
					} else {
						dateFlag = true;
					}
				} else {
					dateField.setText(returndate);
					dateFlag = false;
				}
			}

			@Override
			// 有作用
			public void focusGained(FocusEvent e) {
				String string = dateField.getText();
				if ("日期错误".equals(string)) {
					dateField.setText("");
				}
			}
		});
		lendPanel.add(returnLabel);
		lendPanel.add(dateField);

		// 供应商信息和文本域
		lendLabel = new JLabel("借用人信息:");
		lendLabel.setBorder(borderUtlis);
		lendLabel.setBounds(5, 90, 130, 30);
		lendLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		lendLabel.setForeground(new Color(0, 0, 0));
		lendLabel.setBackground(new Color(223, 183, 148));
		lendLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lendLabel.setOpaque(true);

		nameField = new JTextField("姓名");
		nameField.setForeground(new Color(255, 83, 139));
		nameField.setBounds(145, 90, 90, 30);
		nameField.setBorder(borderUtlis);
		nameField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				String string = nameField.getText();
				boolean ch = false;
				if (!"".equals(string)) {// 非空
					// 判断输入是否是中文
					for (int i = 0; i < string.length(); i++) {
						if (!String.valueOf(string.charAt(i)).matches("[\u4e00-\u9fa5]")) {
							ch = true;
						}
					}
					if (ch) {
						nameField.setText("填汉字");
						nameFlag = false;
					} else {
						nameFlag = true;
					}
				} else {
					nameField.setText("姓名");
					nameFlag = false;
				}
			}

			@Override
			// 有作用
			public void focusGained(FocusEvent e) {
				String string = nameField.getText();
				if ("姓名".equals(string) || "填汉字".equals(string)) {
					nameField.setText("");
				}
			}
		});

		phoneField = new JTextField("联系方式(11)");
		phoneField.setForeground(new Color(255, 83, 139));
		phoneField.setBounds(239, 90, 143, 30);
		phoneField.setBorder(borderUtlis);
		phoneField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		phoneField.setHorizontalAlignment(SwingConstants.CENTER);
		phoneField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				String string = phoneField.getText();
				boolean ch = false;
				if ("".equals(string)) {
					phoneField.setText("联系方式(11)");
				} else {
					for (int i = 0; i < string.length(); i++) {
						if (string.charAt(i) < '0' || string.charAt(i) > '9') {
							ch = true;
						}
					}
					if (ch) {
						phoneField.setText("填数字");
						phoneFlag = false;
					} else if (string.length() != 11) {
						phoneField.setText("Tel应是11位");
						phoneFlag = false;
					} else {
						phoneFlag = true;
					}
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				String string = phoneField.getText();
				if ("填数字".equals(string) || "Tel应是11位".equals(string) || "联系方式(11)".equals(string)) {
					phoneField.setText("");
				}
			}
		});

		lendPanel.add(lendLabel);
		lendPanel.add(nameField);
		lendPanel.add(phoneField);

		// 购买管理员和下拉框
		managerLabel = new JLabel("借用管理员:");
		managerLabel.setBorder(borderUtlis);
		managerLabel.setBounds(5, 130, 130, 30);
		managerLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		managerLabel.setForeground(new Color(0, 0, 0));
		managerLabel.setBackground(new Color(223, 183, 148));
		managerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		managerLabel.setOpaque(true);

		managerCmb = new JComboBox<String>();
		managerCmb.setBounds(145, 130, 170, 30);
		managerCmb.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		managerCmb.setBackground(new Color(135, 206, 250));
		managerCmb.setForeground(new Color(0, 0, 0));
		managerCmb.setMaximumRowCount(3);
		// emCmb.setOpaque(false);
		managerCmb.addItem("-请选择管理员-");

		UserDao userDao = new UserDao();
		userList = new ArrayList<User>();
		userList = userDao.queryUsers();
		int size = userList.size();
		for (int i = 0; i < size; i++) {
			User users = userList.get(i);
			managerCmb.addItem(users.getUserName());
		}

		lendPanel.add(managerLabel);
		lendPanel.add(managerCmb);

		// 四个按钮
		lendButton = new JButton("借用");
		lendButton.setBounds(7, 170, 91, 30);
		lendButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		lendButton.setForeground(new Color(255, 255, 255));
		lendButton.setBackground(new Color(135, 206, 250));
		lendButton.setToolTipText("点击即可借用");
		lendButton.addActionListener(this);

		returnButton = new JButton("归还");
		returnButton.setBounds(101, 170, 91, 30);
		returnButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		returnButton.setForeground(new Color(255, 255, 255));
		returnButton.setBackground(new Color(135, 206, 250));
		returnButton.setToolTipText("点击即可归还");
		returnButton.addActionListener(this);

		backButton = new JButton("退出系统");
		backButton.setBounds(195, 170, 185, 30);
		backButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		backButton.setForeground(new Color(255, 255, 255));
		backButton.setBackground(new Color(135, 206, 250));
		backButton.setToolTipText("点击返回上一界面");
		backButton.addActionListener(this);

		refreshButton = new JButton("刷新");
		refreshButton.setBounds(280, 10, 85, 30);
		refreshButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		refreshButton.setForeground(new Color(0, 0, 0));
		refreshButton.setBackground(new Color(255, 80, 80));
		refreshButton.setToolTipText("点击刷新数据");
		refreshButton.addActionListener(this);

		lendPanel.add(lendButton);
		lendPanel.add(returnButton);
		lendPanel.add(backButton);
		lendPanel.add(refreshButton);

		showdata();
		setListNum();
		ClockPane();

		container.add(listPanel);
		container.add(numPanel);
		container.add(lendPanel);

		jFrame.setResizable(false);
		jFrame.setVisible(true);
		jFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				new SManagerUI().setReOpenFlag(true);
				new GManagerUI().setReOpenFlag(true);
				close();
				// System.exit(0);
			}
		});
	}

	public void emCmb() {
		// 设备名称下拉框内容
		emCmb = new JComboBox<String>();
		emCmb.setBounds(120, 10, 150, 30);
		emCmb.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		emCmb.setBackground(new Color(135, 206, 250));
		emCmb.setForeground(new Color(0, 0, 0));
		emCmb.setMaximumRowCount(5);
		// emCmb.setOpaque(false);
		emCmb.addItem("-请选择设备-");
		lendPanel.add(emCmb);

		buyDao = new BuyDao();
		emList = BuyDao.queryList();

		int size = emList.size();

		for (int i = 0; i < size; i++) {
			BuyEm buyEm = emList.get(i);
			emCmb.addItem(buyEm.getEname());
		}
	}

	public void reset() {
		dateField.setText(returndate);
		nameField.setText("姓名");
		phoneField.setText("联系方式(11)");
	}

	public String[][] setData() {

		emList = new ArrayList<BuyEm>();

		buyDao = new BuyDao();
		emList = BuyDao.queryStoreList();

		int size = emList.size();

		for (int i = 0; i < size; i++) {
			BuyEm buyEm = emList.get(i);
			data[i][0] = buyEm.getEno() + "";
			data[i][1] = buyEm.getEname();
			data[i][2] = buyEm.getEcount() + "";
		}

		return data;
	}

	public void showdata() {

		titleLabel = new JLabel("《设备在库列表》");
		titleLabel.setBackground(new Color(68, 114, 196));
		titleLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		titleLabel.setForeground(new Color(255, 255, 255));
		titleLabel.setBounds(0, 0, 400, 30);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setOpaque(true);
		listPanel.add(titleLabel);

		String[] columns = { "设备号", "设备名称", "在库数量" };

		JTable table;
		JScrollPane scrollPane;
		DefaultTableModel tableModel;
		tableModel = new DefaultTableModel(setData(), columns);

		table = new JTable(tableModel);
		table.setFont(new Font("微软雅黑", Font.BOLD, 13));
		table.setForeground(new Color(255, 83, 139));

		// 数据居中
		DefaultTableCellRenderer dc = new DefaultTableCellRenderer();
		dc.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, dc);

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 30, 390, 160);

		listPanel.add(scrollPane);
	}

	public void setListNum() {// 编号格式(年月日+第n单,n为3位数)
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateStr = sdf.format(date);
		new LendDao();
		int size = LendDao.queryLendSize() + 1;
		String num = "";
		if (size >= 0 && size < 10) {// 个位数,补齐00
			num = "00" + size;
		} else if (size >= 10 && size < 100) {// 两位数,补齐0
			num = "0" + size;
		} else {
			num = "" + size;
		}
//		System.out.println(dateStr+num);
		Listno = dateStr + num;
		Year = dateStr.substring(0, 4);
		String text = dateStr + num;

		numLabel = new JLabel("本借用编号:" + text);
		numLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		numLabel.setForeground(new Color(255, 255, 255));
		numLabel.setBounds(0, 5, 400, 30);
		numLabel.setHorizontalAlignment(SwingConstants.CENTER);

		numPanel.add(numLabel);
	}

	public void ClockPane() {
		clockLabel = new JLabel();
		clockLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		clockLabel.setForeground(new Color(255, 255, 255));
		clockLabel.setBounds(0, 34, 400, 30);
		clockLabel.setHorizontalAlignment(SwingConstants.CENTER);

		clockLabel.setText("借用时间:" + DateFormat.getDateTimeInstance().format(new Date()));
		numPanel.add(clockLabel);
		Timer timer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clockLabel.setText("借用时间:" + DateFormat.getDateTimeInstance().format(new Date()));
			}
		});
		timer.setRepeats(true);
		timer.setCoalesce(true);
		timer.setInitialDelay(0);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String Ename = emCmb.getSelectedItem().toString();
		int Ecount = Integer.parseInt(numCmb.getSelectedItem().toString());
		String LendName = nameField.getText();
		String ManagerName = managerCmb.getSelectedItem().toString();

		if (e.getSource() == refreshButton) {
			listPanel.removeAll();
			showdata();
			listPanel.updateUI();
			numPanel.removeAll();
			setListNum();
			ClockPane();
			numPanel.updateUI();
		}

		if (e.getSource() == lendButton) {

			listPanel.removeAll();
			showdata();
			listPanel.updateUI();
			numPanel.removeAll();
			setListNum();
			ClockPane();
			numPanel.updateUI();
			new LendDao();
			if (Ename.equals("-请选择设备-")) {
				MsgUtil.Errmsg("请选择借用设备名称");
			} else if(Ecount > LendDao.queryLendSize(Ename)) {
				MsgUtil.Errmsg("库存不足！");
			}else if (!dateFlag || !nameFlag || !phoneFlag) {
				MsgUtil.Errmsg("请核实借用日期和借用人信息");
			} else if (ManagerName.equals("-请选择管理员-")) {
				MsgUtil.Errmsg("请选择设备管理员");
			} else if (dateFlag && nameFlag && phoneFlag) {
				String month = dateField.getText().substring(0, 2);
				String day = dateField.getText().substring(2);
				String Returndate = Year+"-"+month+"-"+day;
				String LendPhone = phoneField.getText();
				LendEm lendEm = new LendEm(Listno,Ename,Ecount,LendName,LendPhone,Returndate,ManagerName);
				new ConfirmLendUtil(lendEm).show();
			}
			reset();

		}
		
		if(e.getSource() == returnButton) {
			new ConfirmBackUtil().show();
		}
		
		if (e.getSource() == backButton) {
			new SManagerUI().setReOpenFlag(true);
			new GManagerUI().setReOpenFlag(true);
			close();
		}

	}

	public void close() {
		jFrame.dispose();
	}

	public static void main(String[] args) {
		new LendUI().show();

	}
}
