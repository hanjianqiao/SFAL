package inter;
import lexer.*; import symbols.*;
public class SetElem extends Stmt{
	public Id array; public Expr index ; public Expr expr;
	public SetElem(Access x , Expr y){
		array = x.array; index = x.index ; expr = y;
		if(check(x.type, expr.type)==null) error("type error");
	}
	
	public Type check(Type p1,Type p2){
		if(p1 instanceof Array || p2 instanceof Array) return null;
		else if(p1 == p2) return p2;
		else if(Type.numeric(p1)&& Type.numeric(p2)) return p2;
		else return null;
	}
	
	public void gen(int b , int a ){
		String s1 = index.reduce().toString();
		String s2 = expr.reduce().toString();
		String str = "";
		if(!index.reduce().com){
			switch(index.reduce().style()){
				case 1:
					s1 = "\t\tLPUSHP\t" + s1;
					break;
				case 2:
					s1 = "\t\tPUSHP\t" + s1;
					break;
				case 3:
					s1 = "\t\tPUSH\t" + s1;
					break;
			}
		}
		str += "\t\tPUSH\t" + array.toString() + "\r\n" + s1 + "\r\n\t\tADD";
		if(!expr.reduce().com){
			switch(expr.reduce().style()){
				case 1:
					s2 = "\t\tLPUSHP\t" + s2;
					break;
				case 2:
					s2 = "\t\tPUSHP\t" + s2;
					break;
				case 3:
					s2 = "\t\tPUSH\t" + s2;
					break;
			}
		}
		str += "\r\n\t\t" + s2 + "\r\n\t\tSTOREV\r\n\t\tPOP\t2";
		emit(str);
	}
}