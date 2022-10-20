package dynamicprogramming;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;

/*
문제 설명
아래와 같이 5와 사칙연산만으로 12를 표현할 수 있습니다.

12 = 5 + 5 + (5 / 5) + (5 / 5)
12 = 55 / 5 + 5 / 5
12 = (55 + 5) / 5

5를 사용한 횟수는 각각 6,5,4 입니다. 그리고 이중 가장 작은 경우는 4입니다.
이처럼 숫자 N과 number가 주어질 때, N과 사칙연산만 사용해서 표현 할 수 있는 방법 중 N 사용횟수의 최솟값을 return 하도록 solution 함수를 작성하세요.

제한사항
N은 1 이상 9 이하입니다.
number는 1 이상 32,000 이하입니다.
수식에는 괄호와 사칙연산만 가능하며 나누기 연산에서 나머지는 무시합니다.
최솟값이 8보다 크면 -1을 return 합니다.
입출력 예
N	number	return
5	12	4
2	11	3
입출력 예 설명
예제 #1
문제에 나온 예와 같습니다.

예제 #2
11 = 22 / 2와 같이 2를 3번만 사용하여 표현할 수 있습니다.

출처
※ 공지 - 2020년 9월 3일 테스트케이스가 추가되었습니다.
* */
public class P1 {
    static Map<Integer, Set<Integer>> numSet = new HashMap<>();
    public static void main(String[] args) {
        int result = solution(5, 12);
        System.out.println("solution result = "+result);
    }

    //TOP-DOWN
    public static int solution(int N, int number) {
        for(int i=1; i<=8; i++){
            Set<Integer> newSet = new HashSet<>();
            for( int j=0; j<i/2+1; j++ ){
                int count1 = i-j;
                int count2 = i-count1;
                if( count1 == i ){
                    //10진수로 계산하기
                    newSet.add( calculateOctalNumber(count1,N) );
                }else{
                    if( i==1 ){
                        continue;
                    }

                    Set<Integer> set1 = numSet.get(count1);
                    Set<Integer> set2 = numSet.get(count2);
                    newSet.addAll( calculateNums( set1 , set2 ) );
                }

                if( newSet.contains(number) ){
                    return i;
                }
            }
            numSet.put( i , newSet );
        }
        return -1;
    }

    private static Integer calculateOctalNumber(int count1, int N) {
        int result = -1;
        for( int i=0; i<count1; i++ ){
            if( result == -1 && i == 0 ){
                result = N;
            }else{
                result *= 10;
                result += N;
            }
        }
        return result;
    }

    private static Set<Integer> calculateNums(Set<Integer> set1, Set<Integer> set2) {

        Set<Integer> newset = new HashSet<>();
        for(Integer num1 : set1){
            for(Integer num2 : set2){
                // original version
                /*
                newset.add(num1 + num2);
                newset.add(num1 - num2);
                newset.add(num1 * num2);
                newset.add(num2 - num1);

                if( num2 != 0 )
                    newset.add(num1 / num2);

                if( num1 != 0 )
                    newset.add(num2 / num1);
                 */

                // enum + functional interface version
                for(Operator o : Operator.values()){
                    newset.add(o.calc(num1,num2));
                }
            }
        }
        return newset;
    }

    enum Operator{
        ADD((a,b) -> a+b),
        SUB((a,b) -> a-b),      BSUB((a,b) -> b-a),
        MUL((a,b) -> a*b),
        DIV((a,b) -> a/b),      BDIV((a,b) -> b/a)
        ;

        private BinaryOperator<Integer> bo;
        private Operator(BinaryOperator<Integer> bo) {this.bo = bo;}
        public int calc(int a, int b){
            try {
                return bo.apply(a,b);
            }catch (Exception e){
                return 0;
            }
        }
    }
}

