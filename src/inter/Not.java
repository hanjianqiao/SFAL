package inter;
import lexer.*;
import symbols.*;
public class Not extends Logical{
	public final int style = 11;
	public Not(Token tok, Expr x2){super(tok, x2, x2);}
	public void jumping(int t, int f){expr2.jumping(f, t);}
	public String toString() {
		String str = "";
		if(!expr2.com){
			switch(expr2.style()){
				case 1:
					str += "\t\tLPUSHP\t";
					break;
				case 2:
					str += "\t\tPUSHP\t";
					break;
				case 3:
					str += "\t\tPUSH\t";
					break;
			}
		}
		str += expr2.toString() + "\r\n";
		str += op.toString();
		return str;
	}
	public int style(){
		return 11;
	}
}
