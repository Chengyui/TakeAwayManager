package DB.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DB.Dao.UserDao;
import DB.model.User;
import DB.util.DbUtil;
import DB.util.StringUtil;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class registerFrm extends JFrame {

	private JPanel contentPane;
	private JTextField account;
	private JPasswordField password;
	private JPasswordField confirm_password;
	private DbUtil dbUtil = new DbUtil();
	private UserDao userDao = new UserDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registerFrm frame = new registerFrm();
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
	public registerFrm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(registerFrm.class.getResource("/images/\u7F8E\u56E2\u5916\u5356.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u65B0\u8D26\u53F7:");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 20));
		
		account = new JTextField();
		account.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801:");
		lblNewLabel_1.setFont(new Font("黑体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_2 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		lblNewLabel_2.setFont(new Font("黑体", Font.PLAIN, 20));
		
		password = new JPasswordField();
		
		confirm_password = new JPasswordField();
		
		JButton btnNewButton = new JButton("\u786E\u8BA4\u6CE8\u518C");
		btnNewButton.setIcon(new ImageIcon(registerFrm.class.getResource("/images/login.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 20));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(105, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(lblNewLabel)
									.addComponent(lblNewLabel_1))
								.addComponent(lblNewLabel_2))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(confirm_password)
								.addComponent(password)
								.addComponent(account, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
							.addContainerGap(29, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton)
							.addGap(112))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(account, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(confirm_password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addGap(32)
					.addComponent(btnNewButton)
					.addContainerGap(41, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	
	/**
	 * 注册确认按钮
	 * @param arg0
	 */
	private void confirmActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String account = this.account.getText();
		String password =new String( this.password.getPassword());
		String confirm_password = new String(this.confirm_password.getPassword());
		if(StringUtil.isEmpty(account)){
			JOptionPane.showMessageDialog(null, "用户名不能为空");
			return;
		}
		if(StringUtil.isEmpty(password)){
			JOptionPane.showMessageDialog(null, "密码不能为空");
			return;
		}
		if(StringUtil.isEmpty(confirm_password)){
			JOptionPane.showMessageDialog(null, "确认密码不能为空");
			return;
		}
		if(!password.equals(confirm_password)) {
			JOptionPane.showMessageDialog(null, "确认密码必须与密码相同");
			return;
		}
		User user = new User(account,password,3);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int addnum = userDao.add(con,user);
			if(addnum==1) {
				JOptionPane.showMessageDialog(null, "注册成功");
				dispose();
				new LogOnFrm().setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(null, "注册失败");
				return;
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
