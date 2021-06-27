
import java.util.Random;


public class Khoi_Tao {
    public String s;
    public int n;
    
    // chon phuong thuc khoi tao
    public void Chon(Node node, int chon){
        switch (chon){
            // khoi tao nhap thu cong
            case 1:
                node.Input(s);
                break;
            case 2:
                // khoi tao voi n node
                String s1 = "";
                for (int i = 1; i <= n; i++){
                    s1 += String.valueOf(i);
                    s1 += ",";
                }
                node.Input(s1);
                break;
            case 3:
                // khoi tao random
                Random random = new Random();
                String s2 = "";
                for (int i = 1; i <= n; i++){
                    s2 += String.valueOf(random.nextInt(n));
                    s2 +=",";
                }
                node.Input(s2);
                break;
            default:
                 System.out.println("Kiểm tra phần khởi tạo dữ liệu đầu vào !");
                 break;
        }
    }
}
