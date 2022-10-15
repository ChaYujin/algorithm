package dynamicprogramming;

/* DOWN-UP 방식 */
public class Fibonacci2 {
    static int[] fiboArr = new int[100];
    public static void main(String[] args) {
        int answer = fibo(10);
        System.out.println("answer = "+answer);
    }
    private static int fibo(int n){
        fiboArr[0] = 1;
        fiboArr[1] = 1;

        for( int i=2; i<n; i++ ){
            fiboArr[i] = fiboArr[i-1]+fiboArr[i-2];
        }

        return fiboArr[n-1];
    }
}
