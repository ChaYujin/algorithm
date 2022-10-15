package dynamicprogramming;

import java.util.Arrays;

/**
 * 최소 갯수의 잔돈을 거슬러 주기
 */
public class CoinChanging {
    static int[][] coinCounts;
    public static void main(String[] args) {
        int minCount = solution( new int[]{3,4,1,7} , 14 );
        System.out.println("remaining coin min count = "+minCount);
    }

    private static int solution(int[] currency, int remaining) {
        Arrays.sort(currency);

        coinCounts = new int[currency.length][remaining];
        for( int i=0; i<currency.length; i++ ){
            int currencyUnit = currency[i];

            System.out.println();
            System.out.print(currencyUnit+" : ");

            for( int j=1; j<=remaining; j++){
                if( i > 0 && j < currencyUnit ){
                    coinCounts[i][j-1] =coinCounts[i-1][j-1];
                    System.out.print(coinCounts[i][j-1]+" ");
                    continue;
                }

                int count = (int)j/currencyUnit;
                int remainder = j%currencyUnit;

                if( remainder > 0 && i >= 1){
                    count += coinCounts[i-1][remainder-1];
                }

                coinCounts[i][j-1] = count;
                System.out.print(count+" ");
            }
        }
        System.out.println();

        return coinCounts[currency.length-1][remaining-1];
    }
}
