package inter;
import lexer.*;
import symbols.*;
public class Access extends Op{
	public final int style = 7;
	public Id array;
	public Expr index;
	public Access(Id a, Expr i, Type p){
		super(new Word("[]", Tag.INDEX), p);
		com = true;
		array = a;
		index = i;
	}
	public int style(){
		return 7;
	}
	public Expr gen(){return new Access(array, index.reduce(), type);}
	public void jumping(int t, int f){emitjumps(reduce().toString(), t, f);}
	public String toString(){
		String str = "";
		if(!array.com){
			switch(array.style()){
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
					str += "\tArith expr1, style :" + array.style;
			}
		}
		str += array.toString() + "\r\n";
		if(!index.com){
			switch(index.style()){
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
					str += "\tArith expr1, style :" + array.style;
			}
		}
		str += index.toString();
		
		str += "\r\n\t\tADD\r\n\t\tPUSHV\r\n\t\tLSTORE\tt0\r\n\t\tPOP\t2\r\n\t\tLPUSHP\tt0";
		
		return str;
	}
}
