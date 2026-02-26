public class Main {
    public static void main(String[] args) {
		Int a = new Int();
		a.increment();
		for(int i = 0; i < 10; i++) {
			a.add(a);
		}

		Int b = new Int();
		for (int i = 0; i < 24; i++) {
			b.increment();
		}
		a.substract(b);
		System.out.println(a);

		Int number = new Int(2);
		Int base = new Int(3);
		number.pow(base);
		System.out.println(number);
    }
}