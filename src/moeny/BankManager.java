package moeny;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JDialog;

public class BankManager extends JFrame implements ActionListener{
	private JTextField textField;//id라벨
	private JTextField textField_1;//이름 라벨
	private JTextField textField_2;//나이 라벨
	private JTextField textField_3;//전화번호 라벨
	private JButton btnNewButton;//가입하기 버튼
	private JButton btnNewButton_1;//회원정보 검색 버튼
	private JButton button;//회원탈퇴 버튼
	private JButton button_1;//회원수정 버튼
	private JButton button_2;//전체회원 목록 버튼
	private JDialog dialog;//회원전체목로을 보기위한 다이얼로그
	private JTextArea jt;//회원전체목록을 실제로 담을 텍스트 에어리어
	
	
	private BankDAO bankDAO = new BankDAO();//db연동을 통해 사용할 DAO메서드
	
	
	public BankManager() {//생성자
		setTitle("은행 회원관리 시스템");
		setSize(500,300);
		getContentPane().setLayout(null);
		
		
		
		//각 텍스트필드 객체생성 및 위치 지정
		textField = new JTextField();
		textField_1 = new JTextField();
		textField_2 = new JTextField();
		textField_3 = new JTextField();
		
		textField.setBounds(166, 26, 116, 21);
		textField_1.setBounds(166, 71, 116, 21);
		textField_2.setBounds(166, 124, 116, 21);
		textField_3.setBounds(166, 176, 116, 21);
		
		textField.setColumns(10);
		textField_1.setColumns(10);
		textField_2.setColumns(10);
		textField_3.setColumns(10);
		
		getContentPane().add(textField);
		getContentPane().add(textField_1);
		getContentPane().add(textField_2);
		getContentPane().add(textField_3);
		
		
		
		//각 라벨 객체생성 및 위치지정
		JLabel lblNewLabel = new JLabel("\uC544\uC774\uB514");
		JLabel label = new JLabel("\uC774\uB984");
		JLabel label_1 = new JLabel("\uB098\uC774");
		JLabel label_2 = new JLabel("\uC804\uD654\uBC88\uD638");
		JLabel lblNewLabel_1 = new JLabel("(-\uC5C6\uC774 \uC368\uC8FC\uC138\uC694)");
		lblNewLabel.setBounds(69, 29, 57, 15);
		label.setBounds(69, 74, 57, 15);
		label_1.setBounds(69, 127, 57, 15);
		label_2.setBounds(69, 179, 57, 15);
		lblNewLabel_1.setBounds(294, 179, 98, 15);
		getContentPane().add(lblNewLabel);
		getContentPane().add(label);
		getContentPane().add(label_1);
		getContentPane().add(label_2);
		getContentPane().add(lblNewLabel_1);
		
		
		
		//각 버튼 객체 생성 및 위치 지정
		btnNewButton = new JButton("\uAC00\uC785\uD558\uAE30");
		btnNewButton_1 = new JButton("\uD68C\uC6D0\uC815\uBCF4\uAC80\uC0C9");
		button = new JButton("\uD68C\uC6D0\uD0C8\uD1F4");
		button_1 = new JButton("\uD68C\uC6D0\uC815\uBCF4\uC218\uC815");
		btnNewButton.setBounds(97, 217, 97, 23);
		btnNewButton_1.setBounds(310, 25, 112, 23);
		button.setBounds(262, 217, 97, 23);
		button_1.setBounds(310, 70, 112, 23);
		getContentPane().add(btnNewButton);
		getContentPane().add(btnNewButton_1);
		getContentPane().add(button);
		getContentPane().add(button_1);
		
		button_2 = new JButton("\uD68C\uC6D0\uC804\uCCB4\uBAA9\uB85D");
		button_2.setBounds(310, 119, 112, 23);
		getContentPane().add(button_2);
		
		//각버튼이 액션리스너 기능을 사용할수 있게 오버라이딩
		btnNewButton.addActionListener(this);
		btnNewButton_1.addActionListener(this);
		button.addActionListener(this);
		button_1.addActionListener(this);
		button_2.addActionListener(this);
		
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnNewButton) {//가입하기 버튼 눌렀을때 기능
			try {
				bankDAO.insert(textField.getText(), textField_1.getText(), Integer.parseInt(textField_2.getText()), textField_3.getText());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else if (e.getSource()==btnNewButton_1) {//회원검색 버튼 눌렀을때 기능
			String id = JOptionPane.showInputDialog(null, "검색할 회원 아이디를 입력하세요");
		  try {
			textField.setText(bankDAO.select(id).getId());
			textField_1.setText(bankDAO.select(id).getName());
			textField_2.setText(bankDAO.select(id).getAge()+"");
			textField_3.setText(bankDAO.select(id).getTel());
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			
			
		}else if (e.getSource()==button) {//회원탈퇴 버튼 눌렀을때 기능
			String id = JOptionPane.showInputDialog(null, "삭제할 회원 아이디를 입력하세요");
			
			
			try {
				if (bankDAO.delete(id)==1) {
					JOptionPane.showMessageDialog(null, "탈퇴되었습니다.");
				}else {
					JOptionPane.showMessageDialog(null, "존재하지 않는 아이디입니다.");
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			
			
			
			
		}else if (e.getSource()==button_1) {//회원수정버튼 눌렀을때 기능
			String id = JOptionPane.showInputDialog(null, "수정할 회원의 아이디를 입력하세요");
			String tel = JOptionPane.showInputDialog(null, "수정할 전화번호를 입력하세요");
			
			try {
				if (bankDAO.upDate(id, tel)==1) {
					JOptionPane.showMessageDialog(null, "회원정보가 성공적으로 수정되었습니다.");
				}else {
					JOptionPane.showMessageDialog(null, "회원정보 수정이 실패하였습니다..");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			
			
		}else if (e.getSource()==button_2) {//전체회원목록 버튼 눌렀을때 기능
			//전체회원목록을 눌렀을때 다이얼로그와 텍스트 에어리어를 객체생성하고 거기다가 회원전체목록을 넣어준다.
			dialog = new JDialog(this, "전체회원목록");
			jt = new JTextArea();
			dialog.setLayout(null);
			dialog.setBounds(500, 0, 350, 300);
			jt.setBounds(0, 0, 350, 300);
			dialog.add(jt);
			dialog.setVisible(true);
			try {
				for (int j = 0; j < bankDAO.selectAll().size(); j++) {
					jt.append(bankDAO.selectAll().get(j).toString());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		
	}//액션 이벤트 끝

	public static void main(String[] args) {
		new BankManager();
	}//메인 메서드 끝
}//클래스 끝
