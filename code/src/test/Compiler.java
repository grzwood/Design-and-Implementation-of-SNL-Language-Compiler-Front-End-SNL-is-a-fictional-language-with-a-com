package test;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Compiler extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel northJP = new JPanel(), southJP = new JPanel();// 此对话框采用边界布局，根据中、南部而分别创建中、南面板
	JScrollPane scrollJSP = new JScrollPane();
	JScrollPane scrollJSP2 = new JScrollPane();
	static JTextArea displayJTA = new JTextArea();
	static JTextArea displayJTA2 = new JTextArea();
	JButton doTokenJB = new JButton("词法分析"), doGrammarJB = new JButton("语法分析"),helpJB=new JButton("帮助");
	JComboBox<String> select;
	public Compiler(){
		setTitle("SNL语言的词法&LL(1)语法分析器");
		// 使窗口长宽均为屏幕长宽的4/5，且居中显示
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
		displayJTA.setFont(new Font("宋体", Font.PLAIN, 16));
		northJP.add(scrollJSP2);
		scrollJSP2.setViewportView(displayJTA2);
		displayJTA2.setFont(new Font("宋体", Font.PLAIN, 16));

		select=new JComboBox<String>();
		select.addItem("外部表示");
		select.addItem("内部表示");
		
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
				if(select.getSelectedItem().toString().equals("外部表示")){
					displayJTA2.setText(Data.tokenShow.toString());
				}else{
					displayJTA2.setText(Data.tokenShow2.toString());
				}
			}
		});
		doGrammarJB.setEnabled(false);// 设置语法分析按钮初始时不可用
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
				String s="1.进行词法分析时，可以选择Token的外部和内部表示："
						+ "\n  外部表示是由类型信息：1为分隔符；2为保留字；3为标识符；4为数字常量"
						+ "\n     和内容信息构成，直接显示给用户，方便易读；"
						+ "\n  内部表示则是真实Token序列的展现，依次为：当前单词的行号，类型信息，该单词在存储该类型的列表中所处的下标，它是语法分析的输入。"
						+ "\n2.在语法分析的结果中，每个单词前都用圆括号包含为了匹配到该终极符而规约的SNL对应下标的规则，用户不妨检验一二。"
						+ "\n3.为了安全起见，若词法分析失败，则语法分析功能将不可用，直到词法分析成功。"
						+ "\n4.为了简单起见，语法分析时直接将所有标识符统一表示为ID，将所有数字常量统一表示为INTC。";
				JOptionPane.showMessageDialog(Compiler.this, s,"帮助",JOptionPane.INFORMATION_MESSAGE);
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
