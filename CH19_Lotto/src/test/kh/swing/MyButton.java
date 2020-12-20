package test.kh.swing;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class MyButton extends JButton {
	private Color txtColor = Color.black;
	private Color bgColor = Color.white;
	public MyButton(String txt) { //버튼 만들때 보통 문자도 함께 가져오기 때문에 String
		super(txt);
		setBorderPainted(false);		//테두리선 없음
		setOpaque(false);  //뒷배경색이 나타남. 투명도 100%
	}
	
	
	public void setTxtColor(Color txtColor) {
		this.txtColor = txtColor;
	}


	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}

	@Override
	public void paint(Graphics grps){
		grps.setColor(bgColor);		// setColor 다음에 이어서 나오는 부분에 지정할 색상.
		grps.fillRoundRect(0, 0, getWidth(), getHeight(), 100, 100);  //100은 완벽 원형, getWidth는 로또의 init의 btn[i].setBounds에서 들어옴.
		grps.setColor(txtColor);	// 여기서 setColor 다시 지정하면.. 이 다음부터 나오는 부분에 지정할 색상.
		grps.drawString(getText(), (int)getSize().getWidth()/2-8, (int)getSize().getWidth()/2+5); //나누면 더블로 나오기 때문에 소숫점 버리기 위해 int로
		
	}

}
