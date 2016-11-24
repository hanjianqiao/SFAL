all:
	javac inter/*.java -Xlint:unchecked
	javac lexer/*.java -Xlint:unchecked
	javac symbols/*.java -Xlint:unchecked
	javac parser/*.java -Xlint:unchecked
	javac main/Main.java -Xlint:unchecked

run:
	make all
	java main.Main < in.txt >out.txt
