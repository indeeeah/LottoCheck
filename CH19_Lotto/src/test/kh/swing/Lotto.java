package test.kh.swing;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;

import javax.swing.*;
//https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=112
//{"totSellamnt":59292005000,   //당첨금
//"returnValue":"success",		//회차 여부
//"drwNoDate":"2005-01-22",		//추첨일
//"firstWinamnt":1567271167,	
//"drwtNo6":42,
//"drwtNo4":33,
//"firstPrzwnerCo":9,			//1등 당첨자 명수
//"drwtNo5":41,
//"bnusNo":43,					//보너스 번호
//"firstAccumamnt":0,			//1등 당첨금
//"drwNo":112,					//회차
//"drwtNo2":29,					//2번
//"drwtNo3":30,						
//"drwtNo1":26}					//1번째 당첨번호

import org.json.simple.JSONObject;

public class Lotto extends JFrame implements MouseListener, KeyListener {
	final int WIDTH_BTN = 50;
	final int WIDTH_GAP = 5;
	private final int MAX_CNT = 6;
	private final int MAX_CNT_W_BN = 7;
	// 로또 당첨 번호 표시 - JButton --> 라벨을 쓸 수 없기 때문(크기 위치조정 안됨). 동그라미 그려서 색칠하는 형태
	// 7개
	private MyButton[] btn = new MyButton[MAX_CNT_W_BN];

	// 로또 당첨 확인할 번호 입력 - TextField
	private JTextField[] txt = new JTextField[MAX_CNT];

	// 회차 입력 - JTextField
	private JTextField turnTxt = new JTextField();

	// 회차 확인 = JButton
	private JButton checkBtn = new JButton("확인");

	// 정보 - JLabel
	private JLabel infoLbl = new JLabel();

	// 순위 문구
	JLabel rankLabel = new JLabel("");

	// 회차 정보, 경고 문구 표시할 라벨
	JLabel turnLabel = new JLabel("");
	
	

	public Lotto() {
		super("로또확인 프로그램");
		// 처음 화면을 구성함
		init();
//		checkMine();
		// 이벤트 등록
		event();
		// 화면에 보여주기
		setSize(600, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void init() {
		Container c = getContentPane();
		c.setLayout(null);
		int w = 30;
		for (int i = 0; i < btn.length; i++) {
			btn[i] = new MyButton("0" + (i + 1));
			btn[i].setBounds(30 + (WIDTH_BTN * i) + (WIDTH_GAP * i), 50, WIDTH_BTN, WIDTH_BTN);// 아래 두줄과 같은 결과
//			btn[i].setBounds(w, 50, WIDTH_BTN, WIDTH_BTN);
//			w += WIDTH_BTN + WIDTH_GAP;
			c.add(btn[i]);
		}

		for (int i = 0; i < txt.length; i++) {
			txt[i] = new JTextField(i + 1);
			txt[i].setBounds(30 + (WIDTH_BTN * i) + (WIDTH_GAP * i), 120, WIDTH_BTN, WIDTH_BTN);
			txt[i].setHorizontalAlignment(JTextField.CENTER); // 커서 위치
			c.add(txt[i]);
		}

		// 위치는 항상 add하기 전에 잡아야 한다
		turnTxt.setBounds(50, 200, 100, 40);
		c.add(turnTxt);
		checkBtn.setBounds(200, 200, 100, 40);
		c.add(checkBtn);
		infoLbl.setOpaque(true);
		infoLbl.setBounds(10, 10, 300, 40);
		infoLbl.setForeground(Color.red);
		c.add(infoLbl);
		rankLabel.setBounds(40, 60 + 60 + 60 + 30 + 60 + 5, 300, 30);
		c.add(rankLabel);
		turnLabel.setOpaque(false);
		turnLabel.setBounds(25, 60 + 60 + 60 + 30, 300, 120);
		turnLabel.setForeground(Color.black);
		c.add(turnLabel);

	}

	private void event() {
		checkBtn.addMouseListener(this); // this : 현재 동작하고 있는 이 객체 등록
		turnTxt.addKeyListener(this);
		// txt.addKeyListener(this); //텍스트는 써지기는 하지만 엔터가 안먹히고, 다음 포커스로 가고 싶으면 tab을 클릭해야함
		// 배열로 설정된 경우는 txt[0].addKeyListener(this);처럼 각각 지정 해야 함
	}

	@SuppressWarnings("unlikely-arg-type")
	private void showResult() {
		super.setTitle("결과화면보기");
		// 회차에서 숫자가 아닌 입력 거르기
		try {
			Integer.parseInt(turnTxt.getText()); // 문자로 입력받은 turnNum을 int형으로 반환
////			for (int i = 0; i < txt.length; i++) {
////				Integer.parseInt(txt[i].getText());
////			}
////		} catch (InputMismatchException e) {
////			turnLabel.setText("찾는 회차 번호를 입력해주세요");
////			return;
//// 
		} catch (Exception e) {
			// e.printStackTrace();
			turnLabel.setText("찾는 회차의 숫자를 입력해주세요");
			turnTxt.setText("");
			return;
		}
		turnLabel.setText("");

//		try {
		JsonReader jr = new JsonReader();
		JSONObject jo = jr.connectionUrlToJson(turnTxt.getText()); // getText를 넣으면 문자를 긁어온다// 숫자 넣고 싶으면 String이기 때문에
		String[] right = new String[MAX_CNT]; // 로또번호 맞는지 입력한 것 저장
		int nCnt = 0; // 맞는 숫자 개수
		int bCnt = 0; // 맞는 숫자 개수(보너스 번호. 2등)
						// ""
						// 필요하다
		if (jo == null) {
			infoLbl.setText("찾는 회차의 정보가 없습니다.");
			turnTxt.setText("");
			return;
		}
		if (jo.get("returnValue").equals("fail")) {
			infoLbl.setText(turnTxt.getText() + "찾는 회차의 정보가 없습니다.");
			turnTxt.setText(""); // 텍스트박스 깨끗하게 해줌
			return;
		}
		infoLbl.setText(turnTxt.getText() + "회차의 정보입니다.");
		turnTxt.setText("");

		for (int i = 0; i < MAX_CNT; i++) {

//			btn[i].setText((String) jo.get("drwtNo" + (i + 1)));	//String 형태가 보장된다면 이거.
			String strNo = String.valueOf(jo.get("drwtNo" + (i + 1)));
			// btn[i].setText((String.valueOf(jo.get("drwtNo" + (i + 1))))); // 보장되지 않는다면
			// 이거. 이게 더 안전하다.

			// 공 색깔 바꾸기
			btn[i].setText(strNo);
			int a = Integer.parseInt(strNo);
			if (a > 40) {
				btn[i].setBgColor(Color.magenta);
			} else if (a > 30) {
				btn[i].setBgColor(Color.cyan);
			} else if (a > 20) {
				btn[i].setBgColor(Color.lightGray);
			} else if (a > 10) {
				btn[i].setBgColor(Color.pink);
			} else {
				btn[i].setBgColor(Color.orange);
			}
			btn[6].setBgColor(Color.green);
//				}
//				return;
//				}
//			int a = Integer.parseInt(strNo) / 10;
//			switch (a) {
//			case 0:
//				btn[i].setBgColor(Color.orange);
//				break;
//			case 1:
//				btn[i].setBgColor(Color.pink);
//				break;
//			case 2:
//				btn[i].setBgColor(Color.lightGray);
//				break;
//			case 3:
//				btn[i].setBgColor(Color.cyan);
//				break;
//			default:
//				btn[i].setBgColor(Color.magenta);
//				break;
//			}		//이것보다 위에가 더 정확하다

		}
		btn[MAX_CNT].setText(String.valueOf(jo.get("bnusNo")));

		// 로또 번호 창에 숫자 말고 다른 문자 입력했을 떄
		for (int i = 0; i < MAX_CNT; i++) {
			if (txt[i].getText().equals("")) { // 로또 번호 쓰지 않고 회차만 적었을 때
				try {
					right[i] = txt[i].getText();
					Integer.parseInt(right[i]);
				} catch (Exception e) {
					turnLabel.setText("");
					return; // return쓰면 아예 메소드를 나가버린다.
				}
			}
			if (!txt[i].getText().equals("")) { // 로또 번호에 숫자 말고 문자 입력했을 때
				try {
					right[i] = txt[i].getText();
					Integer.parseInt(right[i]);
				} catch (Exception e) {
					turnLabel.setText("숫자만 입력해주세요");
					txt[0].setText("");
					txt[1].setText("");
					txt[2].setText("");
					txt[3].setText("");
					txt[4].setText("");
					txt[5].setText("");
					return;
				}
			}
		}
		
		
		//로또 번호 범위
		if(Integer.parseInt(txt[0].getText())<1||Integer.parseInt(txt[0].getText())>45
				||Integer.parseInt(txt[1].getText())<1||Integer.parseInt(txt[1].getText())>45
				||Integer.parseInt(txt[2].getText())<1||Integer.parseInt(txt[2].getText())>45
				||Integer.parseInt(txt[3].getText())<1||Integer.parseInt(txt[3].getText())>45
				||Integer.parseInt(txt[4].getText())<1||Integer.parseInt(txt[4].getText())>45
				||Integer.parseInt(txt[5].getText())<1||Integer.parseInt(txt[5].getText())>45)
		{
			turnLabel.setText("로또 번호의 범위는 1~45 입니다.");
			return;
		}
				
		
		//중복 입력 방지
		ArrayList<Integer> checkList = new ArrayList<Integer>();
		for(int i = 0; i<txt.length; i++) {
			checkList.add(Integer.parseInt(txt[i].getText()));
		}
		
		HashSet<Integer> hash = new HashSet<Integer>(checkList);
		ArrayList<Integer> checkedList = new ArrayList<Integer>(hash);
		if(checkedList.size()<6) {
			turnLabel.setText("중복 입력된 로또 번호가 있습니다.");
			return;
		}
		
		
		for (int i = 0; i < MAX_CNT; i++) {
			for (int j = 0; j < right.length; j++) {
				if (right[j].equals(btn[i].getText())) { // 텍스트랑 버튼이랑 비교하는거니까 텍스트의 j와 버튼의 i를 비교해야한다
					btn[i].setBgColor(Color.yellow);
					btn[i].setTxtColor(Color.blue);
					nCnt++;
				}
			}
		}
		for (int i = 0; i < MAX_CNT; i++) {
			if (right[i].equals(btn[6].getText())) {
				btn[6].setBgColor(Color.yellow);
				btn[6].setTxtColor(Color.blue);
				bCnt++;
			}
		}
		
		
		if (nCnt == 6) {
			rankLabel.setText("축하합니다 1등입니다!");
		} else if (nCnt == 5 && bCnt == 1) {
			rankLabel.setText("축하합니다 2등입니다!");

		} else if (nCnt == 5) {
			rankLabel.setText("축하합니다 3등입니다!");

		} else if (nCnt == 4) {
			rankLabel.setText("축하합니다 4등입니다!");

		} else if (nCnt == 3) {
			rankLabel.setText("축하합니다 5등입니다!");

		} else {
			rankLabel.setText("다음 기회를 노려보세요.");
		}
//		} catch (Exception e) {
//			e.printStackTrace();
//			turnLabel.setText("예상치 못한 오류가 발생했습니다");
//			return;
	}

//	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			// 회차 정보 결과확인
			showResult();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		showResult();
	}

}
