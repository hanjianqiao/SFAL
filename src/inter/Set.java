package inter;
import lexer.*;
import symbols.*;
public class Set extends Stmt{
	public Id id;
	public Expr expr;
	public Set(Id i , Expr x){
		id = i;
		expr = x;
		if(check(id.type, expr.type)== null) error("type error");
	}
	
	public Type check(Type p1, Type p2){
		if(Type.numeric(p1)&&Type.numeric(p2)) return p2;
		else if(p1 == Type.Bool && p2 == Type.Bool) return p2;
		return null;
	}
	
	public void gen(int b , int a){
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
		str += expr.gen().toString();
			switch(id.style()){
				case 1:
					str += "\r\n\t\tLSTORE\t";
					break;
				case 2:
					str += "\r\n\t\tSTORE\t";
					break;
			}
		str += id.toString() + "\r\n\t\tPOP\t1";
		emit(str);
	}
}