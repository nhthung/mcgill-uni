import java.util.ArrayList;

public class BellmanFord{


	/**
	 * Utility class. Don't use.
	 */
	public class BellmanFordException extends Exception{
		private static final long serialVersionUID = -4302041380938489291L;
		public BellmanFordException() {super();}
		public BellmanFordException(String message) {
			super(message);
		}
	}

	/**
	 * Custom exception class for BellmanFord algorithm
	 *
	 * Use this to specify a negative cycle has been found
	 */
	public class NegativeWeightException extends BellmanFordException{
		private static final long serialVersionUID = -7144618211100573822L;
		public NegativeWeightException() {super();}
		public NegativeWeightException(String message) {
			super(message);
		}
	}

	/**
	 * Custom exception class for BellmanFord algorithm
	 *
	 * Use this to specify that a path does not exist
	 */
	public class PathDoesNotExistException extends BellmanFordException{
		private static final long serialVersionUID = 547323414762935276L;
		public PathDoesNotExistException() { super();}
		public PathDoesNotExistException(String message) {
			super(message);
		}
	}

    private int[] distances = null;
    private int[] predecessors = null;
    private int source;

    BellmanFord(WGraph g, int source) throws BellmanFordException{
        /* Constructor, input a graph and a source
         * Computes the Bellman Ford algorithm to populate the
         * attributes
         *  distances - at position "n" the distance of node "n" to the source is kept
         *  predecessors - at position "n" the predecessor of node "n" on the path
         *                 to the source is kept
         *  source - the source node
         *
         *  If the node is not reachable from the source, the
         *  distance value must be Integer.MAX_VALUE
         *
         *  When throwing an exception, choose an appropriate one from the ones given above
         */

        /* YOUR CODE GOES HERE */

    	ArrayList<Edge> e = g.getEdges();
    	distances = new int[g.getNbNodes()];
    	predecessors = new int[g.getNbNodes()];
    	predecessors[source] = -1;
    	this.source = source;
    	int cur = source;

    	for (int i = 0; i < distances.length; i++) {
    		distances[i] = Integer.MAX_VALUE;

    		/* Use -2 to indicate the node has no parents/ancestors */
    		predecessors[i] = -2;
    	}

    	distances[source] = 0;
    	predecessors[source] = -1;

    	for (int i = 0; i < g.getNbNodes() - 1; i++) {
    		int index = 0;
    		int [] nodes = new int[g.getNbNodes()];

    		cur = source;
    		nodes[0] = source;

    		/* Iterate through each node */
    		for (int j = 0; j < g.getNbNodes(); j++) {

    			for(int k = 0; k < e.size(); k++) {

    				if (e.get(k).nodes[0] == cur &&
    						distances[e.get(k).nodes[1]] > (distances[e.get(k).nodes[0]] + e.get(k).weight) &&
    						distances[e.get(k).nodes[0]] != Integer.MAX_VALUE) {

    					distances[e.get(k).nodes[1]] = (distances[e.get(k).nodes[0]] + e.get(k).weight);
    					predecessors[e.get(k).nodes[1]] = e.get(k).nodes[0];
    				}
    			}

    			/* Choose the next node */
    			for (int k = 0; k < e.size(); k++) {
    				int node1 = e.get(k).nodes[0];
    				int node2 = e.get(k).nodes[1];
    				boolean b1 = false;
    				boolean b2 = false;

    				for (int l = 0; l < nodes.length; l++) {
    					
    					if (nodes[l] == node1)
    						b1 = true;

    					if (nodes[l] == node2)
    						b2 = true;
    				}
    				
    				if (b1 && b2)
    					continue;

    				else if (!b1 && b2) {
    					nodes[index] = node1;
    					cur = node1;
    					index++;
    					break;
    				}
    				else {
    					nodes[index] = node2;
    					cur = node2;
    					index++;
    					break;
    				}
    			}
    		}
    	}

    	/* Negative cycle check */
    	for (int i = 0; i < distances.length; i++) {
    		try {
    			int t = distances[predecessors[i]];
    		}
    		catch (ArrayIndexOutOfBoundsException n) { continue; }

    		if (distances[i] != Integer.MAX_VALUE &&
    				distances[i] > (distances[predecessors[i]] + g.getEdge(predecessors[i], i).weight)) {
    			
    			throw new NegativeWeightException();
    		}
    	}
    }

    public int[] shortestPath(int destination) throws BellmanFordException{
        /*Returns the list of nodes along the shortest path from
         * the object source to the input destination
         * If not path exists an Exception is thrown
         * Choose appropriate Exception from the ones given
         */

        /* YOUR CODE GOES HERE (update the return statement as well!) */

        return null;
    }

    public void printPath(int destination){
        /*Print the path in the format s->n1->n2->destination
         *if the path exists, else catch the Error and
         *prints it
         */
        try {
            int[] path = this.shortestPath(destination);
            for (int i = 0; i < path.length; i++){
                int next = path[i];
                if (next == destination){
                    System.out.println(destination);
                }
                else {
                    System.out.print(next + "-->");
                }
            }
        }
        catch (BellmanFordException e){
            System.out.println(e);
        }
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        try{
            BellmanFord bf = new BellmanFord(g, g.getSource());
            bf.printPath(g.getDestination());
        }
        catch (BellmanFordException e){
            System.out.println(e);
        }

   }
}
