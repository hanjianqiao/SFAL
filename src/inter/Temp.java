package inter;
import lexer.*;
import symbols.*;
public class Temp extends Expr{
	public static int count = 0;
	public final int style = 1;
	int number = 0;
	public Temp(Type p){super(Word.temp, p); number = ++count;}
	public int style(){
		return 1;
	}
	public String toString(){return "t" + number;}
}
