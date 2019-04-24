package test;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Compiler extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel northJP = new JPanel(), southJP = new JPanel();// �˶Ի�����ñ߽粼�֣������С��ϲ����ֱ𴴽��С������
	JScrollPane scrollJSP = new JScrollPane();
	JScrollPane scrollJSP2 = new JScrollPane();
	static JTextArea displayJTA = new JTextArea();
	static JTextArea displayJTA2 = new JTextArea();
	JButton doTokenJB = new JButton("�ʷ�����"), doGrammarJB = new JButton("�﷨����"),helpJB=new JButton("����");
	JComboBox<String> select;
	public Compiler(){
		setTitle("SNL���ԵĴʷ�&LL(1)�﷨������");
		// ʹ���ڳ����Ϊ��Ļ�����4/5���Ҿ�����ʾ
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int windowWidth = (int) (0.8 * screenSize.width), windowHeight = (int) (0.8 * screenSize.height);
		setBounds((int) ((screenSize.width - windowWidth) * 0.5),
				(int) ((screenSize.height - windowHeight) * 0.5), 402,
				225);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(northJP, BorderLayout.CENTER);
		getContentPane().add(southJP, BorderLayout.SOUTH);
		northJP.setLayout(new GridLayout(1, 2));
		southJP.setLayout(new FlowLayout());

		northJP.add(scrollJSP);
		scrollJSP.setViewportView(displayJTA);
		displayJTA.setFont(new Font("����", Font.PLAIN, 16));
		northJP.add(scrollJSP2);
		scrollJSP2.setViewportView(displayJTA2);
		displayJTA2.setFont(new Font("����", Font.PLAIN, 16));

		select=new JComboBox<String>();
		select.addItem("�ⲿ��ʾ");
		select.addItem("�ڲ���ʾ");
		
		southJP.add(select);
		southJP.add(doTokenJB);
		southJP.add(doGrammarJB);
		southJP.add(helpJB);
		
		Data.initialize();
		
		doTokenJB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(DoToken.doToken(displayJTA.getText())){
					doGrammarJB.setEnabled(true);
				}else{
					doGrammarJB.setEnabled(false);
				}
				if(select.getSelectedItem().toString().equals("�ⲿ��ʾ")){
					displayJTA2.setText(Data.tokenShow.toString());
				}else{
					displayJTA2.setText(Data.tokenShow2.toString());
				}
			}
		});
		doGrammarJB.setEnabled(false);// �����﷨������ť��ʼʱ������
		doGrammarJB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				displayJTA2.setText(DoGrammar.doGrammar());
			}
		});
		helpJB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String s="1.���дʷ�����ʱ������ѡ��Token���ⲿ���ڲ���ʾ��"
						+ "\n  �ⲿ��ʾ����������Ϣ��1Ϊ�ָ�����2Ϊ�����֣�3Ϊ��ʶ����4Ϊ���ֳ���"
						+ "\n     ��������Ϣ���ɣ�ֱ����ʾ���û��������׶���"
						+ "\n  �ڲ���ʾ������ʵToken���е�չ�֣�����Ϊ����ǰ���ʵ��кţ�������Ϣ���õ����ڴ洢�����͵��б����������±꣬�����﷨���������롣"
						+ "\n2.���﷨�����Ľ���У�ÿ������ǰ����Բ���Ű���Ϊ��ƥ�䵽���ռ�������Լ��SNL��Ӧ�±�Ĺ����û���������һ����"
						+ "\n3.Ϊ�˰�ȫ��������ʷ�����ʧ�ܣ����﷨�������ܽ������ã�ֱ���ʷ������ɹ���"
						+ "\n4.Ϊ�˼�������﷨����ʱֱ�ӽ����б�ʶ��ͳһ��ʾΪID�����������ֳ���ͳһ��ʾΪINTC��";
				JOptionPane.showMessageDialog(Compiler.this, s,"����",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Compiler();
	}

}
