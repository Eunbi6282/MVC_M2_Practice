package model2.mvcboard;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;

public class MVCBoardDAO extends DBConnPool{
	// 기본생성자 호출시 부모 클래스를 호출
	public MVCBoardDAO () {
		super(); // 부모의 기본생성자 호출. DBCP에서 con객체 활성화
	}
	
	// 검색 조건에 맞는 게시물 개수를 반환합니다. 
	public int selectCount(Map <String, Object> map) { //Map<Key,Value>
		int totalCount = 0;
		String query = "SELECT COUNT(*) FROM mvcboard";	// 레코드의 총 개수반환,
			if (map.get("searchWord") != null) { //T(String)에 searchWord 이 있을 때 where값을 추가로 춰리에 넣는다. 
				query += " Where " + map.get("searchField") + " " + "like '%" + map.get("searchWord") + "%'";
			}
			
		try {
			stmt = con.createStatement();
			rs= stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
					
			
		} catch (Exception e) {
			System.out.println("게시물 카운트중 예외발생");
			e.printStackTrace();
		}
		return totalCount;
	}

	
	// 검색 조건에 맞는 게시물 목록을 반환합니다.
		// DataBase에서 Select한 결과값을 DTO에 담아서 리턴 시켜줌
	public List <MVCBoardDTO> selectListPage ( Map<String, Object> map){
		List<MVCBoardDTO> board = new Vector <MVCBoardDTO>();
		
		String query = " "
				+ "SELECT * FROM ( " 
				+ "		SELECT Tb.*, ROWNUM rNUM FROM ( "
				+ "			SELECT * FROM mvcboard ";
		
		if (map.get("searchWord") != null) {	// 검색 기능을 사용했다라면 
			query += " WHERE " + map.get("searchField")
				+ " LIKE '%" + map.get("searchWord") + "%' ";
		}
		
		query += "		ORDER BY idx DESC"
				+ " ) Tb "
				+ ") " 
				+" WHERE rNUM BETWEEN ? AND ?";
		
		System.out.println(query);  //콘솔에 전체 쿼리 출력
		
		try{	//psmt객체 생성후 쿼리 실행
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery();	// DataBase에서 Select한 결과값을 rs에 저장
			
			// rs의 저장된 값을 DTO에 저장 ==> 객체를 List에 add
			while(rs.next()) {
				MVCBoardDTO dto = new MVCBoardDTO();
				dto.setIdx(rs.getString(1)); //rs의 index1번의 값을 setter통해 주입
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
				
				board.add(dto); // List의 DB의 rs의 하나의 레코드 값을 dto에 저장하고 
									// dto를 List에 추가
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return board;	// board는 DTO객체를 담고 있다. 
	}
	
	// 목록 검색 (Select ) : 주어진 일련번호에 해당하는 값을 DTO에 담아 반환(한 페이지 read)
		//ViewController에서 요청 처리/ idx값으로 select하기
	public MVCBoardDTO selectView(String idx) {
		MVCBoardDTO dto = new MVCBoardDTO();	
		String query = "SELECT * FROM mvcboard WHERE idx =?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				//rs(select 결과물 들어있음) set이용해서 값 주입
				dto.setIdx(rs.getString(1));	// 1번컬럼
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
				
				
			}
			
		}catch (Exception e) {
			System.out.println("게시물 상세정보 출력시 예외 발생");
			e.printStackTrace();
		}
		
		
		return dto;
	}
		// 주어진 일련 번호에 해당하는 게시물의 조회수를 1증가 시킴
	public void updateVisitCount(String idx) {
		String query = "UPDATE mvcboard SET "
				+ " visitcount = visitcount + 1 "
				+ " WHERE idx = ?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("게시물 조회수 증가시 예외 발생");
		}
	}
	
	
	
	
	// 데이터 삽입 (Insert) 
	public int insertWrite (MVCBoardDTO dto) {
		
		int result = 0;
		try {
			String query = "INSERT INTP mvcboard( "
					+ " idx, name, title, content, ofile, sfile, pass) "
					+ " VALUES( " 
					+ " seq_board_num.nextval, ?, ?, ?, ?, ?, ?)";
			
			psmt = con.prepareStatement(query); 
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getPass());
			
			result = psmt.executeUpdate();	//insert가 성공일 때 result > 0 //DB에 값을 저장
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;	// result : Insert성공시 > 0, 실패시 : 0
	}
	
	// 데이터 수정 (Update)
	public int updatePost (MVCBoardDTO dto) {
		int result = 0;
		
		try {
			String query = "UPDATE mvcboard "
					+ " SET title=?, name=?, content=?, ofile=?, sfile=?"
					+ " WHERE idx=? and pass=?"; // id와 password가 다 맞을 때 수정
			// 쿼리문 준비
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle()); //dto에서 title값 가져오기
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getIdx());
			psmt.setString(7, dto.getPass());
			
			result = psmt.executeUpdate();	//update 성공시 result 변수의 값이 > 0
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Update시 예외 발생");
		}
		
		
		return result;  // result > 0 :  수정 성공, result = 0 : 수정 실패
	}
	
	// 데이터 삭제 (Delete)
	public int deletePost(String idx) {
		int result = 0;
		
		try {
			String query = "DELETE mvcboard WHERE idx = ?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);  // 변수로 넘어온 idx
			
			result = psmt.executeUpdate(); //result가 0보다 크면 삭제 성공, result가 0이면 삭제 실패
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Delete시 예외 발생");
		}
		return result;
	}
	
	// 다운로드 횟수를 증가시키는 메서드
	public void downCountPlus (String idx) {
		String query = "UPDATE mvcboard SET downcount = downcount + 1 "
				+ " WHERE idx = ?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("다운로드 횟수 증가시 예외 발생");
		}
	}
	
	//비밀번호 확인 메서드 (입력한 비밀번호가 DB의 값과 일치하는지 확인)
	public boolean confirmPassword (String pass, String idx) {
		boolean isCorr = true;
		try {
			
			//count(*) =1 레코드 개수/ 레코드가 존재하면 아이디와 패스워드가 일치하는 경우이다.
				//count(*) = 0 레코드가 존재하지 않으면 client 에서 넘긴 값이 DB에 존재하지 않는 것이다.
			String query = "SELECT COUNT(*) FROM mvcboard WHERE pass = ? and idx = ?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, pass);
			psmt.setString(2, idx);
			rs = psmt.executeQuery();
			
			rs.next();  //레코드의 첫번째 레코드에 커서를 위치시켜라
			if(rs.getInt(1) == 0) {	//index 방번호 1방번호 값이 0이면 false
				isCorr = false;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("비밀번호 확인시 예외 발생");
		}
		
		return isCorr;
	}
	
	
	
}
