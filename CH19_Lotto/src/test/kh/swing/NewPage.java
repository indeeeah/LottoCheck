package test.kh.swing;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NewPage extends JFrame implements ActionListener {

	JButton btn_1 = new JButton("1");
	JButton btn_2 = new JButton("2");
	JPanel pn_btn = new JPanel();

	JPanel pn_A = new JPanel(null);
	JPanel pn_B = new JPanel(null);

	CardLayout cardLayout = new CardLayout();// 카드 레이아웃 생성

	NewPage()
	    {
	        setLayout(cardLayout);//카드 레이아웃으로
	       
	        pn_btn.add(btn_1);
	        pn_btn.add(btn_2);
	       
	        add("main", pn_btn);
	        add("screen1", pn_A);
	        add("screen2", pn_B);
	       
	        JButton btn_main = new JButton("main");
	        btn_main.setBounds(400, 400, 80, 30);
	        pn_A.setBackground(Color.red);
	        pn_A.add(btn_main);
	        btn_main.addActionListener(this);//화면1 만들기        
	        
	        btn_main = new JButton("main");
	        btn_main.setBounds(400, 400, 80, 30);
	        pn_B.setBackground(Color.blue);
	        pn_B.add(btn_main);
	        btn_main.addActionListener(this);//화면2 만들기        
	        
	        cardLayout.show(getContentPane(), "main");
	       
	        setSize(500,500);
	        setVisible(true);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       
	        btn_1.addActionListener(this);
	        btn_2.addActionListener(this);//버튼 리스너 등록       
	    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_1)// 버튼1을 눌렀을때
		{
			cardLayout.show(getContentPane(), "screen1");
		}
		if (e.getSource() == btn_2)// 버튼2를 눌렀을 경우
		{
			cardLayout.show(getContentPane(), "screen2");
		}
		if (e.getActionCommand().equals("main"))// main 버튼을 눌렀을 경우
		{
			cardLayout.show(getContentPane(), "main");
		}
	}

	public static void main(String[] args) {
		new NewPage();
	}
}
