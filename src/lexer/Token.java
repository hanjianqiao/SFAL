package lexer;

public class Token {
	public final int tag;
	public Token(int t) {tag = t;}
	public String toString() {
		String str = "\t\t";
		switch((char) tag){
			case '+':
				str += "ADD";
				break;
			case '-':
				str += "SUB";
				break;
			case '*':
				str += "MUL";
				break;
			case '/':
				str += "DIV";
				break;
			case '%':
				str += "MOD";
				break;
			case '|':
				str += "BOR";
				break;
			case '&':
				str += "BAND";
				break;
		}
		return str;
	}
	public int style(){
		return 0;
	}
}
