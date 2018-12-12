package moeny;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BankDAO {

	private Connection con;

	   public void seting() throws Exception {
	      // 1. 드라이버 세팅
	      Class.forName("com.mysql.jdbc.Driver");//import를 시켜
	      // 2. DB연결 - my서버설정 + db명 + id + pw)
	      String url = "jdbc:mysql://localhost:3306/bank";
	      String user = "root";
	      String password = "1234";
	      con = DriverManager.getConnection(url, user, password);
	   }

	   public int insert(String id, String name,int age, String tel)  {//회원가입을 받을 insert문
		   int res = 0;
		   try {
				seting();
				BankDTO dto = new BankDTO();
				dto.setId(id);
				dto.setName(name);
				dto.setAge(age);
				dto.setTel(tel);
				
				// 3. SQL문 객체화
				String sql = "insert into bankmember values(?,?,?,?);";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, dto.getId());
				ps.setString(2, dto.getName());
				ps.setInt(3, dto.getAge());
				ps.setString(4, dto.getTel());
				
				// 4. SQL문 실행 요청
				res = ps.executeUpdate();
				System.out.println("업데이트 확인" + res);
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return res;
		      
		   }//insert문끝
	
	 //  update bodyorder set hconfirm = 'ck_1' where num=10;
	public int upDate(String id, String tel) throws Exception{//회원정보 수정 메서드
		int rn = 0;
		try {
			seting();
			String sql = "update bankmember set tel = '"+tel+"' where id = '"+id+"'";
			
			PreparedStatement ps = con.prepareStatement(sql);
			rn = ps.executeUpdate();
			System.out.println("업데이트 확인" + rn);
			con.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rn;
	}
	
	
	public int delete(String id) throws Exception{//회원정보 삭제 메서드
		int rn = 0;
		
		try {
			seting();
			String sql = "delete from bankmember where id ='"+id+"'";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			rn = ps.executeUpdate();
			
			System.out.println("회원탈퇴 확인" + rn );
			
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rn;
		
		
	}
	
	public BankDTO select(String id) {//회원정보 검색 메서드
		try {
			seting();
			
			BankDTO dto = new BankDTO();
			
			String sql = "select * from bankmember where id ='"+id+"'";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
				id = rs.getString(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				String tel = rs.getString(4);
				
				dto.setId(id);
				dto.setName(name);
				dto.setAge(age);
				dto.setTel(tel);
			
			con.close();
			return dto;
		
		} catch (Exception e) {
			System.out.println("에러");
		}
		return null;
		
	}
	
	
	
	
	public ArrayList<BankDTO> selectAll() throws Exception{//모든 회원정보 검색 메서드
		ArrayList<BankDTO> dto = new ArrayList<>();
		
		try {
			seting();
			String sql = "select * from bankmember";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			
			while (rs.next()) {
				
				String id = rs.getString(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				String tel = rs.getString(4);

				BankDTO bDTO = new BankDTO(id, name, age, tel);
				
				dto.add(bDTO);
			}
			return dto;
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return dto; 
	}
	
	
}//클래스 끝
