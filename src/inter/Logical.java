package inter;
import lexer.*;
import symbols.*;
public class Logical extends Expr{
	public final int style = 5;
	public Expr expr1, expr2;
	Logical(Token tok, Expr x1, Expr x2){
		super(tok, null);
		com = true;
		expr1 = x1;
		expr2 = x2;
		type = check(expr1.type, expr2.type);
		if(type == null) error("type error");
	}
	public Type check(Type p1, Type p2){
		if(p1 == Type.Bool && p2 == Type.Bool) return Type.Bool;
		else return null;
	}
	public Expr gen(){
		int f = newlabel();
		int a = newlabel();
		Temp temp = new Temp(type);
		this.jumping(0, f);
		emit("\t\t" + temp.toString() + " = true");
		emit("\t\tjmp L" + a);
		emitlabel(f);
		emit("\t\t" + temp.toString() + " = false");
		emitlabel(a);
		return temp;
	}
	public int style(){
		return 5;
	}
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
			}
		}
		str += expr2.toString() + "\r\n\t\t";
		
		str += op.toString();
		return str;
	}
}
