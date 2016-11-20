package inter;
import lexer.*;
import symbols.*;
public class Id extends Expr{
	public final int style = 2;
	public int offset;
	public Id(Word id, Type p, int b){super(id, p); offset = b;}
	public int style(){
		return 2;
	}
	public String toString(){return op.toString();}
}
