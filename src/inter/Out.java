package inter;
public class Out extends Stmt{
	String str;
	public Out(String str){
		this.str = str;
	}
	public void gen(int b, int a){
		emit("\t\tPUSHP\t" + str + "\r\n\t\tOUT");
	}
	public String toString(){
		return "\t\tPUSHP\t" + str + "\r\n\t\tOUT";
	}
}