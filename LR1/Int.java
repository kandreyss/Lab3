public class Int {
	private int value;

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
	
	@Override
	public String toString() {
		return Integer.toString(value);
	}
}
	
