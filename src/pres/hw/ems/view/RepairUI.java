package pres.hw.ems.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.TextArea;
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
import pres.hw.ems.dao.RepairDao;
import pres.hw.ems.dao.UserDao;
import pres.hw.ems.domain.BuyEm;
import pres.hw.ems.domain.RepairEm;
import pres.hw.ems.domain.User;
import pres.hw.ems.util.BackRepairUtil;
import pres.hw.ems.util.ConfirmRepairUI;
import pres.hw.ems.util.MsgUtil;
import pres.hw.ems.util.TextBorderUtlis;

public class RepairUI implements ActionListener{

	private JFrame jFrame;
	private Container container;
	private JPanel listPanel, numPanel, repairPanel;
	private JLabel titleLabel, numLabel, clockLabel, emLabel, managerLabel;
	private JComboBox<String> emCmb, numCmb, managerCmb;
	private JButton repairButton, backButton, refreshButton, returnButton;
	private TextBorderUtlis borderUtlis;

	private String Listno;
	private String[][] data = new String[30][3];
	private List<BuyEm> emList;
	private TextArea text;
	private List<User> userList;
	private static BuyDao buyDao;
	
	public RepairUI() {
		// TODO Auto-generated constructor stub
	}
	
	public void show() {
		jFrame = new JFrame("设备报修");
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

		repairPanel = new JPanel();
		repairPanel.setBackground(Color.yellow);
		repairPanel.setBounds(0, 260, 400, 203);
		repairPanel.setLayout(null);
		repairPanel.setOpaque(false);
		
		// 边框美化
		borderUtlis = new TextBorderUtlis(new Color(112, 48, 160), 1, true);

		// 设备名称
		emLabel = new JLabel("设备信息/数量:");
		emLabel.setBorder(borderUtlis);
		emLabel.setBounds(5, 5, 150, 30);
		emLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		emLabel.setForeground(new Color(0, 0, 0));
		emLabel.setBackground(new Color(223, 183, 148));
		emLabel.setHorizontalAlignment(SwingConstants.CENTER);
		emLabel.setOpaque(true);
		repairPanel.add(emLabel);
		emCmb();
		repairPanel.add(emCmb);
		// 购买数量下拉框
		numCmb = new JComboBox<String>();
		for (int i = 1; i <= 10; i++) {
			numCmb.addItem(i + "");
		}
		numCmb.setBounds(315, 5, 65, 30);
		numCmb.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		numCmb.setBackground(new Color(135, 206, 250));
		numCmb.setForeground(new Color(0, 0, 0));
		numCmb.setMaximumRowCount(4);
		repairPanel.add(numCmb);
		
		managerLabel = new JLabel("报修管理员:");
		managerLabel.setBorder(borderUtlis);
		managerLabel.setBounds(5, 40, 130, 30);
		managerLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		managerLabel.setForeground(new Color(0, 0, 0));
		managerLabel.setBackground(new Color(223, 183, 148));
		managerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		managerLabel.setOpaque(true);
		
		managerCmb = new JComboBox<String>();
		managerCmb.setBounds(140, 40, 170, 30);
		managerCmb.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		managerCmb.setBackground(new Color(135, 206, 250));
		managerCmb.setForeground(new Color(0, 0, 0));
		managerCmb.setMaximumRowCount(3);
		managerCmb.addItem("-请选择管理员-");
		
		UserDao userDao = new UserDao();
		userList = new ArrayList<User>();
		userList = userDao.queryUsers();
		int size = userList.size();
		for (int i = 0; i < size; i++) {
			User users = userList.get(i);
			managerCmb.addItem(users.getUserName());
		}
		repairPanel.add(managerLabel);
		repairPanel.add(managerCmb);
		
		text = new TextArea("请填写报修原因");
		text.setBounds(5, 75, 375, 90);
		text.setFont(new Font("楷体", Font.BOLD, 15));
		text.setForeground(new Color(255, 80, 80));
		text.setBackground(new Color(209, 242, 246));
		text.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				String str = text.getText();
				if("".equals(str)) {
					text.setText("请填写报修原因");
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				String str = text.getText();
				if(str.equals("请填写报修原因")) {
					text.setText("");
				}
			}
		});
		repairPanel.add(text);
		
		// 四个按钮
		repairButton = new JButton("报修");
		repairButton.setBounds(7, 170, 91, 30);
		repairButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		repairButton.setForeground(new Color(255, 255, 255));
		repairButton.setBackground(new Color(135, 206, 250));
		repairButton.setToolTipText("点击即可报修");
		repairButton.addActionListener(this);

		returnButton = new JButton("归库");
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
		refreshButton.setBounds(315, 40, 65, 30);
		refreshButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		refreshButton.setForeground(new Color(0, 0, 0));
		refreshButton.setBackground(new Color(255, 80, 80));
		refreshButton.setToolTipText("点击刷新数据");
		refreshButton.addActionListener(this);
		
		repairPanel.add(returnButton);
		repairPanel.add(backButton);
		repairPanel.add(repairButton);
		repairPanel.add(refreshButton);
		
		showdata();
		setListNum();
		ClockPane();
		
		container.add(listPanel);
		container.add(numPanel);
		container.add(repairPanel);

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
	
	public void emCmb() {
		// 设备名称下拉框内容
		emCmb = new JComboBox<String>();
		emCmb.setBounds(160, 5, 150, 30);
		emCmb.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		emCmb.setBackground(new Color(135, 206, 250));
		emCmb.setForeground(new Color(0, 0, 0));
		emCmb.setMaximumRowCount(5);
		// emCmb.setOpaque(false);
		emCmb.addItem("-请选择设备-");

		buyDao = new BuyDao();
		emList = BuyDao.queryList();

		int size = emList.size();

		for (int i = 0; i < size; i++) {
			BuyEm buyEm = emList.get(i);
			emCmb.addItem(buyEm.getEname());
		}
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
		new RepairDao();
		int size = RepairDao.queryRepairSize() + 1;
		String num = "";
		if (size >= 0 && size < 10) {// 个位数,补齐00
			num = "00" + size;
		} else if (size >= 10 && size < 100) {// 两位数,补齐0
			num = "0" + size;
		} else {
			num = "" + size;
		}
		Listno = dateStr + num;
		String text = dateStr + num;

		numLabel = new JLabel("本报修编号:" + text);
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

		clockLabel.setText("报修时间:" + DateFormat.getDateTimeInstance().format(new Date()));
		numPanel.add(clockLabel);
		Timer timer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clockLabel.setText("报修时间:" + DateFormat.getDateTimeInstance().format(new Date()));
			}
		});
		timer.setRepeats(true);
		timer.setCoalesce(true);
		timer.setInitialDelay(0);
		timer.start();
	}
	
	public void reset() {
		//emCmb();
		//text.setText("请填写报修原因");
	}
	
	public void close() {
		jFrame.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String Ename = emCmb.getSelectedItem().toString();
		int Rcount = Integer.parseInt(numCmb.getSelectedItem().toString());
		String Rreason = text.getText();
		String username = managerCmb.getSelectedItem().toString();
		
		if (e.getSource() == refreshButton) {
			listPanel.removeAll();
			showdata();
			listPanel.updateUI();
			numPanel.removeAll();
			setListNum();
			ClockPane();
			numPanel.updateUI();
		}
		
		if(e.getSource() == repairButton) {
			new RepairDao();
			if(Ename.equals("-请选择设备-")) {
				MsgUtil.Errmsg("请选择报修设备名称");
			}else if(Rcount > RepairDao.queryLendSize(Ename)) {
				MsgUtil.Errmsg("请检查报修数量！");
			}else if(username.equals("-请选择管理员-")) {
				MsgUtil.Errmsg("请选择设备管理员");
			}else if(Rreason.equals("请填写报修原因")) {
				MsgUtil.Errmsg("请填写报修设备原因");
			}else {
				RepairEm repairEm = new RepairEm(Listno,Ename,Rcount,Rreason,username);
				new ConfirmRepairUI(repairEm).show();
			}
		}
		
		if(e.getSource() == returnButton) {
			new BackRepairUtil().show();
		}
		
		if (e.getSource() == backButton) {
			new SManagerUI().setReOpenFlag(true);
			new GManagerUI().setReOpenFlag(true);
			close();
		}
	}

//	public static void main(String[] args) {
//		new RepairUI().show();
//	}
}
