package com.mvc;

import java.util.ArrayList;
import java.util.List;

public class AddressController {
	private static final String _ADD = "insert";
	private static final String _MOD = "update";
	private static final String _DEL = "delete";
	private static final String _SEL = "select";
	private static final String _ALL = "selectall";
	
	/****************************************
	 * � ����� ���ϴ����� ����ڰ� ����
	 * ȭ�鿡�� ���� ���� �޴�(���)�� ���α׷����� ���� �Ǿ���
	 * �̰��� �����ϴ°��� ����� ��û�� �޾��ִ°��� send�޼ҵ���
	 * �Է��� ��� �ּҷ� ���̺� �ʿ���ϴ� ������ ����ڷ� ���� �Է� �޾ƾ��ϰ�
	 * �� ���� ��Ʈ�Ѱ����� ����
	 * �׷��� ���� Ŭ������ �ٽ� �����Ҽ� ����
	 * �׷��� �Ķ���� Ÿ�Կ� AddressVO�� �����
	 * �� ����ȸ�� ��� select�� ó���ϹǷ� �� ����� ����Ŭ�� ���� �޾Ƽ�
	 * ȭ�鿡 �ѷ�����
	 * �׷��� �Ķ���Ϳ��� AddressVO
	 * ����Ÿ�Կ��� AddressVOŸ������ �ϴµ�
	 * ���� �̸��� ���Ƽ� �򰥸� �� �����Ƿ� ���ξ Ȱ������
	 * ���ξ p�� ���̸� �Ķ���� Ÿ�� �� ����ڰ� �Է��� ���� ��� 
	 * r�� ���̸� ��ȸ����� ������
	 * @param pvo
	 * @return
	 */
	public AddressVO send(AddressVO pvo){
		AddressVO rvo = new AddressVO();	
		//�Է��Ͻ�
			if(_ADD.equals(pvo.getCommand())){
				System.out.println("ac send _ADD ȣ�� ����");
				AddLogic addLogic = new AddLogic();
				rvo = addLogic.insertAddress(pvo);
			}
		//������
			if(_MOD.equals(pvo.getCommand())){
				/*System.out.println("��Ʈ�ѷ� : _MOD");
				System.out.println(pvo.getAddr_no());*/
				ModLogic modLogic = new ModLogic();
				rvo = modLogic.updateAddress(pvo);	
			}
		//������
			if(_DEL.equals(pvo.getCommand())){
				DelLogic delLogic = new DelLogic();
				rvo = delLogic.deleteAddress(pvo);
			}
		
		return rvo; 
	}
	
	public AddressVO[] allSend(AddressVO pvo){
	AddressVO rvos[] = null;
	//��ü ��ȸ��
		/*if(_ALL.equals(pvo.getCommand())){
		AllLogic allLogic = new AllLogic();
		
		rvos = allLogic.allAddress2(pvo);
		//System.out.println(allLogic.allAddress(pvo).length);
		//System.out.println("��Ʈ�ѷ�"+rvos+" ���� : "+allLogic.allAddress(pvo).length);
		}*/
		//�˻���
		if(_SEL.equals(pvo.getCommand())){
				System.out.println("sellogic");
				System.out.println("sellogic num : "+pvo.getAddr_phone());
				SelLogic selLogic = new SelLogic();
				rvos = selLogic.selectAddress(pvo);
				//System.out.println("�˻�ȣ��");
				//System.out.println(rvos);
			}
		return rvos;
	}
	
	public List<AddressVO> allsend2(AddressVO pvo){
		List<AddressVO> addressList = new ArrayList<AddressVO>();
		//��ü ��ȸ��
			if(_ALL.equals(pvo.getCommand())){
			AllLogic allLogic = new AllLogic();
			
			addressList = allLogic.allAddress2(pvo);
			//System.out.println(allLogic.allAddress(pvo).length);
			//System.out.println("��Ʈ�ѷ�"+rvos+" ���� : "+allLogic.allAddress(pvo).length);
			}
			return addressList;
	}
	
	
}
