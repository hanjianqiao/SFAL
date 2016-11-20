package inter;
import lexer.*;
import symbols.*;
public class Arith extends Op{
	public final int style = 6;
	public Expr expr1, expr2;
	public Arith(Token tok, Expr x1, Expr x2){
		super(tok, null);
		com = true;
		expr1 = x1;
		expr2 = x2;
		type = Type.max(expr1.type, expr2.type);
		if(type == null) error("type error");
	}
	public int style(){
		return 6;
	}
	public Expr gen(){return new Arith(op, expr1.reduce(), expr2.reduce());}
	public String toString(){
		String str = "";
		if(!expr1.com){
			switch(expr1.style()){
				case 1:
					str += "\t\tLPUSHP\t";
					break;
				case 2:
					str += "\t\tPUSHP\t";
					break;
				case 3:
					str += "\t\tPUSH\t";
					break;
				default:
					str += "\tArith expr1, style :" + expr1.style;
			}
		}
		str += expr1.toString() + "\r\n";
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
				default:
					str += "\tArith expr2, style :" + expr2.style;
			}
		}
		str += expr2.toString() + "\r\n";
		
		str += op.toString();
		return str;
	}
}
