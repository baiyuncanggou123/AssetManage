import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
public class AssetDepreciationUI extends JFrame {
    public AssetDepreciationUI(){
        setTitle("资产折旧计算");
        setSize(1000,600);
        setLocationRelativeTo(null);
        // 创造面板
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2,10,10));
        JLabel assetidLabel = new JLabel("请输入你要计算的 资产ID:");
        JTextField assetidField = new JTextField();
        JLabel Label1 = new JLabel("选择1 年限平均法");
        JLabel Label2 = new JLabel("选择2 双倍余额递减法");
        JButton Button1 = new JButton("choose 1");
        JButton Button2 = new JButton("choose 2");
        panel.add(assetidLabel);
        panel.add(assetidField);
        panel.add(Label1);
        panel.add(Label2);
        panel.add(Button1);
        panel.add(Button2);
        add(panel);

        LocalDate currentDate = LocalDate.now();
        String sb2 = currentDate.toString();
        LocalDate date2 = LocalDate.parse(sb2.toString(), DateTimeFormatter.ISO_DATE);
        int year1 = date2.getYear();
        int month1 = date2.getMonthValue();
        int day1 = date2.getDayOfMonth();

        AssetDAO assetDAO = new AssetDAO();

        Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int year =0;
                int month =0;
                int day =0;
                double cost =0;
                try{
                    String sb1 = assetidField.getText();
                    LocalDate date1;
                    if(sb1==null||sb1.isEmpty()){
                        JOptionPane.showMessageDialog(null, "错误输入 ");
                        System.exit(0);
                    }else{
                        int k = Integer.parseInt(sb1);
                        cost = assetDAO.find123(k);
                        String sb = assetDAO.find1234(k);
                        if(sb!=null&&!sb.isEmpty()){
                            date1 = LocalDate.parse(sb.toString(), DateTimeFormatter.ISO_DATE);
                            year = date1.getYear();
                            month = date1.getMonthValue();
                            day = date1.getDayOfMonth();
                        }else{
                            JOptionPane.showMessageDialog(null, "wrong input "+sb);
                            System.exit(0);
                        }
                    }
                }
                catch (SQLException f){
                    JOptionPane.showMessageDialog(null, "查找失败: " + f.getMessage());
                    System.exit(0);
                }
                int jiange = (year1-year)*365+(month1-month)*30+(day1-day);
                DepreciationCalculator depreciationCalculator = new DepreciationCalculator();
                double finalCost = cost;
                DepreciationSearch depreciationSearch = new DepreciationSearch();
                double salvage = 0;
                try {
                    salvage = depreciationSearch.search(Integer.parseInt(assetidField.getText()));
                } catch (SQLException e1) {
                    throw new RuntimeException(e1);
                }
                double finalSalvage = salvage*finalCost;
                double ife = depreciationSearch.getlife(salvage,jiange);
                if(ife<0){
                    JOptionPane.showMessageDialog(null, "超出使用年限");
                    System.exit(0);
                }
                double result = depreciationCalculator.straight(finalCost, finalSalvage,ife);
                JOptionPane.showMessageDialog(null, "Choose1 : 资产折旧为 "+result + "剩余时间: "+ife+"年");
                JOptionPane.showMessageDialog(null, cost);
            }
        });

        Button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int year =0;
                int month =0;
                int day =0;
                double cost =0;
                try{
                    String sb1 = assetidField.getText();
                    LocalDate date1;
                    if(sb1==null||sb1.isEmpty()){
                        JOptionPane.showMessageDialog(null, "错误输入 ");
                        System.exit(0);
                    }else{
                        int k = Integer.parseInt(sb1);
                        cost = assetDAO.find123(k);
                        String sb = assetDAO.find1234(k);
                        if(sb!=null&&!sb.isEmpty()){
                            date1 = LocalDate.parse(sb.toString(), DateTimeFormatter.ISO_DATE);
                            year = date1.getYear();
                            month = date1.getMonthValue();
                            day = date1.getDayOfMonth();
                        }else{
                            JOptionPane.showMessageDialog(null, "wrong input");
                            System.exit(0);
                        }
                    }
                }
                catch (SQLException f){
                    JOptionPane.showMessageDialog(null, "查找失败: " + f.getMessage());
                    System.exit(0);
                }

                int jiange = (year1-year)*365+(month1-month)*30+(day1-day);
                DepreciationCalculator depreciationCalculator = new DepreciationCalculator();
                double finalCost = cost;
                DepreciationSearch depreciationSearch = new DepreciationSearch();
                double salvage = 0;
                try {
                    salvage = depreciationSearch.search(Integer.parseInt(assetidField.getText()));
                } catch (SQLException e1) {
                    throw new RuntimeException(e1);
                }
                double finalSalvage = salvage*finalCost;
                double ife = depreciationSearch.getlife(salvage,jiange);
                if(ife<0){
                    JOptionPane.showMessageDialog(null, "超出使用年限");
                    System.exit(0);
                }
                double result = depreciationCalculator.straight(finalCost, finalSalvage,ife);
                JOptionPane.showMessageDialog(null, "2 : 资产折旧为 "+result + "剩余时间 "+ife);
            }
        });
    }
}
