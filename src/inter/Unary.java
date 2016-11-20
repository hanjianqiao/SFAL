package inter;
import lexer.*;
import symbols.*;
public class Unary extends Op{
	public final int style = 8;
	public Expr expr;
	public Unary(Token tok, Expr x){
		super(tok, null);
		expr = x;
		com = true;
		type = Type.max(Type.Int, expr.type);
		if(type == null) error("type error");
	}
	public int style(){
		return 8;
	}
	public Expr gen(){return new Unary(op, expr.reduce());}
	public String toString(){
		String str = "";
		if(!expr.com){
			switch(expr.style()){
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
		str += expr.toString() + "\r\n\t\t";
		str += op.toString();
		
		return str;
	}
}
