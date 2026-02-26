public class Main {
    public static void main(String[] args) {
        Matrix m = new Matrix(2);
        m.setElement(0, 1, 1);
		m.setElement(1, 0, 1);
		m.setElement(1, 1, 0);

        Matrix result = m;

        System.out.println("Initial matrix:");
		System.out.println(m);

        for (int i = 1; i <= 10; i++) {
            System.out.println("Matrix to the power " + i + ":\n" + result);
            result = result.product(m);
        }

		Matrix mV = new Matrix(2);
		mV.setElement(0, 0, 1);
		mV.setElement(0, 1, 2);
		mV.setElement(1, 0, 3);
		mV.setElement(1, 1, 4);
		mV.doMiracle(0);
		System.out.println("Vertical:");
		System.out.println(mV);

		Matrix mH = new Matrix(3);
		mH.setElement(0, 1, 2);
		mH.setElement(1, 0, 3);
		mH.setElement(1, 1, 4);
		mH.setElement(0, 0, 1);
		mH.setElement(0, 2, 100);
		mH.setElement(2, 1, -100);
		System.out.println(mH);
		mH.doMiracle(0);
		System.out.println("Horizontal:");
		System.out.println(mH);
		
    }
}