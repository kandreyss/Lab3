OUT=out
JAVA_FILES=org/suai/lab3/*.java
MAIN_CLASS=org.suai.lab3.Main

build:
	rm -rf $(OUT)
	javac -d $(OUT) $(JAVA_FILES)

run:
	@rm -rf $(OUT)
	@javac -d $(OUT) $(JAVA_FILES)
	@java -cp $(OUT) $(MAIN_CLASS)

clean:
	rm -rf $(OUT)