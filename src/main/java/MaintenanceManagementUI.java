import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class MaintenanceManagementUI extends Frame {
    private MaintenanceRecordService  maintenanceRecordService = new MaintenanceRecordService();
    public MaintenanceManagementUI(){
        setTitle("维修登记模块");
        setSize(1000,600);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7,2,10,10));
        JLabel assetidLabel = new JLabel("增加/修改 资产ID");
        JTextField assetidfield = new JTextField();
        JLabel maintenancedate = new JLabel("增加/修改 维修日期");
        JTextField maintenancedatefield = new JTextField();
        JLabel maintenanceCost = new JLabel("增加/修改 维修花费");
        JTextField maintenacecostfield = new JTextField();
        JLabel description = new JLabel("增加/修改 描述");
        JTextField descriptionfield = new JTextField();
        JLabel maintenanceID = new JLabel("删除/修改 维修ID");
        JTextField maintenanceIDfield = new JTextField();
        JButton addButton = new JButton("添加维修记录");
        JButton viewButton = new JButton("查看所有维修记录");
        JButton deleteButton = new JButton("删除某个维修记录");
        JButton findButton = new JButton("修改某个维修记录");
        panel.add(assetidLabel);
        panel.add(assetidfield);
        panel.add(maintenanceCost);
        panel.add(maintenacecostfield);
        panel.add(maintenancedate);
        panel.add(maintenancedatefield);
        panel.add(description);
        panel.add(descriptionfield);
        panel.add(maintenanceID);
        panel.add(maintenanceIDfield);
        panel.add(addButton);
        panel.add(viewButton);
        panel.add(deleteButton);
        panel.add(findButton); // 添加面板到窗口
        add(panel);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MaintenanceRecord maintenanceRecord = new MaintenanceRecord();
                maintenanceRecord.setAssetID(Integer.parseInt(assetidfield.getText()));
                maintenanceRecord.setDesripiton(descriptionfield.getText());
                maintenanceRecord.setMaintenanceCost(Double.parseDouble(maintenacecostfield.getText()));
                maintenanceRecord.setMaintenanceDate(maintenancedatefield.getText());
                MaintenanceRecordDAO maintenanceRecordDAO = new MaintenanceRecordDAO();
                try {
                    int k = maintenanceRecordDAO.assetid(Integer.parseInt(assetidfield.getText()));
                    if(k!=-1){
                        JOptionPane.showMessageDialog(null,"添加失败，asset已经在维修");
                        System.exit(0);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try{
                    maintenanceRecordService.addmaintenanceRecord(maintenanceRecord);
                    maintenanceRecordDAO.changestatus(4,Integer.parseInt(assetidfield.getText()));
                    JOptionPane.showMessageDialog(null,"添加成功");
                    System.exit(0);
                }catch (SQLException ee){
                    JOptionPane.showMessageDialog(null, "添加失败: " + ee.getMessage() + "--" + maintenancedate.getText());
                    System.exit(0);
                }
            }
        });
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    List<MaintenanceRecord> maintenanceRecordList = maintenanceRecordService.getAllMaintenanceRecords();
                    StringBuilder sb = new StringBuilder();
                    for(MaintenanceRecord maintenanceRecord : maintenanceRecordList){
                        sb.append("维修ID: ").append(maintenanceRecord.getMaintenanceID())
                                .append("维修花费:").append(maintenanceRecord.getMaintenanceCost())
                                .append("维修日期:").append(maintenanceRecord.getMaintenanceDate()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                    System.exit(0);
                }catch (SQLException ee){
                    JOptionPane.showMessageDialog(null, "查询失败: " + ee.getMessage());
                    System.exit(0);
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int maintenanceid = Integer.parseInt(maintenanceIDfield.getText());
                try{
                    MaintenanceRecordDAO maintenanceRecordDAO = new MaintenanceRecordDAO();
                    int assetid = maintenanceRecordDAO.mainid(Integer.parseInt(maintenanceIDfield.getText()));
                    maintenanceRecordDAO.changestatus(1,assetid);
                    maintenanceRecordService.deleteMaintenanceRecord(maintenanceid);
                    JOptionPane.showMessageDialog(null,"删除成功");
                    System.exit(0);
                }catch (SQLException ee){
                    JOptionPane.showMessageDialog(null, "删除失败: " + ee.getMessage());
                    System.exit(0);
                }
            }
        });
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MaintenanceRecord maintenanceRecord = new MaintenanceRecord();
                maintenanceRecord.setAssetID(Integer.parseInt(assetidfield.getText()));
                maintenanceRecord.setDesripiton(descriptionfield.getText());
                maintenanceRecord.setMaintenanceCost(Double.parseDouble(maintenacecostfield.getText()));
                maintenanceRecord.setMaintenanceDate(maintenancedatefield.getText());
                maintenanceRecord.setMaintenanceID(Integer.parseInt(maintenanceIDfield.getText()));
                try{
                    maintenanceRecordService.updateMaintenanceRecord(maintenanceRecord);
                    JOptionPane.showMessageDialog(null,"修改成功");
                    System.exit(0);
                }catch (SQLException ee){
                    JOptionPane.showMessageDialog(null, "更新失败: " + ee.getMessage());
                    System.exit(0);
                }
            }
        });
    }
}
