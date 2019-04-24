package test;

import java.util.Stack;

class DoGrammar {
	public static Stack<String> stack;
	public static StringBuffer grammarShow;
	public static StringBuffer match;
	
	public static String doGrammar() {
		grammarShow=new StringBuffer();
		match=new StringBuffer();
		match.append("(");
		stack=new Stack<String>();
		stack.push("Program");
		String iToken=null; // Ϊtoken������λ�Ļ�ԭ��ķ��ռ���
		String iStack=null; // Ϊջ�����ռ���
		int iRule=0; // Ϊ��Լ�õĹ����к�
		int iLine=1; // token�е��к�
		boolean need=true; // ָʾ����ѭ���Ƿ���Ҫ��ԭtoken����ʼʱ��Ҫ���ڻ�ԭ����Ϊ�٣���match����ǰ���ռ�������Ϊ����continue
		for(int i=0;i<Data.token.size();){
			if(need){ // ��ԭtoken�еĵ�����Ϣ��iToken
				if(Data.token.get(i).l>iLine){
					iLine=Data.token.get(i).l;
					grammarShow.append("\n");
				}
				switch(Data.token.get(i).i){
				case 1:
					iToken=Data.separator.get(Data.token.get(i).j);
					break;
				case 2:
					iToken=Data.reservedWord.get(Data.token.get(i).j);
					break;
				case 3:
					iToken="ID"; // ��ֵΪID������ʶ��
					break;
				case 4:
					iToken="INTC"; // ��ֵΪINTC�������ֳ���
					break;
				default:
				}
				need=false;
			}
			if(stack.isEmpty()){ // ���token���л�δ�������ջ�ѿգ������
				grammarShow.append("\n�﷨����ʧ�ܣ�\n��"+iLine+"�н����﷨����ʱ����ջ�ѿն�token���в�Ϊ�գ�");
				return grammarShow.toString();
			}
			iStack=stack.pop(); // Ϊջ��Ԫ��
			System.out.println(iStack);
			if(iStack.equals("null")){
				continue;
			}
			if(Data.nonTerminal.contains(iStack)){ // ջ��Ԫ��Ϊ���ռ�������Ҫ����LL(1)��������й�Լ
				System.out.print(Data.nonTerminal.indexOf(iStack)+" "+Data.terminal.indexOf(iToken));
				if((iRule=Data.analysis[Data.nonTerminal.indexOf(iStack)][Data.terminal.indexOf(iToken)])==0){ // ������û�п��õĹ�����й�Լ
					grammarShow.append("\n�﷨����ʧ�ܣ�\n��"+iLine+"�н��й�Լʱ�����޿��õĹ���");
					System.out.println(" "+iRule);
					return grammarShow.toString();
				}else{
					System.out.println(" "+iRule);
					for(int j=Data.rule.get(iRule).B.size()-1;j>=0;j--){ // ������������ջ
						 stack.push(Data.rule.get(iRule).B.get(j)); 
					 }
					 match.append(iRule+",");
					 System.out.println(match.toString());
					 System.out.println();
					 continue;
				}
			}else{ // ��ʱջ��Ԫ��Ϊ�ռ�������Ҫ��token������λ����ƥ��
				if(iStack.equals(iToken)){ // ƥ��ɹ�
					match.setLength(match.length()-1); // ɾ��ĩβ�ģ���
					if(match.length()!=0){
						match.append(")"); // ���������׷���ϣ�
					}
					grammarShow.append(match.toString()+iToken+" ");
					System.out.println(grammarShow.toString());
					System.out.println();
					match=new StringBuffer();
					match.append("(");
					i++;
					need=true;
					continue;
				}else{ // ��ƥ�������
					grammarShow.append("\n�﷨����ʧ�ܣ�\n��"+iLine+"�н����ռ���ƥ��ʱ����");
					return grammarShow.toString();
				}
			}
		} // forѭ������
		if(stack.isEmpty()){
			grammarShow.append("\n�﷨�����ɹ���");
			return grammarShow.toString();
		}else{
			grammarShow.append("\n�﷨����ʧ�ܣ�\n��"+iLine+"�н����﷨����ʱ����token������Ϊ�ն�ջ���գ�");
			return grammarShow.toString();
		}
	}
}
