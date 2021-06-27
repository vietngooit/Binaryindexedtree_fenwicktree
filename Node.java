
import java.util.ArrayList;


public class Node {
    public ArrayList <Integer> Tree_A = new ArrayList<>();
    public ArrayList <Integer> In_A = new ArrayList<>();
    public ArrayList <Integer> Sum_A = new ArrayList<>();
    public ArrayList <Integer> A = new  ArrayList<>();
    
    public void Input(String s){
        Tree_A.add(0);
        Sum_A.add(0);
        A.add(0);
        String [] a;
        a = s.split(",");
        for (int i = 0; i < a.length; i++){
            A.add(Integer.parseInt(a[i]));
        }
        for (int i = 1; i < A.size(); i++){
            Sum_A.add(A.get(i) + Sum_A.get(i - 1));
            Tree_A.add(Sum_A.get(i) - Sum_A.get((i - (i & -i) + 1) - 1));
        }
        A.remove(0);
        Tree_A.remove(0);
    }
    
    public void Delete(){
        A.clear();
        Sum_A.clear();
        Tree_A.clear();
        In_A.clear();  
    }
    
    public String Output(){
        return "BIT" + "[" + A.size() + "] = "  + Tree_A;
    }
}
