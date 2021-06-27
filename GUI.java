
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.Timer;



public class GUI {
    
    private JFrame jframe;
    private JComboBox combo_khoitao, combo_update, combo_sum;
    private DefaultComboBoxModel<String> choose, choose_update, choose_sum;
    private JTextField textfiled;
    private int choose_khoi_tao = 0, choose_up = 0, choose_s = 0, Thuc_hien = 0;
    private JButton Khoi_tao, Run;
    public int draw_khoi_tao = 0, draw_update = 0, draw_sum = 0, begin, end;
    public Main main = new Main();
    public Param param = new Param();
    public ArrayList<Integer> A_ve = new ArrayList<Integer>();
    public  int time = 250;

    public class draw extends JPanel implements ActionListener {
        public Timer timer = new Timer(time, this);
        int cnt_A = 0, cnt_B = 0;
        public void run_draw(Graphics g){
                Graphics2D g2d = (Graphics2D) g;
                FontMetrics JF = g2d.getFontMetrics();
                Draw_Tree draw_tree = new Draw_Tree();
                draw_tree.main = main;
                draw_tree.cnt_A = cnt_A;
                draw_tree.cnt_B = cnt_B;
                draw_tree.prama = param;
                draw_tree.JF = JF;
                draw_tree.begin = begin;
                draw_tree.end = end;
                draw_tree.A_ve = A_ve;
                
            try{
                timer.start();
                if(draw_khoi_tao == 1 && draw_update == 0 && draw_sum == 0){
                    draw_tree.Mang_cay(g);
                }
                else{
                    if(draw_update != 0 && draw_sum == 0){
                        draw_tree.Draw_Update(g);
                    }
                    if(draw_sum != 0 && draw_update == 0){
                        repaint();
                        draw_tree.Draw_Sum(g);                         
                    }
                }
                
                if(draw_sum != 0 && draw_update == 0){
                    cnt_A = main.node.A.size();
                    cnt_B = main.node.Tree_A.size();          
                }
                draw_tree.Dung_cay(g);
   
            }catch (Exception e){
                System.out.println("Lỗi vẽ hình");
            }
        }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            run_draw(g);
            repaint();
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(draw_update != 0) time = 500;
            else time = 250;
            if(cnt_A < main.node.A.size()) cnt_A++;
            if(cnt_B < main.node.Tree_A.size() && cnt_A == main.node.A.size()) cnt_B++;
        }
    }
    public GUI(){
        try{
            init();
        }catch (Exception e){
            System.out.println("Lỗi màn hình");
        }
    }

        private void init() {
            try {
                
                jframe = new JFrame();
  
                //ImageIcon image = new ImageIcon("D:\\JAVA_SWING\\DO_AN_BIT\\ANH_DO_AN\\jframe.jpg");
                ImageIcon image = new ImageIcon(getClass().getResource("/images/jframe.jpg"));
                JLabel label = new JLabel(image);
                label.setBounds(150, 150, 1092, 512);
                label.setOpaque(true);
                label.setVisible(true);
                draw v = new draw();
                
                jframe.add(v);
                jframe.add(label);
                jframe.setTitle("BINARY INDEXEX TREE");
                jframe.getContentPane().setBackground(param.COLOR_TRANG_SUA);
                jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);           
                jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jframe.setLayout(null);
                jframe.setVisible(true);

                //khoi tao cay
                combo_khoitao = new JComboBox();
                combo_khoitao.setBackground(param.COLOR_TRANG_SUA);
                combo_khoitao.setOpaque(true);
                combo_khoitao.setForeground(param.COLOR_NUOCBIEN);
                combo_khoitao.setFont(Param.TAHOMA_P14);
                choose = new DefaultComboBoxModel<String>();
                choose.addElement("Nhập Node T.Công");
                choose.addElement("Nhập N Node");
                choose.addElement("Random N Node");
                combo_khoitao.setModel(choose);
                
                combo_khoitao.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        textfiled.setForeground(param.COLOR_XANHLA);
                        if(combo_khoitao.getSelectedItem().equals("Nhập Node T.Công")){
                            choose_khoi_tao = 1;
                            textfiled.setText("Nhập node: ");
                        }
                        if(combo_khoitao.getSelectedItem().equals("Nhập N Node")){
                            choose_khoi_tao = 2;
                            textfiled.setText("Nhập số lượng Node: ");
                        }
                        if(combo_khoitao.getSelectedItem().equals("Random N Node")){
                            choose_khoi_tao = 3;
                            Random rd = new Random();
                            String s = String.valueOf(rd.nextInt(15) + 1);
                            textfiled.setText("Khởi tạo Random: " + s);
                        }
                    }
                });
                
                //nut khoi tao cay
                Khoi_tao = new JButton("Khỏi tạo Cây");
                Khoi_tao.setBackground(param.COLOR_TRANG_SUA);
                Khoi_tao.setMnemonic(KeyEvent.VK_K);
                //Khoi_tao.setBorderPainted(false);
                Khoi_tao.setFont(param.TAHOMA_15);
                Khoi_tao.setForeground(param.COLOR_XANHLA);
                Khoi_tao.setOpaque(true);
                Khoi_tao.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String check = main.toString(textfiled.getText());
                        textfiled.setText(check);
                        if (textfiled.getText().equals("FALSE")){
                            JOptionPane.showMessageDialog(jframe, "Kiểm Tra Dữ Liệu!", "ERROR", JOptionPane.ERROR_MESSAGE);
                            choose_khoi_tao = 0;
                        }
                        try{
                            time = 200;
                            v.setBounds(0, 150, param.WIDTH, param.HIGHT);
                            v.setBackground(param.COLOR_TRANG_SUA);
                            v.setVisible(true);
                            label.setVisible(false);
                            draw_khoi_tao = 1;
                            draw_update = 0;
                            draw_sum = 0;
                            main.khoitao.s = "";
                            main.khoitao.n = 0;
                            
                            if (choose_khoi_tao == 1) {
                                v.repaint();
                                v.cnt_A = 0;
                                v.cnt_B = 0;
                                v.timer.restart();         
                                v.setLayout(new FlowLayout());
                                main.node.Delete();
                                main.khoitao.s = textfiled.getText();
                                main.phuong_thuc_khoi_tao = choose_khoi_tao;
                                main.Du_lieu_man_hinh();
                                textfiled.setForeground(param.COLOR_XANHLA);
                                textfiled.setText("Khởi Tạo Thành Công " + main.node.Output());
                                jframe.getContentPane().add(v);
                             

                        }
                            else{
                                if(choose_khoi_tao == 2){
                                    boolean kt = true;
                                    if(textfiled.getText().length() > 7) kt = false;
                                    if(kt == true){
                                        if(Integer.parseInt(textfiled.getText().toString()) <= 0 || Integer.parseInt(textfiled.getText().toString()) > 15){
                                            kt = false;
                                        }
                                    }
                                    if(kt == true){
                                        v.repaint();
                                        v.cnt_A = 0;
                                        v.cnt_B = 0;
                                        v.timer.start();
                                        v.timer.start();         
                                        v.setLayout(new FlowLayout());
                                        main.node.Delete();                    
                                        main.khoitao.n = Integer.parseInt(textfiled.getText());
                                        main.phuong_thuc_khoi_tao = choose_khoi_tao;
                                        main.Du_lieu_man_hinh();            
                                        textfiled.setForeground(param.COLOR_XANHLA);
                                        textfiled.setText("Khởi Tạo Thành Công " + main.node.Output()); 
                                        jframe.getContentPane().add(v);
                                    }
                                    else{
                                        JOptionPane.showConfirmDialog(jframe, "Kiểm Tra Dữ Liệu!", "ERROR", JOptionPane.ERROR_MESSAGE);
                                        v.repaint();
                                        v.cnt_A = 0;
                                        v.cnt_B = 0;
                                        textfiled.setForeground(param.COLOR_DO);
                                        main.node.Delete();
                                        textfiled.setText("Khởi tạo Tree thất bại");
                                    }
                                }
                                else{
                                    if(choose_khoi_tao == 3){
                                        boolean kt = true;
                                    if(textfiled.getText().length() > 7) kt = false;
                                    if(kt == true){
                                        if(Integer.parseInt(textfiled.getText().toString()) <= 0 || Integer.parseInt(textfiled.getText().toString()) > 15){
                                            kt = false;
                                        }
                                    }
                                    if(kt == true){
                                        v.repaint();
                                        v.cnt_A = 0;
                                        v.cnt_B = 0;
                                        v.timer.start();  
                                        v.setLayout(new FlowLayout());
                                        main.node.Delete();                    
                                        main.khoitao.n = Integer.parseInt(textfiled.getText());
                                        main.phuong_thuc_khoi_tao = choose_khoi_tao;
                                        main.Du_lieu_man_hinh();
                                        textfiled.setForeground(param.COLOR_XANHLA);
                                        textfiled.setText("Khởi Tạo Thành Công " + main.node.Output());
                                        jframe.getContentPane().add(v);
                                        
                                    }
                                    else{
                                        JOptionPane.showConfirmDialog(jframe, "Kiểm Tra Dữ Liệu!", "ERROR", JOptionPane.ERROR_MESSAGE);
                                        v.repaint();
                                        v.cnt_A = 0;
                                        v.cnt_B = 0;
                                        textfiled.setForeground(param.COLOR_DO);  
                                        main.node.Delete();
                                        textfiled.setText("Khởi tạo Tree thất bại");
                                    }
                                    }
                                }
                            }

                        }catch (Exception e1){
                            System.out.println("Lỗi  hàm khởi tạo cây");
                        }
                    }
                });
                // combo update
                combo_update = new JComboBox();
                combo_update.setBackground(param.COLOR_TRANG_SUA);
                combo_update.setForeground(param.COLOR_NUOCBIEN);
                combo_update.setOpaque(true);
                combo_update.setFont(Param.TAHOMA_P14);
                choose_update = new DefaultComboBoxModel<String>();
                choose_update.addElement("Cập nhật tất cả cây");
                choose_update.addElement("Cập nhật tại node N");
                choose_update.addElement("Cập nhật từ n đến m");
                combo_update.setModel(choose_update);
                combo_update.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Thuc_hien = 1;
                        textfiled.setForeground(param.COLOR_XANHLA);
                        if(combo_update.getSelectedItem().equals("Cập nhật tất cả cây")){
                            choose_up = 1;
                            textfiled.setText("Nhập Value: ");
                        }
                        if(combo_update.getSelectedItem().equals("Cập nhật tại node N")){
                            choose_up = 2;
                            textfiled.setText("Nhập Index Node và Value: ");
                        }
                        if(combo_update.getSelectedItem().equals("Cập nhật từ n đến m")){
                            choose_up = 3;
                            textfiled.setText("Nhập Index Node N M và Valude: ");
                        }                      
                    }
                });
                // combo_sum
                combo_sum = new JComboBox();
                combo_sum.setBackground(param.COLOR_TRANG_SUA);
                combo_sum.setForeground(param.COLOR_NUOCBIEN);
                combo_sum.setOpaque(true);
                combo_sum.setFont(Param.TAHOMA_P14);
                choose_sum = new DefaultComboBoxModel<String>();
                choose_sum.addElement("Sum từ 1 đến n");
                choose_sum.addElement("Sum từ n đến m");
                combo_sum.setModel(choose_sum);
                combo_sum.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Thuc_hien = 2;
                        textfiled.setForeground(param.COLOR_XANHLA);
                        if(combo_sum.getSelectedItem().equals("Sum từ 1 đến n")){
                            choose_s = 1;                          
                            textfiled.setText("Nhập Index Node N: ");
                        }
                        if(combo_sum.getSelectedItem().equals("Sum từ n đến m")){
                            choose_s = 2;                          
                            textfiled.setText("Nhập Index N và M: ");
                        }
                    }
                });
                
                //phan Function
                Run = new JButton("Run");
                Run.setBackground(param.COLOR_TRANG_SUA);
                Run.setMnemonic(KeyEvent.VK_R);
                Run.setFont(Param.TAHOMA_15);
                Run.setForeground(param.COLOR_CAM);
                //Run.setBorderPainted(false);
                Run.setOpaque(true);
                Run.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String check = main.toString(textfiled.getText());
                        textfiled.setText(check);
                        if (textfiled.getText().equals("FALSE")){
                            JOptionPane.showMessageDialog(jframe, "Kiểm Tra Dữ Liệu!", "ERROR", JOptionPane.ERROR_MESSAGE);
                            Thuc_hien = 0;
                        }
                        
                        try{
                            
                            if(Thuc_hien != 0){
                                if(Thuc_hien == 1){
                                    time = 1000;
                                    if(choose_up == 1){
                                        int value = Integer.parseInt(textfiled.getText());
                                        A_ve.clear();

                                        for (int i = 0; i < main.node.A.size(); i++) {
                                                A_ve.add(main.node.A.get(i));
                                        }
                                        main.function.update(main.node, value);                            
                                        draw_update = 1;
                                        draw_sum = 0;
                                        v.repaint();
                                        v.cnt_A = main.node.A.size();
                                        v.cnt_B = main.node.Tree_A.size();
                                        v.timer.start();
                                        textfiled.setText("Cập nhật lại toàn Cây với value " + value);
                                        jframe.getContentPane().add(v);
                                    }
                                    else{
                                        if(choose_up == 2){
                                            String[] t2 = textfiled.getText().split(",");
                                            int index = Integer.parseInt(t2[0]);
                                            int value = Integer.parseInt(t2[1]);
                                            A_ve.clear();                                    
                                            for (int i = 0; i < main.node.A.size(); i++) {
                                                    A_ve.add(main.node.A.get(i));
                                            }
                                            boolean oke = true;
                                            if(index <= 0 || index > main.node.Tree_A.size()) oke = false;
                                            if(oke == true){
                                                main.function.update(main.node, index, value);
                                                draw_update = 2;
                                                draw_sum = 0;
                                                v.repaint();
                                                v.cnt_A = main.node.A.size();
                                                v.cnt_B = main.node.Tree_A.size();
                                                v.timer.start();
                                                textfiled.setText("Cập nhật tại vị trí " + index + " với giá trị value " + value);
                                                jframe.getContentPane().add(v);
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(jframe, "Kiểm Tra Dữ Liệu!", "ERROR", JOptionPane.ERROR_MESSAGE);
                                                textfiled.setText("Nhập lại vị index và value: ");
                                            }
                                        }
                                        else {
                                            if(choose_up == 3){
                                                A_ve.clear();
                                                for (int i = 0; i < main.node.A.size(); i++) {
                                                        A_ve.add(main.node.A.get(i));
                                                }
                                                String[] t3 = textfiled.getText().split(",");
                                                int index1 = Integer.parseInt(t3[0]);
                                                int index2 = Integer.parseInt(t3[1]);
                                                int value = Integer.parseInt(t3[2]);
                                                boolean oke2 = true;
                                                if(index1 <= 0 || index2 <= 0 || index1 > index2 || index1 > main.node.Tree_A.size() || index2 > main.node.Tree_A.size()) oke2 = false;
                                                if(oke2 == true){
                                                    main.function.update(main.node, index1, index2, value);
                                                    draw_update = 3;
                                                    draw_sum = 0;
                                                    v.repaint();
                                                    v.cnt_A = main.node.A.size();
                                                    v.cnt_B = index1;
                                                    v.timer.start();
                                                    textfiled.setText("Cập nhật từ vị trí " + index1 + " tới vị trí " + index2 + " với giá trị value " + value);
                                                    jframe.getContentPane().add(v);
                                                }
                                                else{
                                                    JOptionPane.showMessageDialog(jframe, "Kiểm Tra Dữ Liệu!", "ERROR", JOptionPane.ERROR_MESSAGE);
                                                    textfiled.setText("Nhập lại vị n -> m và value: ");
                                                }
                                            }
                                        }
                                    }
                                }

                                if(Thuc_hien == 2){
                                    draw_update = 0;
                                    draw_sum = 1;
                                    if(choose_s == 1){
                                        int index = Integer.parseInt(textfiled.getText());
                                        boolean oke3 = true;
                                        if(index <= 0 || index > main.node.Tree_A.size()) oke3 = false;
                                        if(oke3 == true){
                                            begin = 0;
                                            end = index - 1;
                                            textfiled.setText("Sum Từ 1 Đến " + index + " = " + String.valueOf((main.function.sum(main.node, index))));
                                            draw_sum = 1;
                                            v.repaint();
                                            v.cnt_A = 0;
                                            v.cnt_B = 0;
                                            v.timer.restart();
                                            jframe.getContentPane().add(v);
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(jframe, "Kiểm Tra Dữ Liệu!", "ERROR", JOptionPane.ERROR_MESSAGE);
                                            textfiled.setText("Nhập lại vị n: ");
                                        }
                                    }
                                    else{
                                        if(choose_s == 2){
                                            String[] s = textfiled.getText().split(",");
                                            int index1 = Integer.parseInt(s[0]);
                                            int index2 = Integer.parseInt(s[1]); 
                                            boolean oke4 = true;
                                            if(index1 <= 0 || index2 <= 0 || index1 > index2 || index1 > main.node.Tree_A.size() || index2 > main.node.Tree_A.size()) oke4 = false;
                                            if(oke4 == true){
                                                begin = index1 - 1;
                                                end =  index2 - 1;
                                                textfiled.setText("Sum Từ " + index1 + " Đến " + index2 + " = " + String.valueOf( (main.function.sum(main.node, index1, index2))));
                                                draw_sum = 2;
                                                v.repaint();
                                                v.cnt_A = 0;
                                                v.cnt_B = 0;
                                                v.timer.restart();                                          
                                                jframe.getContentPane().add(v);
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(jframe, "Kiểm Tra Dữ Liệu!", "ERROR", JOptionPane.ERROR_MESSAGE);
                                                textfiled.setText("Nhập lại vị n -> m: ");
                                            }
                                        }
                                    }
                                }
                            }
                            else{
                                System.out.println("Không có chức năng này!");
                                Thuc_hien = 0;
                            }
                            
                        }catch(Exception e1){
                            System.out.println("Lôi hàm thực hiện");
                        }
                    }
                });
                textfiled = new JTextField("BINARY INDEXED TREE");
                textfiled.setBackground(param.COLOR_TRANG_SUA);
                textfiled.setOpaque(true);
                textfiled.setHorizontalAlignment(SwingConstants.CENTER);
                textfiled.setFont(param.TAHOMA_P16);
                textfiled.setForeground(param.COLOR_XANHLA);   
                
                JButton Back = new JButton("Trang chủ");
                Back.setMnemonic(KeyEvent.VK_T);
                Back.setBackground(param.COLOR_TRANG_SUA);
                Back.setFont(param.TAHOMA_15);
                Back.setForeground(param.COLOR_CAM);
                Back.setOpaque(true);
                Back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try{
                            v.setVisible(false);
                            label.setVisible(true);
                            jframe.add(label);
                            v.cnt_A = 0;
                            v.cnt_B = 0;
                            v.repaint();
                            A_ve.clear();
                            main.node.Delete();
                            textfiled.setText("BINARY INDEXED TREE");
                            
                        }catch (Exception e1) {
                            System.out.println("lỗi back");
                        }
                    }
                });
                JButton Thoat = new JButton("EXIT");
                Thoat.setMnemonic(KeyEvent.VK_E);
                Thoat.setBackground(param.COLOR_TRANG_SUA);
                Thoat.setFont(param.TAHOMA_15);
                Thoat.setForeground(param.COLOR_DO);
                Thoat.setOpaque(true);
                Thoat.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                      int ret = JOptionPane.showConfirmDialog(null, "Muốn có chắc là muốn thoát ?", "EXIT", JOptionPane.YES_NO_OPTION);
                      if(ret == JOptionPane.YES_OPTION) System.exit(0);
                    }
                });
                
                // hien thi nut bam tren man hinh
                
//                JPanel p1 = new JPanel();
//                p1.setLayout(new BoxLayout(p1, BoxLayout.X_AXIS));
//                p1.add(Back);
//                p1.add(combo_khoitao);
//                p1.add(Khoi_tao);
//                p1.add(combo_update);
//                p1.add(combo_sum);
//                p1.add(Run);
//                p1.add(Thoat);
//                p1.setVisible(true);
//                jframe.getContentPane().add(BorderLayout.NORTH,p1);
//                jframe.add(textfiled, BorderLayout.SOUTH);
//                
                
            GroupLayout groupLayout = new GroupLayout(jframe.getContentPane());
            groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
                    .createSequentialGroup().addContainerGap()
                    .addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
                        .createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(textfiled, GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
                                .addGroup(groupLayout.createSequentialGroup()                                      
                                    .addComponent(Back)
                                    .addGap(15)
                                    .addComponent(combo_khoitao, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(Khoi_tao)
                                    .addGap(15)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(combo_update, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                    .addGap(15)
                                    .addComponent(combo_sum, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(Run) 
                                    .addGap(15)
                                    .addComponent(Thoat)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))                                 
                                    .addGap(15))
                                    .addGroup(groupLayout.createSequentialGroup()
                                    .addGap(50))));

            groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
                .createSequentialGroup().addContainerGap()
                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
                    .createParallelGroup(Alignment.BASELINE)
                    .addComponent(Back)
                    .addComponent(combo_khoitao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(Khoi_tao)
                    .addComponent(combo_update, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_sum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(Run))
                    .addComponent(Thoat))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 217, Short.MAX_VALUE)       
                    .addComponent(textfiled, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                    .addGap(24));
                jframe.getContentPane().setLayout(groupLayout);

        
        } catch (Exception e) {
            System.err.println("lỗi hàm init");
        }
    }
    
}
