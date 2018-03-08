package com.chatting.test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Memo extends JDialog implements ActionListener{
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ChatVO pvo = null;
	JPanel jp_north = new JPanel();
	JPanel jp_south = new JPanel();
	
	JLabel jlb_memo 	= new JLabel("메모");
	JTextArea jta_memo	= new JTextArea();
	
	JButton jbtn_save	= new JButton("저장");
	JButton jbtn_exit	= new JButton("취소");
	FriendList fl = null;
	String m_id = null;
	String title = null;
	ChatVO cvo =null;
	
	
	public Memo(FriendList fl){
		this.fl = fl;
		cvo=selectMemo(pvo);
				
		initDisplay_memo();
		//System.out.println(pvo);
	}
	
	public void initDisplay_memo(){
		jbtn_save.addActionListener(this);
		jbtn_exit.addActionListener(this);
		
		this.setVisible(true);
		this.setSize(400, 500);
		
		this.setTitle(fl.nickName+"의 메모"+fl.i);
		
		this.setLayout(new BorderLayout());
		this.add("North",jp_north);
		this.add("Center",jta_memo);
		this.add("South", jp_south);
		
		jp_north.setLayout(new FlowLayout());
		jp_north.add(jlb_memo);
		
		jp_south.setLayout(new GridLayout());
		jp_south.add(jbtn_save);
		jp_south.add(jbtn_exit);
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		//저장 버튼을 클릭시 DB에 메모내용을 insert
		if(obj==jbtn_save){
			addMemo();				
			dispose();
			//JOptionPane.showMessageDialog(this, fl.id);
		}
		else if(obj==jbtn_exit){
			fl.i=0;
			dispose();
		}
	}
	//메모 저장 시 신규로 DB에 insert 하는 메소드
	public void addMemo(){
		m_id = fl.id;
		title = m_id+"의 메모"+fl.i;
		System.out.println(m_id);
		//System.out.println("addMemo메소드 호출 성공");
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO c_memo(m_title, m_nick, m_contents)");
		sql.append("VALUES(?, ?, ?)");
		int result = 0;
        try {                                                 
            Class.forName(DBConnection._DRIVER);
            con = DriverManager.getConnection(DBConnection._URL
                          , DBConnection._USER
                          , DBConnection._PW);
            pstmt = con.prepareStatement(sql.toString());
            pstmt.setString(1, title);
            pstmt.setString(2, fl.nickName);
            pstmt.setString(3, jta_memo.getText());
            result = pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, result);
            if(result == 1){
                   System.out.println("입력 성공");
            }
        } catch (ClassNotFoundException ce) {
        	System.out.println("드라이버 클래스를 못찾아요");
        } catch (SQLException se){
            //System.out.println(se.toString());
            //System.out.println("[[query]]"+sql.toString());
            modMemo();
        	//se.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
      }  
	}
	//메모 저장시 DB에 저장되어 있을 시에 기존 내용을 update 해주는 메소드 
	public void modMemo() {
		m_id = fl.id;
		title = m_id+"의 메모"+fl.i;
		JOptionPane.showMessageDialog(this, title);
		//System.out.println("modMemo 메소드 호출 성공");
		StringBuilder sql = new StringBuilder();
        sql.append("UPDATE c_memo");
        sql.append("   SET  m_nick = ?, m_contents = ?");
        sql.append(" WHERE m_title = ?");
        int result = 0;
        try {                                                 
               Class.forName(DBConnection._DRIVER);
               con = DriverManager.getConnection(DBConnection._URL
                            , DBConnection._USER
                            , DBConnection._PW);
               pstmt = con.prepareStatement(sql.toString());
              // pstmt.setString(1, title);
               pstmt.setString(1, fl.nickName);
               pstmt.setString(2, jta_memo.getText());
               pstmt.setString(3, title);
               result = pstmt.executeUpdate();
               JOptionPane.showMessageDialog(this, "11");
               if(result == 1){
                     System.out.println("수정 성공");
               }
        } catch (ClassNotFoundException ce) {
               System.out.println("드라이버 클래스를 못찾아요");
        } catch (SQLException se){
               System.out.println(se.toString());
               System.out.println("[[query]]"+sql.toString());
        } catch(Exception e){
               e.printStackTrace();
        }
	}
	//메모창 열때 DB에 접속해 저장내용이 있으면 메모창에 출력해주는 메소드
	public ChatVO selectMemo(ChatVO pvo){
		//System.out.println("select 메소드 호출");
		m_id = fl.id;
		title = m_id+"의 메모"+fl.i;
		System.out.println(title);
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT m_title, m_nick, m_contents"); 
		sql.append("  FROM c_memo");
		sql.append(" WHERE m_title = ?");
		ChatVO rvo = null;
		try {
		    Class.forName(DBConnection._DRIVER);
			con = DriverManager.getConnection(DBConnection._URL
											, DBConnection._USER
				       						, DBConnection._PW);
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();
			if(rs.next()){
				rvo = new ChatVO();
				rvo.setName(rs.getString("m_title"));
				rvo.setId(rs.getString("m_nick"));
				rvo.setContents(rs.getString("m_contents"));
			}
			//System.out.println("ChatVO : "+rvo);
			//System.out.println(rvo.getName()+rvo.getId()+rvo.getContents());
			jta_memo.setText(rvo.getContents());
		} catch (NullPointerException ne) {
			System.out.println("첫메모입력입니다.");			
		} catch (SQLException se) {
			System.out.println("[[query]]"+sql.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		return rvo;
	}
	
}
