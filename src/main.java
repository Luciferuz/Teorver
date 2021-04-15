public class main {

    public void main() {
        int m = 5;
        int n = 3;
        int k = 3;
        int tests = 10000;
        int good = 0;

        for (int i = 0; i < tests; i++) {

        }


        System.out.println("Аналитическое решение = " + (n - k * m) / (n + m));
        System.out.println("Моделирование = " + good / tests);
    }

}