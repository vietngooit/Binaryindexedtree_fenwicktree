
public class Main {
    public Node node = new Node();
    public Function function = new Function();
    public Khoi_Tao khoitao = new Khoi_Tao();
    public int phuong_thuc_khoi_tao;
    
    // dữ liệu đầu vào
    public void Du_lieu_man_hinh(){
        khoitao.Chon(node, phuong_thuc_khoi_tao);
    }
    // kiểm tra dữ liệu nhập
    public String toString(String s){
        return Input.check_input(s);
    }
}
