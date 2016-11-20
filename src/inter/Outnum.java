package inter;
public class Outnum extends Stmt{
	String str;
	public Outnum(String str){
		this.str = str;
	}
	public void gen(int b, int a){
		emit("\t\tPUSHP\t" + str + "\r\n\t\tOUTNUM");
	}
	public String toString(){
		return "\t\tPUSHP\t" + str + "\r\n\t\tOUTNUM";
	}
}