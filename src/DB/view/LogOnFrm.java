package DB.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import DB.model.User;
import DB.util.DbUtil;
import DB.util.StringUtil;
import DB.Dao.UserDao;

import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;

public class LogOnFrm extends JFrame {

	private JPanel contentPane;
	private JTextField userName;
	private JPasswordField passWord;
	private DbUtil dbUtil = new DbUtil();
	private UserDao userDao = new UserDao();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogOnFrm frame = new LogOnFrm();
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
	public LogOnFrm() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LogOnFrm.class.getResource("/images/\u7F8E\u56E2\u5916\u5356.png")));
		setTitle("\u5916\u5356\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u5916\u5356\u7BA1\u7406\u7CFB\u7EDF");
		lblNewLabel.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/\u7F8E\u56E2\u5916\u5356.png")));
		lblNewLabel.setFont(new Font("����", Font.BOLD, 25));
		
		JLabel lblNewLabel_1 = new JLabel("\u8D26\u53F7\uFF1A");
		lblNewLabel_1.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/user.png")));
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 20));
		
		userName = new JTextField();
		userName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel_2.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/password.png")));
		lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 20));
		
		passWord = new JPasswordField();
		
		JLabel lblNewLabel_3 = new JLabel("");
		
		JButton btnNewButton = new JButton("\u767B\u9646");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/login.png")));
		btnNewButton.setFont(new Font("����", Font.PLAIN, 20));
		
		JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
		btnNewButton_1.setFont(new Font("����", Font.PLAIN, 20));
		btnNewButton_1.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/_\u91CD\u7F6E.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResetActionPerformed(e);
			}
		});
		
		JButton register_button = new JButton("\u6CE8\u518C");
		register_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new registerFrm().setVisible(true);
				//contentPane.add(reg);
			}
		});
		register_button.setFont(new Font("����", Font.PLAIN, 20));
		register_button.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/\u6CE8\u518C (2).png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(295, Short.MAX_VALUE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
					.addGap(237))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(299, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel_2)
										.addComponent(lblNewLabel_1))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(passWord)
										.addComponent(userName, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)))
								.addComponent(lblNewLabel_3))
							.addGap(162))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(register_button)
							.addGap(48)
							.addComponent(btnNewButton)
							.addGap(45)
							.addComponent(btnNewButton_1)
							.addGap(71))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(34)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(userName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(passWord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_3)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton)
							.addComponent(btnNewButton_1)
							.addComponent(register_button, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
					.addGap(53))
		);
		contentPane.setLayout(gl_contentPane);
	}
/**
 *���ò�����������������Ϊ��
 * @param e
 */
protected void ResetActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.userName.setText("");
		this.passWord.setText("");
	}

/**
 * �û���½����
 * @param e
 */
	
	private void loginActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String userName = this.userName.getText();
		String passWord = new String(this.passWord.getPassword());
		if(StringUtil.isEmpty(userName)){
			JOptionPane.showMessageDialog(null, "�û�������Ϊ��");
			return;
		}
		if(StringUtil.isEmpty(passWord)){
			JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
			return;
		}
		User user = new User(userName,passWord);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			User currenUser = userDao.login(con,user);
			if(currenUser!= null) {
				//JOptionPane.showMessageDialog(null,"��½�ɹ�");
			  	dispose();
				new MainFrm(user).setVisible(true);
					}
			else {
				JOptionPane.showMessageDialog(null, "�û������������������");
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
