package inter;
import lexer.*;
import symbols.*;
public class Op extends Expr{
	public final int style = 4;
	public Op(Token tok, Type p){
		super(tok, p);
		com = true;
	}
	public Expr reduce(){
		Expr x = gen();
		Temp t = new Temp(type);
		String str = "";
		if(!x.com){
			switch(x.style()){
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
		str += x.toString();
		if(!t.com){
			switch(t.style()){
				case 1:
					str += "\r\n\t\tLSTORE\t";
					break;
				case 2:
					str += "\r\n\t\tSTORE\t";
					break;
			}
		}
		str += t.toString();
		if(!t.com){
			str += "\r\n\t\tPOP\t1";
		}
		emit(str);
		return t;
	}
	public int style(){
		return op.style();
	}
	public String toString(){return op.toString();}
}
