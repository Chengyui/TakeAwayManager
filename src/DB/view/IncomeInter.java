package DB.view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DB.Dao.FoodOrderDao;
import DB.Dao.incomePerDayDao;
import DB.model.FoodList;
import DB.model.FoodOrder;
import DB.model.incomePerDay;
import DB.util.DbUtil;
import DB.util.StringUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class IncomeInter extends JInternalFrame {
	private JTable IncomeTabel;

	/**
	 * Launch the application.
	 */
	private DbUtil dbUtil = new DbUtil();
	private FoodOrderDao foodOrderDao = new FoodOrderDao();
	private JComboBox orderlist = new JComboBox();
	private JTable FoodOrderTable;
	private JTable sumIncomeTable;
	private JTextField date;
	private int incomeSum = 0;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IncomeInter frame = new IncomeInter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IncomeInter() {
		setFrameIcon(new ImageIcon(IncomeInter.class.getResource("/images/\u94B1\u888B.png")));
		setTitle("\u65E5\u6536\u5165");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 612, 540);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("\u987E\u5BA2\u8D26\u6237");
		lblNewLabel.setFont(new Font("黑体", Font.BOLD, 20));
		
		JButton btnNewButton = new JButton("\u786E\u5B9A");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userNameSearchPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("黑体", Font.BOLD, 22));
		
		JButton btnNewButton_1 = new JButton("\u67E5\u770B\u4ECA\u65E5\u603B\u6536\u5165");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sumIncomePerformed(e);
			}
		});
		btnNewButton_1.setFont(new Font("黑体", Font.BOLD, 22));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JLabel lblNewLabel_1 = new JLabel("\u65E5\u671F");
		lblNewLabel_1.setFont(new Font("黑体", Font.BOLD, 20));
		
		date = new JTextField();
		date.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("\u63D0\u4EA4");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddincomePerformed(e);
			}
		});
		btnNewButton_2.setFont(new Font("黑体", Font.BOLD, 20));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(96)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
								.addComponent(btnNewButton_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(37)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(date, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addGap(20)
									.addComponent(orderlist, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
									.addGap(38)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(150, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel, 0, 0, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(orderlist, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton)))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_2)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(date, Alignment.LEADING)
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addGap(4)
								.addComponent(lblNewLabel_1))))
					.addGap(87))
		);
		
		sumIncomeTable = new JTable();
		sumIncomeTable.setFont(new Font("黑体", Font.BOLD, 15));
		sumIncomeTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u603B\u6536\u5165"
			}
		));
		scrollPane_1.setViewportView(sumIncomeTable);
		
		IncomeTabel = new JTable();
		IncomeTabel.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7528\u6237", "\u6536\u5165"
			}
		));
		scrollPane.setViewportView(IncomeTabel);
		getContentPane().setLayout(groupLayout);
		
		this.fillOrderList();
		
		this.fillTable(new FoodOrder());

	}
	
	private void AddincomePerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String Date = this.date.getText();
		if(StringUtil.isEmpty(Date)) {
			JOptionPane.showMessageDialog(null,"日期不能为空");
			return;
		}
		incomePerDay incomeperday = new incomePerDay();
		incomeperday.setDate(Date);
		incomeperday.setIncome(incomeSum);
		incomePerDayDao incomeperdao = new incomePerDayDao();
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int updatenum = incomeperdao.add(con, incomeperday);
			if(updatenum == 1) {
				JOptionPane.showMessageDialog(null,"修改成功");
			}
			else {
				JOptionPane.showMessageDialog(null,"修改失败");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
	}
}

	/**
	 * 查询总收入
	 * @param e
	 */
	private void sumIncomePerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.fillTable_2(new FoodOrder());
	}

	/**
	 * 查询收入
	 * @param e
	 */
	private void userNameSearchPerformed(ActionEvent e) {
		FoodOrder foodOrder = new FoodOrder();
		foodOrder = (FoodOrder)this.orderlist.getSelectedItem();
		this.fillTable(foodOrder);
	}

	/**
	 * 初始化表格
	 */
	private void fillTable(FoodOrder foodorder) {	
		DefaultTableModel dtm=(DefaultTableModel)IncomeTabel.getModel();
		dtm.setRowCount(0);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			ResultSet rs=foodOrderDao.list_1(con,foodorder );
			while(rs.next()){
				Vector v=new Vector();
				v.add(rs.getString("UserName"));
				//v.add(rs.getString("FoodName"));
				v.add(rs.getInt("Price"));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				DbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 订单下拉框实现
	 */
	
	private void fillOrderList() {
		Connection con  = null;
		FoodOrder foodorder = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = foodOrderDao.list_1(con,new FoodOrder());
			String prestring = new String();
			while(rs.next()) {
				// 对foodorder相同用户部分进行去重操作
				foodorder = new FoodOrder();
				//foodorder.setFoodName(rs.getString("foodName"));
				foodorder.setPrice(rs.getInt("Price"));
				//foodorder.setId(rs.getInt("id"));
				foodorder.setUserName(rs.getString("userName"));
				
				if(prestring.equals(foodorder.getUserName())==false) {
					this.orderlist.addItem(foodorder);
					prestring = new String();
					prestring = foodorder.getUserName();
				}		
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				DbUtil.closeCon(con);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void fillTable_2(FoodOrder foodorder) {	
		DefaultTableModel dtm=(DefaultTableModel)sumIncomeTable.getModel();
		dtm.setRowCount(0);
		Connection con=null;
		Vector v=new Vector();
		incomeSum = 0;
		try {
			con=dbUtil.getCon();
			ResultSet rs=foodOrderDao.list_2(con,foodorder );
			while(rs.next()){
				//Vector v=new Vector();
				//v.add(rs.getString("UserName"));
				//v.add(rs.getString("FoodName"));
				incomeSum += rs.getInt("Price");
				//dtm.addRow(v);
			}
			v.add(incomeSum);
			dtm.addRow(v);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				DbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
