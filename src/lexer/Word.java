package lexer;

public class Word extends Token {
	public String lexeme = "";

	public Word(String s, int tag) {
		super(tag);
		lexeme = s;
	}

	public int style(){
		switch(tag){
			case Tag.ID:
				return 2;
			default:
				return -1;
		}
	}
	public String toString() {
		return lexeme;
	}

	public static final Word
		and		= new Word("AND", Tag.AND),	 		or 		= new Word("OR", Tag.OR),
		eq 		= new Word("EQU", Tag.EQ), 			ne 		= new Word("NEQU", Tag.NE),
		l		= new Word("LESS", Tag.L),			g		= new Word("GRT", Tag.G),
		le		= new Word("LESSE", Tag.LE), 		ge 		= new Word("GRTE", Tag.GE),
		minus 	= new Word("NEG", Tag.MINUS),	 	True 	= new Word("true", Tag.TRUE),
		False 	= new Word("false", Tag.FALSE),		temp 	= new Word("t", Tag.TEMP);
}
