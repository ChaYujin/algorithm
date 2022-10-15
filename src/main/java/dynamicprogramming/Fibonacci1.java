package dynamicprogramming;

/* TOP-DOWN 방식 */
public class Fibonacci1 {
    static int[] fiboArr = new int[100];
    public static void main(String[] args) {
        int answer = fibo(10);
        System.out.println("answer = "+answer);
    }
    private static int fibo(int n){
        if( n <= 1 ){
            return n;
        }

        if( fiboArr[n] > 0 ){
            return fiboArr[n];
        }

        fiboArr[n] = fibo(n-1) + fibo(n-2);
        return fiboArr[n];
    }
}
