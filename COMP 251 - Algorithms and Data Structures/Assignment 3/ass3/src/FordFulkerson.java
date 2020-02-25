import java.io.*;
import java.util.*;




public class FordFulkerson {


	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
		ArrayList<Integer> Stack = new ArrayList<Integer>();
		/* YOUR CODE GOES HERE */

		ArrayList<Edge> Path = new ArrayList<Edge>();
		ArrayList<Edge> e = graph.getEdges();
		Hashtable<Integer, Boolean> visited = new Hashtable<Integer, Boolean>();
		boolean found = false;
		boolean noChildren = true;
		
		Stack.add(source);

		while (!found) {
			if (Stack.isEmpty())
				return Stack;
			
			noChildren = true;

			if (Stack.get(Stack.size()-1) == destination)
				found = true;

			else if(Path.isEmpty() || Path.get(Path.size()-1).weight > 0) {
				visited.put(Stack.get(Stack.size()-1), new Boolean(true));
				
				for (int i = 0; i<e.size(); i++) {
					
					if (e.get(i).nodes[0] == Stack.get(Stack.size()-1) &&
							(e.get(i).weight > 0) &&
							visited.get(e.get(i).nodes[1]) == null) {
						
						Path.add(e.get(i));
						Stack.add(e.get(i).nodes[1]);
						noChildren = false;
						break;
					}
				}

				if (noChildren) {
					if (Path.isEmpty()) {
						Stack.clear();
						break;
					}
					Path.remove(Path.size() -1);
					Stack.remove(Stack.size()-1);
				}
			}

			else if (Path.get(Path.size()-1).weight <= 0) {
				visited.put(Stack.get(Stack.size()-1), new Boolean(true));
				Path.remove(Path.size() -1);
				Stack.remove(Stack.size()-1);
			}
		}
		return Stack;
	}



	public static void fordfulkerson(Integer source, Integer destination, WGraph graph, String filePath){
		String answer="";
		String myMcGillID = "260793376"; //Please initialize this variable with your McGill ID
		int maxFlow = 0;

		/* YOUR CODE GOES HERE */

		WGraph copyGraph = new WGraph(graph);
		WGraph rGraph = new WGraph(graph);

		for (Edge e : copyGraph.getEdges()) {
			e.weight = 0;
		}

		while (true) {

			ArrayList<Integer> Path = pathDFS(source, destination, rGraph);
			Integer bottleneck = Integer.MAX_VALUE;

			if (Path.isEmpty())
				break;

			/* Find bottleneck */
			for (int i = 0; i < Path.size() - 1; i++) {
				Edge e = rGraph.getEdge(Path.get(i), Path.get(i + 1));

				if (e.weight < bottleneck)
					bottleneck = e.weight;
			}

			/* Augment edges */
			for (int i = 0; i < Path.size() - 1; i++) {
				int node1 = Path.get(i);
				int node2 = Path.get(i + 1);
				Edge e = copyGraph.getEdge(node1, node2);

				if (e!= null && graph.getEdge(node1, node2) != null) { /* Forward edge */
					e.weight+=bottleneck;
				}
				else {
					if(e == null)
						copyGraph.getEdge(node2, node1).weight -= bottleneck;
				}
			}

			/* Update residual graph */
			for(int i = 0; i < Path.size() - 1; i++) {
				int node1 = Path.get(i);
				int node2 = Path.get(i+1);
				Edge e = copyGraph.getEdge(node1, node2);
				Edge originalEdge = graph.getEdge(node1, node2);

				if (originalEdge == null) {
					e = copyGraph.getEdge(node2, node1);
					originalEdge = graph.getEdge(node2, node1);
					if(e!= null)
					{
						rGraph.setEdge(node2, node1, (originalEdge.weight - e.weight));
						rGraph.setEdge(node1, node2, e.weight);
					}
					continue;
				}

				else if (e.weight <= originalEdge.weight) {

					rGraph.setEdge(node1, node2, (originalEdge.weight - e.weight)); /* Set forward edge */
				}

				if (e.weight>0) {
					try {
						rGraph.addEdge(new Edge(node2, node1, e.weight)); /* Add backward edge */
					}
					catch (Exception exception) {
						rGraph.setEdge(node2, node1, e.weight); /* Set backward edge */
					}
				}
			}
			maxFlow += bottleneck;
		}

		graph = copyGraph;
		
		if (copyGraph.getNbNodes() == 0)
			maxFlow = -1;

		answer += maxFlow + "\n" + graph.toString();
		writeAnswer(filePath+myMcGillID+".txt",answer);
		System.out.println(answer);
	}


	public static void writeAnswer(String path, String line){
		BufferedReader br = null;
		File file = new File(path);
		// if file doesnt exists, then create it

		try {
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(line+"\n");
		bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	 public static void main(String[] args){
		 String file = args[0];
		 File f = new File(file);
		 WGraph g = new WGraph(file);
		 fordfulkerson(g.getSource(),g.getDestination(),g,f.getAbsolutePath().replace(".txt",""));
	 }
}
