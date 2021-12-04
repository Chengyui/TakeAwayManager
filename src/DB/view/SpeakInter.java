package DB.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import DB.Dao.FoodListDao;
import DB.Dao.FoodOrderDao;
import DB.model.FoodList;
import DB.model.FoodOrder;
import DB.model.User;
import DB.util.DbUtil;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SpeakInter extends JInternalFrame {
	private JTextField AccountText;
	private JTextArea SpeakText = new JTextArea();
	private User user = null;
	private String food;
	private DbUtil dbUtil = new DbUtil();
	private FoodOrderDao foodOrderDao = new FoodOrderDao();
	private JComboBox FoodOrderSelect = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpeakInter frame = new SpeakInter(new User());
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
	public SpeakInter(User user) {
		this.user = user;
		
		setTitle("\u8BC4\u4EF7");
		setFrameIcon(new ImageIcon(SpeakInter.class.getResource("/images/classrepair.png")));
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 580, 424);
		
		JLabel lblNewLabel = new JLabel("\u987E\u5BA2\u8D26\u6237");
		lblNewLabel.setFont(new Font("黑体", Font.BOLD, 20));
		
		AccountText = new JTextField();
		AccountText.setEditable(false);
		AccountText.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u83DC\u54C1\u540D\u79F0");
		lblNewLabel_1.setFont(new Font("黑体", Font.BOLD, 20));
		
		JLabel lblNewLabel_2 = new JLabel("\u8BC4\u4EF7");
		lblNewLabel_2.setFont(new Font("黑体", Font.BOLD, 20));
		
		JButton btnNewButton = new JButton("\u63D0\u4EA4");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddEvaluatePerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(SpeakInter.class.getResource("/images/\u5B8C\u6210 (1).png")));
		btnNewButton.setFont(new Font("黑体", Font.BOLD, 20));
		
		JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResetPerformed(e);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(SpeakInter.class.getResource("/images/\u518D\u6B21.png")));
		btnNewButton_1.setFont(new Font("黑体", Font.BOLD, 20));
		
		//JTextArea SpeakText = new JTextArea();
		SpeakText.setLineWrap(true);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(AccountText, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(SpeakText, GroupLayout.PREFERRED_SIZE, 240, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnNewButton)
									.addGap(22)
									.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
								.addComponent(FoodOrderSelect, 0, 240, Short.MAX_VALUE))))
					.addGap(192))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(49)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(AccountText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addComponent(FoodOrderSelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(37)
							.addComponent(SpeakText, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton_1)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(86)
							.addComponent(lblNewLabel_2)))
					.addContainerGap(34, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		this.fillFoodOrderSelect();

	}
	
	/**
	 * 添加评论
	 * @param e
	 */
	private void AddEvaluatePerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String evaluation = SpeakText.getText();
		FoodOrder foodOrder = new FoodOrder();
		foodOrder.setFoodName((String)this.FoodOrderSelect.getSelectedItem());
		food = foodOrder.getFoodName();
		String username = user.getUserName();
		FoodOrderDao foodorderdao = new FoodOrderDao();
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int updatenum = foodorderdao.update(con, username, food, evaluation);
			if(updatenum == 1) {
				JOptionPane.showMessageDialog(null,"评论成功");
			}
			else {
				JOptionPane.showMessageDialog(null,"评论失败");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * 重置按钮，重新评论
	 * @param e
	 */
	protected void ResetPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.SpeakText.setText("");
	}

	/**
	 * 菜单下拉框实现
	 * 以及顾客帐户显示
	 */
	private void fillFoodOrderSelect() {
		this.AccountText.setText(user.getUserName());
		//System.out.println(user.getUserName());
		Connection con  = null;
		FoodOrder foodOrder = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = foodOrderDao.list(con,new FoodOrder());
			while(rs.next()) {
				if(rs.getString("userName").equals(user.getUserName()) == false) {
					//System.out.println(rs.getString("userName"));
					continue;
				}
				foodOrder = new FoodOrder();
				foodOrder.setFoodName(rs.getString("foodName"));
				foodOrder.setPrice(rs.getInt("Price"));
			    foodOrder.setId(rs.getInt("id"));
				this.FoodOrderSelect.addItem(foodOrder.getFoodName());
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
}
