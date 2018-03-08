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
	 * 어떤 기능을 원하는지는 사용자가 선택
	 * 화면에서 선택 받은 메뉴(기능)는 프로그램에게 전달 되야함
	 * 이것을 선택하는것은 사용자 요청을 받아주는것은 send메소드임
	 * 입력인 경우 주소록 테이블에 필요로하는 값들을 사용자로 부터 입력 받아야하고
	 * 이 값을 컨트롤계층에 전달
	 * 그래야 다음 클래스에 다시 전달할수 있음
	 * 그래서 파라미터 타입에 AddressVO를 사용함
	 * 또 상세조회의 경우 select를 처리하므로 그 결과를 오라클로 부터 받아서
	 * 화면에 뿌려야함
	 * 그래서 파라미터에도 AddressVO
	 * 리턴타입에도 AddressVO타입으로 하는데
	 * 둘의 이름이 같아서 헷갈릴 수 있으므로 접두어를 활용하자
	 * 접두어에 p를 붙이면 파라미터 타입 즉 사용자가 입력한 값을 담고 
	 * r을 붙이면 조회결과를 담기로함
	 * @param pvo
	 * @return
	 */
	public AddressVO send(AddressVO pvo){
		AddressVO rvo = new AddressVO();	
		//입력일시
			if(_ADD.equals(pvo.getCommand())){
				System.out.println("ac send _ADD 호출 성공");
				AddLogic addLogic = new AddLogic();
				rvo = addLogic.insertAddress(pvo);
			}
		//수정시
			if(_MOD.equals(pvo.getCommand())){
				/*System.out.println("컨트롤러 : _MOD");
				System.out.println(pvo.getAddr_no());*/
				ModLogic modLogic = new ModLogic();
				rvo = modLogic.updateAddress(pvo);	
			}
		//삭제시
			if(_DEL.equals(pvo.getCommand())){
				DelLogic delLogic = new DelLogic();
				rvo = delLogic.deleteAddress(pvo);
			}
		
		return rvo; 
	}
	
	public AddressVO[] allSend(AddressVO pvo){
	AddressVO rvos[] = null;
	//전체 조회시
		/*if(_ALL.equals(pvo.getCommand())){
		AllLogic allLogic = new AllLogic();
		
		rvos = allLogic.allAddress2(pvo);
		//System.out.println(allLogic.allAddress(pvo).length);
		//System.out.println("컨트롤러"+rvos+" 길이 : "+allLogic.allAddress(pvo).length);
		}*/
		//검색시
		if(_SEL.equals(pvo.getCommand())){
				System.out.println("sellogic");
				System.out.println("sellogic num : "+pvo.getAddr_phone());
				SelLogic selLogic = new SelLogic();
				rvos = selLogic.selectAddress(pvo);
				//System.out.println("검색호출");
				//System.out.println(rvos);
			}
		return rvos;
	}
	
	public List<AddressVO> allsend2(AddressVO pvo){
		List<AddressVO> addressList = new ArrayList<AddressVO>();
		//전체 조회시
			if(_ALL.equals(pvo.getCommand())){
			AllLogic allLogic = new AllLogic();
			
			addressList = allLogic.allAddress2(pvo);
			//System.out.println(allLogic.allAddress(pvo).length);
			//System.out.println("컨트롤러"+rvos+" 길이 : "+allLogic.allAddress(pvo).length);
			}
			return addressList;
	}
	
	
}
