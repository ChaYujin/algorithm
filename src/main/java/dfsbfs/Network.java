package dfsbfs;

import java.util.LinkedList;
import java.util.Queue;

/*
문제 설명
네트워크란 컴퓨터 상호 간에 정보를 교환할 수 있도록 연결된 형태를 의미합니다. 예를 들어, 컴퓨터 A와 컴퓨터 B가 직접적으로 연결되어있고, 컴퓨터 B와 컴퓨터 C가 직접적으로 연결되어 있을 때 컴퓨터 A와 컴퓨터 C도 간접적으로 연결되어 정보를 교환할 수 있습니다. 따라서 컴퓨터 A, B, C는 모두 같은 네트워크 상에 있다고 할 수 있습니다.

컴퓨터의 개수 n, 연결에 대한 정보가 담긴 2차원 배열 computers가 매개변수로 주어질 때, 네트워크의 개수를 return 하도록 solution 함수를 작성하시오.

제한사항
컴퓨터의 개수 n은 1 이상 200 이하인 자연수입니다.
각 컴퓨터는 0부터 n-1인 정수로 표현합니다.
i번 컴퓨터와 j번 컴퓨터가 연결되어 있으면 computers[i][j]를 1로 표현합니다.
computer[i][i]는 항상 1입니다.
입출력 예
n	computers	return
3	[[1, 1, 0], [1, 1, 0], [0, 0, 1]]	2
3	[[1, 1, 0], [1, 1, 1], [0, 1, 1]]	1
 */
public class Network {
	public static void main(String[] args) {
		Network solution = new Network();
		System.out.println( solution.solution(3, new int[][] {{1, 1, 0},{1, 1, 0},{0, 0, 1}} ) );
		System.out.println( solution.solution(3, new int[][] {{1, 1, 0},{1, 1, 1},{0, 1, 1}} ) );
	}

    public int solution(int n, int[][] computers) {
        return bfs(0, computers);
    }
    
    int bfs(int startIndex, int[][] computers) {
    	int[] visited = new int[computers.length];
    	int count = 0;

    	int idx;
		while( (idx = next(visited)) != -1 ) {
    		bfs(idx, computers, visited);
    		count++;
    	}
		return count;
    }
    
    private int next(int[] visited) {
    	for( int i=0; i<visited.length; i++ ) {
    		if( visited[i] == 0 ) {
    			return i;
    		}
    	}
		return -1;
	}

	void bfs(int startIndex, int[][] computers, int[] visited) {
    	Queue<Integer> queue = new LinkedList<Integer>();
    	
    	queue.add(startIndex);
		visited[startIndex] = 1;
    	
    	while( !queue.isEmpty() ) {
    		Integer idx = queue.remove();
    		
    		int[] linkArr = computers[idx];
    		
    		for( int i=0; i<linkArr.length; i++ ) {
    			if( i != idx && linkArr[i] == 1 && visited[i] != 1 ) {
    				visited[i] = 1;
    				queue.add(i);
    			}
    		}
    	}
    	return;
    }
}
