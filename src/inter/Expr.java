package inter;
import lexer.*;
import symbols.*;
public class Expr extends Node{
	public boolean com;
	public Token op;
	public Type type;
	public final int style = 0;
	Expr(Token tok, Type p){com = false; op = tok; type = p;}
	public Expr gen(){return this;}
	public Expr reduce(){return this;}
	public void jumping(int t, int f){emitjumps(toString(), t, f);}
	public void emitjumps(String test, int t, int f){
		if(t != 0 && f != 0){
			emit(test + "\r\n\t\tJTRUEPOP\tL" + t + "\r\n\t\tPOP\t1");
			emit("\t\tJMP\tL" + f);
		}else if(t != 0) emit(test + "\r\n\t\tJTRUEPOP\tL" + t + "\r\n\t\tPOP\t1");
		else if(f != 0) emit(test + "\r\n\t\tJFALSEPOP\tL" + f + "\r\n\t\tPOP\t1");
		else;
	}
	public int style(){
		return op.style();
	}
	public String toString(){return op.toString();}
}
