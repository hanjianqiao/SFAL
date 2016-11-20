package lexer;
import symbols.*;
import java.io.*;
import java.util.*;

public class Lexer {

	public static int line = 1;
	char peek = ' ';
	int flags = 0x00000000;
	Hashtable words = new Hashtable();

	void reserve(Word w) {
		words.put(w.lexeme, w);
	}

	public Lexer() {
		reserve(new Word("if", 		Tag.IF));
		reserve(new Word("else", 	Tag.ELSE));
		reserve(new Word("while",	Tag.WHILE));
		reserve(new Word("do", 		Tag.DO));
		reserve(new Word("break", 	Tag.BREAK));
		reserve(new Word("null",	Tag.NULL));
		reserve(new Word("this",	Tag.THIS));
		reserve(new Word("extends",	Tag.EXTENDS));
		reserve(new Word("for",		Tag.FOR));
		reserve(new Word("return",	Tag.RETURN));
		reserve(new Word("new",		Tag.NEW));
		reserve(new Word("NewArray",	Tag.NEWARRAY));
		reserve(new Word("Print",	Tag.PRINT));
		reserve(new Word("ReadInteger",	Tag.READINTEGER));
		reserve(new Word("ReaderLine",	Tag.READERLINE));
		reserve(new Word("out",	Tag.OUT));
		reserve(new Word("outnum",	Tag.OUTNUM));
		
		reserve(Word.True);		reserve(Word.False);
		reserve(Type.Int);		reserve(Type.Char);
		reserve(Type.Bool);		reserve(Type.Double);
		reserve(Type.Void);		reserve(Type.Class);
	}

	public void readch() throws IOException {
		peek = (char) System.in.read();
	}

	boolean readch(char c) throws IOException {
		readch();
		if (peek != c) {
			return false;
		}
		peek = ' ';
		return true;
	}

	public Token scan() throws IOException {
		for (;; readch()) {
			if (peek == ' ' || peek == '\t' || peek == 13)
				continue;
			else if (peek == '\n') {
				line += 1;
			} else {
				break;
			}
		}
		switch (peek) {
		case '&':
			if (readch('&')) return Word.and; else return new Token('&');
		case '|':
			if (readch('|')) return Word.or; else return new Token('|');
		case '=':
			if (readch('=')) return Word.eq; else return new Token('=');
		case '!':
			if (readch('=')) return Word.ne; else return new Token('!');
		case '<':
			if (readch('=')) return Word.le; else return Word.l;
		case '>':
			if (readch('=')) return Word.ge; else return Word.g;
		}

		if (Character.isDigit(peek)) {
			if(peek == '0'){
				int v = 0;
				readch();
				if(peek == 'X' || peek == 'x'){
					do {
						switch(peek){
						case '0':
							v = 16 * v + 0;
							break;
						case '1':
							v = 16 * v + 1;
							break;
						case '2':
							v = 16 * v + 2;
							break;
						case '3':
							v = 16 * v + 3;
							break;
						case '4':
							v = 16 * v + 4;
							break;
						case '5':
							v = 16 * v + 5;
							break;
						case '6':
							v = 16 * v + 6;
							break;
						case '7':
							v = 16 * v + 7;
							break;
						case '8':
							v = 16 * v + 8;
							break;
						case '9':
							v = 16 * v + 9;
							break;
						case 'A':
						case 'a':
							v = 16 * v + 10;
							break;
						case 'b':
						case 'B':
							v = 16 * v + 11;
							break;
						case 'c':
						case 'C':
							v = 16 * v + 12;
							break;
						case 'd':
						case 'D':
							v = 16 * v + 13;
							break;
						case 'e':
						case 'E':
							v = 16 * v + 14;
							break;
						case 'f':
						case 'F':
							v = 16 * v + 15;
							break;
						}
						readch();
					} while (Character.isDigit(peek) || peek >= 'A' && peek <= 'F'
									|| peek >='a' && peek <= 'f');
					return new Num(v);
				}
				else if(Character.isDigit(peek)){
					do {
						v = 8 * v + Character.digit(peek, 10);
						readch();
					} while (peek >= '0' && peek <= '7');
					return new Num(v);
				}
			}

			int v = 0;
			while (Character.isDigit(peek)){
				v = 10 * v + Character.digit(peek, 10);
				readch();
			}
			if (peek != '.')
				return new Num(v);
			float x = v;
			float d = 10;
			for (;;) {
				readch();
				if (!Character.isDigit(peek))
					break;
				x = x + Character.digit(peek, 10) / d;
				d = d * 10;
			}
			if(peek != 'e' && peek != 'E'){
				return new Real(x);
			}
			v = 0;
			char op = '+';
			readch();
			if(peek == '-' || peek == '+'){op = peek; readch();}
			do {
				v = 10 * v + Character.digit(peek, 10);
				readch();
			} while (Character.isDigit(peek));
			return new Real(Float.valueOf(String.valueOf(x) + 'E' + op + String.valueOf(v)));
		}

		if (Character.isLetter(peek)) {
			StringBuffer b = new StringBuffer();
			do {
				b.append(peek);
				readch();
			} while (Character.isLetterOrDigit(peek));
			String s = b.toString();
			if(s.equals("func")){
				for (;; readch()) {
					if (peek == ' ' || peek == '\t' || peek == 13)
						continue;
					else if (peek == '\n') {
						line += 1;
					} else {
						break;
					}
				}
				b = new StringBuffer();
				do {
					b.append(peek);
					readch();
				} while (Character.isLetterOrDigit(peek));
				s = b.toString();
				if(peek != '('){
					System.out.println("Expect (, but " + peek);
				}
				while(peek != ')'){
					readch();
				}
				readch();
			}
			Word w = (Word) words.get(s);
		//	System.out.println("Get " + s);
			if (w != null)
				return w;
			w = new Word(s, Tag.ID);
			words.put(s, w);
			
			
			return w;
		}
		Token tok = new Token(peek);
		peek = ' ';
		return tok;
	}
}
