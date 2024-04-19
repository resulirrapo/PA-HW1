# Exemplu de Makefile pentru soluții scrise în Java.

.PHONY: build clean

build: P1.class P2.class P3.class P4.class

# Nu compilați aici, nici măcar ca dependențe de reguli.
run-p1:
	java Servere
run-p2:
	java Colorare
run-p3:
	java Compresie
run-p4:
	java P4

# Schimbați numele surselor și ale binarelor (peste tot).
P1.class: Servere.java
	javac $^
P2.class: Colorare.java
	javac $^
P3.class: Compresie.java
	javac $^
P4.class: P4.java
	javac $^

# Vom șterge fișierele bytecode compilate.
clean:
	rm -f *.class
