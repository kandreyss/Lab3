public class Int {
	private int value;

	public Int() {
		value = 0;
	}

	public Int(int number) {
		value = number;
	}

	public void increment() {
		value++;
	}

	public void decrement() {
		value--;
	}

	public void add(Int n) {
		value += n.value;
	}

	public void substract(Int n) {
		value -= n.value;
	}

	public void multiply(Int n) {
		Int addend = new Int(value);
		for(int i = 0; i < n.value - 1; i++) {
			add(addend);
		}
	}

	public void pow(Int base) {
		Int multiplier = new Int(value);
		for(int i = 0; i < base.value - 1; i++) {
			multiply(multiplier);
		}
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}
}