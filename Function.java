
public class Function {
    
    // up tai p
    public void update(Node node, int p, int x){
        int index = p;
        while(index <= node.Tree_A.size()){
            node.Tree_A.add(index - 1, node.Tree_A.get(index - 1) + x);
            node.Tree_A.remove(index);
            index += index & (-index);
        }
        node.A.add(p - 1, node.A.get(p - 1) + x);
        node.A.remove(p);
    }
    // up tu n -> m
    public void update(Node node, int n, int m, int x){
        for (int i = n - 1; i <= m; i++){
            for (int j = i + 1; j <= node.Tree_A.size(); j += j & -j){
                node.Tree_A.add(j - 1, node.Tree_A.get(j - 1) + x);
                node.Tree_A.remove(j);
            }
        }
        for (int i = n; i <= m; i++){
            node.A.add(i - 1, node.A.get(i - 1) + x);
            node.A.remove(i);
        }
    }
    // up all tree
    public void update(Node node, int x){
        for (int i = 0; i < node.Tree_A.size(); i++){
            for (int j = i + 1; j <= node.Tree_A.size(); j += j & -j){
                node.Tree_A.add(j - 1, node.Tree_A.get(j - 1) + x);
                node.Tree_A.remove(j);
            }
        }
        for (int i = 1; i <= node.A.size(); i++){
            node.A.add(i - 1, node.A.get(i - 1) + x);
            node.A.remove(i);
        }
    }
    
    // phan tinh Sum
    // sum tu 1 -> n
    public int sum(Node node, int n){
        int res = 0;
        for (int i = n; i != 0; i -=  i & - i) res += node.Tree_A.get(i - 1);
        return res;
    }
    
    // sum tu n -> m
    public int sum(Node node, int n, int m){
       return sum(node,m) - sum(node,n - 1);
    }
}
