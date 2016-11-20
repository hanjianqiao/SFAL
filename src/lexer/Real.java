package lexer;

public class Real extends Token {
	public final float value;

	public Real(float v) {
		super(Tag.REAL);
		value = v;
	}
	public int style(){
		return 3;
	}

	public String toString() {
		return "" + value;
	}
}
