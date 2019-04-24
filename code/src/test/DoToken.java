package test;
import java.util.ArrayList;
import java.util.List;

public class DoToken {
	public static List<String> identifier; // ��ʶ���б�
	public static List<String> INTC; // �����б�
	
	public static boolean isIdentifier(String s) { // ��ʶ���Զ���
		if (!Data.L.contains(s.charAt(0))) {
			return false;
		}
		for (int i = 1; i < s.length(); i++) {
			if (!Data.L.contains(s.charAt(i)) && !Data.D.contains(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	public static boolean isINTC(String s) { // ���ֳ����Զ���
		if (s.charAt(0) == '0') {
			if (s.length() == 1) {
				return true;
			} else {
				return false;
			}
		}
		if (!Data.D1.contains(s.charAt(0))) {
			return false;
		}
		for (int i=1; i < s.length(); i++) {
			if (!Data.D.contains(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean doToken(String s) { // �ʷ�����������
		identifier = new ArrayList<String>(); // ÿ�ε��ôʷ���������ʱҪ�����³�ʼ�����б�ͻ����ַ���
		INTC = new ArrayList<String>();
		Data.token = new ArrayList<Token>();
		Data.tokenShow = new StringBuffer();
		Data.tokenShow2 = new StringBuffer();
		int line = 1; // ������ʼ��Ϊһ
		String ss; // s��ȫ�����ַ�����ss�Ƿָ������ַ���
		StringBuffer sb = new StringBuffer(); // sb��������ָ��е��ַ���
		Token t; // �½���token����
		for (int i = 0; i < s.length(); i++) { // ��Դ������һ���ַ�һ���ַ��ؽ��ж�ȡ���������������ʣ�Ȼ�������ǵĻ��ڱ�ʾToken
			if (s.charAt(i) != ' ' && s.charAt(i) != '\n' && s.charAt(i) != '\t'
					&& !Data.separator.contains(String.valueOf((s.charAt(i))))) { // ������ַ����Ƿָ�����ֱ��׷�ӵ�sb��
				sb.append(s.charAt(i));
			} else { // ������ַ��Ƿָ���
				// ���������ĵ���
				if (sb.length() != 0) { // ��������ĵ��ʳ���Ϊ�㣬�������˲��ֶ�ֱ�ӽ������ķָ������ɲ���
					ss = sb.toString();
					if (Character.isLetter(ss.charAt(0))) {
						if (Data.reservedWord.contains((ss))) { // ����ָ������ַ����Ǳ���������token��tokenShow��Ҫ׷��
							t = new Token(2, Data.reservedWord.indexOf(ss),line);
							Data.token.add(t);
							Data.tokenShow.append("(2," + ss + ")");
							Data.tokenShow2.append("("+line+",2," + Data.reservedWord.indexOf(ss)
									+ ")");
						} else if (isIdentifier(ss)) { // ����ָ������ַ����Ǳ�ʶ������token��tokenShow��Ҫ׷��
							if (!identifier.contains(ss)) {
								identifier.add(ss); // �����ʶ���б���û�иñ�ʶ�������
							}
							t = new Token(3, identifier.indexOf(ss),line);
							Data.token.add(t);
							Data.tokenShow.append("(3," + ss + ")");
							Data.tokenShow2.append("("+line+",3," + identifier.indexOf(ss)
									+ ")");
						} else {
							// ��*���б�ʶ�Ĵ�����Ϊ�����Ŀ��ܳ���
							// ************************************************************************************************
							Data.tokenShow.append("\n�ʷ�����ʧ�ܣ���" + line + "�з����ʷ������޷�ʶ��\""
									+ ss + "\"");
							Data.tokenShow2.append("\n�ʷ�����ʧ�ܣ���" + line + "�з����ʷ������޷�ʶ��\""
									+ ss + "\"");
							return false;
						}
					} else { // ��������ֳ���
						if (isINTC(ss)) {
							if (!INTC.contains(ss)) { // ������ֳ����б���û�и����ֳ��������
								INTC.add(ss);
							}
							t = new Token(4, INTC.indexOf(ss),line);
							Data.token.add(t);
							Data.tokenShow.append("(4," + ss + ")");
							Data.tokenShow2.append("("+line+",4," + INTC.indexOf(ss) + ")");
						} else {
							// ************************************************************************************************
							Data.tokenShow.append("\n�ʷ�����ʧ�ܣ���" + line + "�з����ʷ������޷�ʶ��\""
									+ ss + "\"");
							Data.tokenShow2.append("\n�ʷ�����ʧ�ܣ���" + line + "�з����ʷ������޷�ʶ��\""
									+ ss + "\"");
							return false;
						}
					}
					sb = new StringBuffer(); // ���³�ʼ�����Է��뵥�ʵĻ����ַ���
				}
				// ����ָ���
				if (s.charAt(i) == ' ') { // ����ָ����ǿո�����tokenShow��׷�ӿո�
					Data.tokenShow.append(" ");
					Data.tokenShow2.append(" ");
					continue;
				}
				if (s.charAt(i) == '\n') { // ����ָ����ǻ��з�����tokenShow��׷�ӿո�
					line++; // ���к�+1
					Data.tokenShow.append("\n");
					Data.tokenShow2.append("\n");
					continue;
				}
				if (s.charAt(i) == '\t') { // ����ָ������Ʊ������tokenShow��׷���Ʊ��
					Data.tokenShow.append("\t");
					Data.tokenShow2.append("\t");
					continue;
				}
				if (s.charAt(i) == ':') {
					if (s.charAt(++i) == '=') {
						t = new Token(1, Data.separator.indexOf(":="),line); // ����ָ����Ǹ�ֵ������token��tokenShow��Ҫ׷��
						Data.token.add(t);
						Data.tokenShow.append("(1,:=)");
						Data.tokenShow2.append("("+line+",1," + Data.separator.indexOf(":=") + ")");
						continue;
					} else {
						// ************************************************************************************************
						Data.tokenShow.append("\n�ʷ�����ʧ�ܣ���" + line
								+ "�з����ʷ�����\":\"��Ӧ�ý�\"=\"");
						Data.tokenShow2.append("\n�ʷ�����ʧ�ܣ���" + line
								+ "�з����ʷ�����\":\"��Ӧ�ý�\"=\"");
						return false;
					}
				}
				if (s.charAt(i) == '.') {
					if((i+1)!=s.length() && s.charAt(i+1)=='.'){ // �����..����token��tokenShow��Ҫ׷��
						t = new Token(1, Data.separator.indexOf(".."),line); // ����ָ���������������token��tokenShow��Ҫ׷��
						Data.token.add(t);
						Data.tokenShow.append("(1,..)");
						Data.tokenShow2.append("("+line+",1," + Data.separator.indexOf("..") + ")");
						i++;
						continue;
					}
					// ����ָ����������÷���������������token��tokenShow��Ҫ׷��
					t = new Token(1, Data.separator.indexOf("."),line);
					Data.token.add(t);
					Data.tokenShow.append("(1,.)");
					Data.tokenShow2.append("("+line+",1," + Data.separator.indexOf(".") + ")");
					if((i+1)==s.length() || s.charAt(i+1)==' ' || s.charAt(i+1)=='\n' || s.charAt(i+1)=='\t'){ // ����ѵ�����ĩβ��δ��ĩβ������ַ�Ϊ�ո񡢻س����Ʊ��������ʷ�����
						// *********************************************************************
						Data.tokenShow.append("\n�ʷ������ɹ���");
						Data.tokenShow2.append("\n�ʷ������ɹ���");
						return true;
					}
					continue;
				}
				t = new Token(1, Data.separator.indexOf(String.valueOf(s.charAt(i))),line); // ����ָ����Ǹ�ֵ������token��tokenShow��Ҫ׷��
				Data.token.add(t);
				Data.tokenShow.append("(1," + s.charAt(i) + ")");
				Data.tokenShow2.append("("+line+",1,"
						+ Data.separator.indexOf(String.valueOf(s.charAt(i))) + ")");
			}
		}
		// ************************************************************************************************
		if (sb.length() != 0) { // ���������ʱ������ĵ��ʳ��Ȳ�Ϊ�㣬����Ϊ��Ӧ��Token�����ܴʷ�������ʧ�ܣ���Ϊ����δ�ܳɹ�������
			ss = sb.toString();
			if (Character.isLetter(ss.charAt(0))) {
				if (Data.reservedWord.contains((ss))) { // ����ָ������ַ����Ǳ���������token��tokenShow��Ҫ׷��
					t = new Token(2, Data.reservedWord.indexOf(ss),line);
					Data.token.add(t);
					Data.tokenShow.append("(2," + ss + ")");
					Data.tokenShow2.append("("+line+",2," + Data.reservedWord.indexOf(ss)
							+ ")");
				} else if (isIdentifier(ss)) { // ����ָ������ַ����Ǳ�ʶ������token��tokenShow��Ҫ׷��
					if (!identifier.contains(ss)) {
						identifier.add(ss); // �����ʶ���б���û�иñ�ʶ�������
					}
					t = new Token(3, identifier.indexOf(ss),line);
					Data.token.add(t);
					Data.tokenShow.append("(3," + ss + ")");
					Data.tokenShow2.append("("+line+",3," + identifier.indexOf(ss)
							+ ")");
				} else {
					// ��*���б�ʶ�Ĵ�����Ϊ�����Ŀ��ܳ���
					// ************************************************************************************************
					Data.tokenShow.append("\n�ʷ�����ʧ�ܣ���" + line + "�з����ʷ������޷�ʶ��\""
							+ ss + "\"");
					Data.tokenShow2.append("\n�ʷ�����ʧ�ܣ���" + line + "�з����ʷ������޷�ʶ��\""
							+ ss + "\"");
					return false;
				}
			} else { // ��������ֳ���
				if (isINTC(ss)) {
					if (!INTC.contains(ss)) { // ������ֳ����б���û�и����ֳ��������
						INTC.add(ss);
					}
					t = new Token(4, INTC.indexOf(ss),line);
					Data.token.add(t);
					Data.tokenShow.append("(4," + ss + ")");
					Data.tokenShow2.append("("+line+",4," + INTC.indexOf(ss) + ")");
				} else {
					// ************************************************************************************************
					Data.tokenShow.append("\n�ʷ�����ʧ�ܣ���" + line + "�з����ʷ������޷�ʶ��\""
							+ ss + "\"");
					Data.tokenShow2.append("\n�ʷ�����ʧ�ܣ���" + line + "�з����ʷ������޷�ʶ��\""
							+ ss + "\"");
					return false;
				}
			}
			sb = new StringBuffer(); // ���³�ʼ�����Է��뵥�ʵĻ����ַ���
		}
		Data.tokenShow.append("\n�ʷ�����ʧ�ܣ�����δ����������");
		Data.tokenShow2.append("\n�ʷ�����ʧ�ܣ�����δ����������");
		return false;
	}
}
