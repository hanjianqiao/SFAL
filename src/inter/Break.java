package inter;
public class Break extends Stmt{
	Stmt stmt;
	public Break(){
		if(stmt.Enclosing == null) error("unenclosed break");
		stmt = Stmt.Enclosing;
	}
	public void gen(int b , int a){
		emit("\t\tJMP\tL"+stmt.after);
	}
}