package DB.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
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
import DB.model.FoodList;
import DB.util.DbUtil;
import DB.util.StringUtil;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class menuFixInter extends JInternalFrame {
	private JTextField fixName;
	private JTextField fixPrice;
	private DbUtil dbUtil = new DbUtil();
	private FoodListDao foodListDao = new FoodListDao();
	private JComboBox FoodListSelect = new JComboBox();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menuFixInter frame = new menuFixInter();
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
	public menuFixInter() {
		setFrameIcon(new ImageIcon(menuFixInter.class.getResource("/images/\u4FEE\u6539_\u586B\u5145.png")));
		setTitle("\u83DC\u5355\u4FEE\u6539");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 450, 300);
		
		JDesktopPane desktopPane = new JDesktopPane();
		
		JLabel lblNewLabel = new JLabel("\u9700\u8981\u4FEE\u6539\u7684\u83DC\u54C1\u540D");
		lblNewLabel.setFont(new Font("黑体", Font.BOLD, 20));
		
		JLabel lblNewLabel_1 = new JLabel("\u4FEE\u6539\u540E\u7684\u83DC\u54C1\u540D");
		lblNewLabel_1.setFont(new Font("黑体", Font.BOLD, 20));
		
		fixName = new JTextField();
		fixName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u4FEE\u6539\u540E\u7684\u83DC\u54C1\u4EF7\u683C");
		lblNewLabel_2.setFont(new Font("黑体", Font.BOLD, 20));
		
		fixPrice = new JTextField();
		fixPrice.setColumns(10);
		
		JButton fixBut = new JButton("\u4FEE\u6539");
		fixBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FixActionPerformed(e);
			}
		});
		fixBut.setIcon(new ImageIcon(menuFixInter.class.getResource("/images/\u4FEE\u6539_\u586B\u5145.png")));
		fixBut.setFont(new Font("黑体", Font.BOLD, 23));
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_2)
							.addGap(18)
							.addComponent(fixPrice, 148, 148, 148))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addGap(32)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(FoodListSelect, 0, 148, Short.MAX_VALUE)
								.addComponent(fixName, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(82))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(171)
					.addComponent(fixBut)
					.addContainerGap(168, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(FoodListSelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(fixName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(fixPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addComponent(fixBut)
					.addContainerGap(35, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		//菜单下拉框初始化
		this.fillFoodListSelect();
	}
	
	
	/**
	 * 修改菜单按钮实现
	 * @param e
	 */
	private void FixActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String NewFoodName = this.fixName.getText();
		String NewFoodPrice = this.fixPrice.getText();
		if(StringUtil.isEmpty(NewFoodName)) {
			JOptionPane.showMessageDialog(null,"菜品新名不能为空");
			return;
		}
		if(StringUtil.isEmpty(NewFoodPrice)) {
			JOptionPane.showMessageDialog(null,"菜品新价格不能为空");
			return;
		}
		FoodList foodlist =(FoodList)FoodListSelect.getSelectedItem();
		foodlist.setFoodName(NewFoodName);
		foodlist.setPrice(Integer.parseInt(NewFoodPrice));
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int updatenum = foodListDao.update(con,foodlist);
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
	 * 菜单下拉框实现
	 */
	private void fillFoodListSelect() {
		Connection con  = null;
		FoodList foodList = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = foodListDao.list(con,new FoodList());
			while(rs.next()) {
				foodList = new FoodList();
				foodList.setFoodName(rs.getString("FoodName"));
				foodList.setPrice(rs.getInt("Price"));
				foodList.setId(rs.getInt("id"));
				this.FoodListSelect.addItem(foodList);
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
