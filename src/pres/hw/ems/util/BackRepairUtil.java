package pres.hw.ems.util;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import pres.hw.ems.dao.LendDao;
import pres.hw.ems.dao.RepairDao;
import pres.hw.ems.domain.LendEm;
import pres.hw.ems.domain.RepairEm;

public class BackRepairUtil implements ActionListener{
	
	private String title = "设备入库";
	private JFrame jFrame;
	private Container container;
	private JPanel tablePanel,buttonPanel;
	private TextArea text;
	private JComboBox<String> emCmb;
	private String[][] data = new String[30][7];
	private List<RepairEm> repairList;
	private TextBorderUtlis borderUtlis;
	private JButton yesButton,noButton;
	
	private static RepairDao repairDao;
	
	public BackRepairUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public int setData(){
		repairList = new ArrayList<RepairEm>();
		repairDao = new RepairDao();
		repairList = RepairDao.queryRepairList();
		
		int size = repairList.size();
		
		for(int i=0;i<size;i++) {
			RepairEm repairEm = repairList.get(i);
			data[i][0] = repairEm.getRepairno();
			data[i][1] = repairEm.getRepairdate();
			data[i][2] = repairEm.getEno() + "";
			data[i][3] = repairEm.getEname();
			data[i][4] = repairEm.getRcount() + "";
			data[i][5] = repairEm.getRreason();
			data[i][6] = repairEm.getUsername();
		}
		return size;
	}
	public void show() {
		jFrame = new JFrame(title);
		jFrame.setBounds(510, 500, 540, 250);
		container = jFrame.getContentPane();
		container.setBackground(new Color(209, 242, 246));
		container.setLayout(null);
		
		//2个JPanel
		tablePanel = new JPanel();
		tablePanel.setBackground(new Color(255, 80, 80));
		tablePanel.setBounds(0, 0, 526, 165);
		tablePanel.setOpaque(true);
		tablePanel.setLayout(null);
		
		buttonPanel = new JPanel(); 
		buttonPanel.setBackground(new Color(255, 180, 80));
		buttonPanel.setBounds(0, 165, 526, 48);
		buttonPanel.setOpaque(true);
		buttonPanel.setLayout(null);
		
		int size = setData();
		
		text = new TextArea();
		text.setBounds(0, 0, 526, 165);
		text.setFont(new Font("楷体", Font.BOLD, 15));
		text.setForeground(new Color(255, 255, 255));
		text.setBackground(new Color(255, 80, 80));
		
		String str = "报修编号\t\t报修时间\t\t\t设备号\t设备名称\t报修数量\t报修原因\t管理员\n";
		String Data = "";
		for(int i=0;i<size;i++) {
			Data = Data + str;
			for(int j=0;j<7;j++) {
				if(j == 2) {
					Data = Data + data[i][j] + "\t\t";
				}else if(j == 3 && data[i][j].length()<3) {
					Data = Data + data[i][j] + "\t\t";
				}else if(j == 4 ) {
					Data = Data + data[i][j] + "\t\t";
				}else if(j == 5) {
					Data = Data + data[i][j] + "\t";
				}else {
					Data = Data + data[i][j] + '\t';
				}
			}
			Data = Data + '\n';
		}
		text.setText(Data);
		tablePanel.add(text);
		
		// 边框美化
		borderUtlis = new TextBorderUtlis(new Color(112, 48, 160), 1, true);
		
		// 设备名称下拉框内容
		emCmb = new JComboBox<String>();
		emCmb.setBounds(7, 10, 193, 30);
		emCmb.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		emCmb.setBackground(new Color(135, 206, 250));
		emCmb.setForeground(new Color(0, 0, 0));
		emCmb.setMaximumRowCount(5);
		// emCmb.setOpaque(false);
		emCmb.addItem("-请选择报修编号-");
		for(int i = 0 ;i<size;i++) {
			emCmb.addItem(data[i][0]);
		}
		
		buttonPanel.add(emCmb);
		
		//2个JButton
		yesButton = new JButton("确认入库");
		yesButton.setBounds(210, 10, 150, 30);
		yesButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		yesButton.setForeground(new Color(0, 0, 0));
		yesButton.setBackground(new Color(64, 200, 164));
		yesButton.addActionListener(this);
		
		noButton = new JButton("取消入库");
		noButton.setBounds(370, 10, 150, 30);
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
		String Repairno = emCmb.getSelectedItem().toString();
		if(e.getSource() == yesButton) {
			if (Repairno.equals("-请选择报修编号-")) {
				MsgUtil.Errmsg("请选择报修编号");
			}else {
				MsgUtil.updateid(RepairDao.delete(Repairno), 1);
				close();
			}
		}
		if(e.getSource() == noButton) {
			close();
		}
	}
	public void close(){
		jFrame.dispose();
	}
	
//	public static void main(String[] args) {
//		new BackRepairUtil().show();
//	}

}
