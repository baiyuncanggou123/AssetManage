import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AssetManageUI extends JFrame{
    public AssetManageUI(){
        setTitle("资产管理系统");
        setSize(1000,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 退出即关闭
        setLocationRelativeTo(null);
        // 创造面板
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,1,10,10));
        // 创造按钮
        JButton assetButton = new JButton("资产登记");
        JButton assignmentButton = new JButton("资产领用管理");
        JButton depreciationButton = new JButton("资产折旧管理");
        JButton maintenanceButton = new JButton("维修管理");
        JButton exitButton = new JButton("退出");
        // 添加按钮到面板
        panel.add(assetButton);
        panel.add(assignmentButton);
        panel.add(depreciationButton);
        panel.add(maintenanceButton);
        panel.add(exitButton);
        // 添加面板到窗口
        add(panel);
        // 添加按钮监听事件
        assetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 打开新窗口并将其展示出来
                new AssetRegistrationUI().setVisible(true);
            }
        });
        assignmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AssetAssignmentUI().setVisible(true);
            }
        });

        depreciationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AssetDepreciationUI().setVisible(true);
            }
        });

        maintenanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MaintenanceManagementUI().setVisible(true);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

}
