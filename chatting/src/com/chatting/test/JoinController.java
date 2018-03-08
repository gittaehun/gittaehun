package com.chatting.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class JoinController {
	private static final String _ADD = "insert";
	private static final String _MOD = "update";
	private static final String _DEL = "delete";
	private static final String _DET = "select";
	private static final String _ALL = "selectall";
	private static final String _IDCHECK = "idCheck";
	String cols[] = {"���̵�","���","�̸�","����ó","����"};
	String data[][] = new String[0][5];
	DefaultTableModel dtm_member = 
		   new DefaultTableModel(data,cols);
	JTable jtb_addr = new JTable(dtm_member);
	JScrollPane jsp_addr = new JScrollPane(jtb_addr);
	public ChatVO send(ChatVO pvo){
		ChatVO cvo = new ChatVO();
		//�� �Է��� �����Ŵ�?
		if(_ADD.equals(pvo.getCommand())){//_ADD == "insert"
			System.out.println
			("joinController sendȣ�� ����");
			JoinLogic addLogic = new JoinLogic();
			cvo = addLogic.insertChat(pvo);
		}else if(_DET.equals(pvo.getCommand())){
			System.out.println("ȣ��");
			LoginLogic loginlogic = new LoginLogic();
			cvo = loginlogic.selectChat(pvo);
		}else if(_IDCHECK.equals(pvo.getCommand())){
			LoginLogic loginlogic = new LoginLogic();
			cvo = loginlogic.idCheck(pvo);
		}
		return cvo;
	}
	public void refreshData(){
		//���� ������ ���̺� ��ȸ�� ����� �ִٸ� �ϴ� ��� ��������.
		while(dtm_member.getRowCount()>0){
			dtm_member.removeRow(0);
		}
		JoinController 
		jCtrl = new JoinController();
		ChatVO pvo = new ChatVO();
		pvo.setCommand("selectall");
		List<ChatVO> addressList 
		= jCtrl.sendALL2(pvo);	
		Iterator<ChatVO> iter 
		= addressList.iterator();
		while(iter.hasNext()){
			ChatVO avo = iter.next();
			Vector<Object> oneRow = 
					new Vector<Object>();
			oneRow.add(avo.getMem_id());
			oneRow.add(avo.getMem_pw());
			oneRow.add(avo.getMem_name());
			oneRow.add(avo.getMem_tel());
			oneRow.add(avo.getMem_gender());
			dtm_member.addRow(oneRow);			
		}
		
	}
		private List<ChatVO> sendALL2(ChatVO pvo){
		List<ChatVO> memberList 
		= new ArrayList<ChatVO>(); 
		if(_ALL.equals(pvo.getCommand())){
			SearchLogic sLogic = new SearchLogic();
			memberList = 
					sLogic.selectALL2(pvo);
		}
		return memberList;
	}	
}
