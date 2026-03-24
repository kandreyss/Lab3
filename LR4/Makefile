MAIN_CLASS = org.suai.lab4.Main
TEST_CLASS = org.suai.lab4.sortedlists.SortedIntegerListTest

SRC = $(shell find org -name "*.java")

build:
	mkdir -p out
	javac -d out $(SRC)

run:
	java -cp out $(MAIN_CLASS)

test:
	java -cp out $(TEST_CLASS)

clean:
	rm -rf out