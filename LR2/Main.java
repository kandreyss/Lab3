public class Main {
    public static void main(String[] args) {
        Matrix m = new Matrix(2);
        Matrix result = m;
        m.setElement(1, 1, 0);
        m.setElement(0, 1, 1);
        m.setElement(1, 0, 1);

        System.out.println("Initial matrix:");
        System.out.println(m);

        for (int i = 1; i <= 10; i++) {
            System.out.println("Matrix to the power " + i + ":\n" + result);
            result = result.product(m);
        }
    }
}