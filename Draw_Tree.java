
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Draw_Tree {
    
    public int cnt_A, cnt_B, begin, end;
    public FontMetrics JF;
    public Main main = new Main();
    public Param prama = new Param();
    public ArrayList<Integer> e_x = new ArrayList<Integer>();
    public ArrayList<Integer> e_y = new ArrayList<Integer>();
    public ArrayList<Integer> o_x = new ArrayList<Integer>();
    public ArrayList<Integer> o_y = new ArrayList<Integer>();
    public ArrayList<Integer> A_ve = new ArrayList<Integer>();
    
    public Draw_Tree(){
        
        this.cnt_A = cnt_A;
        this.cnt_B = cnt_B;
        this.main = main;
        this.A_ve = A_ve;
        this.begin = begin;
        this.end = end;
    }
    
    
    public void Mang_cay(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        JF = g2d.getFontMetrics();
        Graphics2D g2d_A = (Graphics2D) g;
        int X_A = 0, Y_A = 400;
        int kcA = Param.WIDTH / (main.node.A.size() + 3);
        for (int i = 0; i < cnt_A; i++) {
            String s = String.valueOf(main.node.A.get(i));
            int k = JF.stringWidth(s);
            g2d_A.setColor(prama.COLOR_DATROI);
            int X = X_A += kcA;
            int Y = Y_A;
            g2d_A.fillOval(X - 6, Y, k + 15, 35);
            g2d_A.setColor(prama.COLOR_TRANG);
            g2d_A.setFont(prama.TAHOMA_P15);
            g2d_A.drawString(s, X + 1, Y + 22);
        }
    }
    
    public void Dung_cay (Graphics g){        
        Graphics2D g2d_tree = (Graphics2D) g;
        JF = g2d_tree.getFontMetrics();
        int X_B = 0, Y_B = 350;
        int kcB = Param.WIDTH / (main.node.Tree_A.size() + 3);
        if (cnt_A == main.node.A.size()){
                for (int i = 0; i < cnt_B; i++){
                    if ((i + 1) % 2 != 0) {
                        String s = String.valueOf(main.node.Tree_A.get(i));
                        int k = JF.stringWidth(s);
                        g2d_tree.setColor(prama.COLOR_XANHLA);
                        int X = X_B += kcB;
                        int Y = 350;
                        g2d_tree.fillOval(X - 6, Y, k + 15, 35);
                        g2d_tree.setColor(prama.COLOR_DEN);
                        g2d_tree.setFont(prama.TAHOMA_P15);
                        g2d_tree.drawString(s, X + 1, Y + 22);
                        o_x.add(X + k);
                        o_y.add(Y);
                    } 
                    else {
                        if ((i + 1) == ((i + 1) & -(i + 1))){                        
                            Y_B -= 50;
                            String s = String.valueOf(main.node.Tree_A.get(i));
                            int k = JF.stringWidth(s);
                            g2d_tree.setColor(Param.COLOR_DO);
                            int X = X_B += kcB;
                            int Y = Y_B;
                            g2d_tree.fillOval(X - 6, Y, k + 15, 35);
                            g2d_tree.setColor(prama.COLOR_DEN);
                            g2d_tree.setFont(prama.TAHOMA_P15);
                            g2d_tree.drawString(s, X + 1, Y + 22);
                            g2d_tree.setColor(Param.COLOR_CAM);
                            for (int j = 0; j < o_x.size(); j++){
                                
                                    g2d_tree.drawLine(o_x.get(j), o_y.get(j), X, Y);
                            }
                            if (e_x.size() == 1){
                                    g2d_tree.drawLine(e_x.get(0), e_y.get(0), X, Y);
                            }
                            e_x.clear();
                            e_y.clear();
                            o_x.clear();
                            o_y.clear();
                            o_x.add(X);
                            o_y.add(Y);
                        } 
                        else{
                            String s = String.valueOf(main.node.Tree_A.get(i));
                            int k = JF.stringWidth(s);
                            g2d_tree.setColor(prama.COLOR_CAM);
                            int X = X_B += kcB;
                            int Y = Y_B;
                            g2d_tree.fillOval(X - 6, Y, k + 15, 35);
                            g2d_tree.setColor(prama.COLOR_DEN);
                            g2d_tree.setFont(prama.TAHOMA_P15);
                            g2d_tree.drawString(s, X + 1, Y + 22);
                            g2d_tree.setColor(prama.COLOR_CAM);
                            g2d_tree.drawLine(o_x.get(o_x.size() - 1), o_y.get(o_y.size() - 1), X, Y);
                            o_x.remove(o_x.size() - 1);
                            o_y.remove(o_y.size() - 1);
                            e_x.add(X);
                            e_y.add(Y);
                            if (e_x.size() == 2) {
                                g2d_tree.setColor(prama.COLOR_CAM);
                                g2d_tree.drawLine(e_x.get(0), e_y.get(0), e_x.get(1), e_y.get(1));
                                o_x.add(e_x.get(1));
                                o_y.add(e_y.get(1));
                                e_x.clear();
                                e_y.clear();
                        }
                    }
                }
            }
        }
        if (cnt_B == main.node.Tree_A.size() && cnt_B > 0){
           
            g2d_tree.setColor(prama.COLOR_VANGDAM);
            g2d_tree.fillOval(X_B += kcB - 6, 50, 35, 35);
            g2d_tree.setColor(prama.COLOR_DEN);
            g2d_tree.setFont(prama.TAHOMA_P15);
            g2d_tree.drawString("R", X_B + 13, 72);
            g2d_tree.setColor(prama.COLOR_CAM);
            for (int j = 0; j < o_x.size(); j++) {
                    g2d_tree.drawLine(o_x.get(j), o_y.get(j), X_B + 5, 80);
            }
            for (int j = 0; j < e_x.size(); j++) {
                    g2d_tree.drawLine(e_x.get(j), e_y.get(j), X_B + 5, 80);
            }
        }
   }
    public void Draw_Update(Graphics g){
        Graphics2D g2d_tree = (Graphics2D) g;
        JF = g2d_tree.getFontMetrics();
        int X_A = 0, Y_A = 400;
        int kcA = Param.WIDTH / (main.node.Tree_A.size() + 3);
        for (int i = 0; i < cnt_A; i++) {
            String s = String.valueOf(main.node.A.get(i));
            int k = JF.stringWidth(s);
            g2d_tree.setColor(prama.COLOR_DATROI);
            int X = X_A += kcA;
            int Y = Y_A;
            g2d_tree.fillOval(X - 6, Y, k + 15, 35);
            g2d_tree.setColor(prama.COLOR_TRANG);
            g2d_tree.setFont(prama.TAHOMA_P15);
            g2d_tree.drawString(s, X + 1, Y + 23);
        }
    }
    public void Draw_Sum(Graphics g){
        Graphics2D g2d_tree = (Graphics2D) g;
        JF = g2d_tree.getFontMetrics();
        int X_A = 0, Y_A = 400;
        int kcA = Param.WIDTH / (main.node.Tree_A.size() + 3);
        for (int i = 0; i < main.node.A.size(); i++){
            String s = String.valueOf(main.node.A.get(i));
            int k = JF.stringWidth(s);
            if(i >= begin && i <= end){
                g2d_tree.setColor(prama.COLOR_DO);
                int X = X_A += kcA;
                int Y = Y_A;
                g2d_tree.fillOval(X - 6, Y, k + 15, 35);
                g2d_tree.setColor(prama.COLOR_DEN);
                g2d_tree.setFont(prama.TAHOMA_P15);
                g2d_tree.drawString(s, X + 1, Y + 23);
            }
            else{
                g2d_tree.setColor(prama.COLOR_DATROI);
                int X = X_A += kcA;
                int Y = Y_A;
                g2d_tree.fillOval(X - 6, Y, k + 15, 35);
                g2d_tree.setColor(prama.COLOR_TRANG);
                g2d_tree.setFont(prama.TAHOMA_P15);
                g2d_tree.drawString(s, X + 1, Y + 23);
            }
        }
    }        
}