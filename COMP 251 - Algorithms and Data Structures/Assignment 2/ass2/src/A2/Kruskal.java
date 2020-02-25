package A2;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Kruskal{

    public static WGraph kruskal(WGraph g){

        /* Fill this method (The statement return null is here only to compile) */
    	final String FILE_NAME = "student_mst.txt";
    	
    	PrintWriter mstFile;
    	DisjointSets p;
        WGraph mst;
        
        int numNodes;
        
        mstFile = null;
        mst = null;
        
        try {
        	numNodes = g.getNbNodes();
            p = new DisjointSets(numNodes);
        	mstFile = new PrintWriter(FILE_NAME, "UTF-8");
        	
        	mstFile.println(numNodes);
        	
        	for (Edge e: g.listOfEdgesSorted()) {
            	int u = e.nodes[0],
            		v = e.nodes[1],
            		w = e.weight;
            	
            	if (IsSafe(p, e)) {
            		p.union(u, v);
            		mstFile.println(u + " " + v + " " + w);
            	}
        	}
        	mstFile.close();
        	
        	mst = new WGraph(FILE_NAME);
        }
        catch (IOException e) {
        	System.err.println("MST file creation failed.");
        	return null;
        }
        finally { mstFile.close(); }
 
        return mst;
    }

    public static Boolean IsSafe(DisjointSets p, Edge e){

        /* Fill this method (The statement return 0 is here only to compile) */
    	int u = e.nodes[0],
    		v = e.nodes[1];
    	
    	return p.find(u) != p.find(v);
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        WGraph t = kruskal(g);
        System.out.println(t);

   } 
}
