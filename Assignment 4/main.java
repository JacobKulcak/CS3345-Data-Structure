import java.util.LinkedList;
import java.util.Stack;

//======================================
public class Graph {
    private int V; // number of vertices
    
    private LinkedList<Integer>[] adjList; // list of adjacent vertices


    public Graph(int numVer) {
        V = numVer;
        adjList = new LinkedList[numVer];
        
        for (int i = 0; i < numVer; i++){
            adjList[i] = new LinkedList();
        }
    }


    // add an edge to graph
    public void addEdge(int x, int y) {
        adjList[x].add(y);
    }


    // recursive function for topSort
    private void topologicalSortUtil(int v, boolean visited[], Stack<Integer> stack) {
        // mark current node as visited
        visited[v] = true;

        // recur for all vertices adjacent to this vertex
        for (Integer neighbor : adjList[v]) {
            if (!visited[neighbor]){
                topologicalSortUtil(neighbor, visited, stack);
            }
        }

        // push current vertex to stack and store result
        stack.push(v);
    }


    // the topological sort function
    public void topologicalSort() {
        Stack<Integer> stack = new Stack<>();

        // mark all vertices as not visited
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;

        // call recursive function to store topSort starting from all vertices one by one
        for (int i = 0; i < V; i++) {
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        }

        // print stack contents
        System.out.println("Topological Sort: ");
        
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }


    //Main function creates graph structure, adds edges, and runs topSort
    public static void main(String[] args) {
        
        Graph g = new Graph(7);
        
        g.addEdge(5, 2);
        
        g.addEdge(2, 3);
        
        g.addEdge(3, 1);
        
        g.addEdge(6, 5);

        g.addEdge(5, 0);
        
        g.addEdge(4, 0);
        
        g.addEdge(4, 1);
        
        g.topologicalSort();
    }
}