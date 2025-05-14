import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class AssetAssignmentUI extends JFrame {
    private AssetAssignmentDAO assetAssignmentDAO = new AssetAssignmentDAO();
    public AssetAssignmentUI(){
        setTitle("资产领用登记");
        setSize(1000,600);
        setLocationRelativeTo(null);
        // 创造面板
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7,2,10,10));
        JLabel assetidLabel = new JLabel("增加/修改 资产ID:");
        JTextField assetidField = new JTextField();
        JLabel assignedToLabel = new JLabel("增加/修改 资产被分配人:");
        JTextField assignedTOField = new JTextField();
        JLabel assignmentdateLabel = new JLabel("增加/修改 资产领用日期:");
        JTextField assignmentdateField = new JTextField();
        JLabel returndataLabel = new JLabel("增加/修改 资产归还日期:");
        JTextField returndateField = new JTextField();
        JLabel assignmentidLabel = new JLabel("修改/删除 资产领用ID:");
        JTextField assignmentidField = new JTextField();
        JButton addButton = new JButton("添加资产领用记录");
        JButton viewButton = new JButton("查看所有资产领用记录");
        JButton deleteButton = new JButton("删除某个资产领用记录");
        JButton findButton = new JButton("修改某个资产领用记录");
        panel.add(assetidLabel);
        panel.add(assetidField);
        panel.add(assignedToLabel);
        panel.add(assignedTOField);
        panel.add(assignmentdateLabel);
        panel.add(assignmentdateField);
        panel.add(returndataLabel);
        panel.add(returndateField);
        panel.add(assignmentidLabel);
        panel.add(assignmentidField);
        panel.add(addButton);
        panel.add(viewButton);
        panel.add(deleteButton);
        panel.add(findButton); // 添加面板到窗口
        add(panel);



        // 子检查 如果有超出日期的直接删除
        try {
            assetAssignmentDAO.checkself();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AssetAssignment assetAssignment = new AssetAssignment();
                assetAssignment.setAssigned(assignedTOField.getText());
                assetAssignment.setAssetID(Integer.parseInt(assetidField.getText()));
                assetAssignment.setReturnDate(returndateField.getText());
                assetAssignment.setAssignmentDate(assignmentdateField.getText());

                // 先查看assetid存在不存在
                int k = assetAssignment.getAssetID();
                try {
                    int l = assetAssignmentDAO.assetid(k);
                    if(l!=-1){
                        JOptionPane.showMessageDialog(null, "添加失败,assetid已经存在,无法操作");
                        System.exit(0);
                    }
                }catch (SQLException f){
                    JOptionPane.showMessageDialog(null, "添加失败: " + f.getMessage());
                    System.exit(0);
                }

                try{
                    assetAssignmentDAO.addAssignment(assetAssignment);
                    JOptionPane.showMessageDialog(null, "资产领用记录表添加成功！");
                }catch (SQLException f){
                    JOptionPane.showMessageDialog(null, "2添加失败: " + f.getMessage()+" "+assetAssignment.getAssigned());
                    System.exit(0);
                }
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    List<AssetAssignment> assetAssignmentList = assetAssignmentDAO.getAllAssetAssignments();
                    StringBuilder sb = new StringBuilder();
                    for(AssetAssignment assetAssignment : assetAssignmentList){
                        sb.append("AssetassginmentID:").append(assetAssignment.getAssignmentID()).append(" AssetID: ").append(assetAssignment.getAssetID()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                }catch (SQLException f){
                    JOptionPane.showMessageDialog(null, "查询失败: " + f.getMessage());
                    System.exit(0);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AssetAssignment assetAssignment = new AssetAssignment();
                assetAssignment.setAssignmentID(Integer.parseInt(assignmentidField.getText()));

                try {
                    int k = assetAssignmentDAO.assignmenid(Integer.parseInt(assignmentidField.getText()));
                    if(k==-1){
                        JOptionPane.showMessageDialog(null, "删除失败: ");
                        System.exit(0);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                try{
                    assetAssignmentDAO.deleteAssignment(assetAssignment.getAssignmentID());
                    JOptionPane.showMessageDialog(null,"删除成功");
                }catch (SQLException f){
                    JOptionPane.showMessageDialog(null, "删除失败: " + f.getMessage());
                    System.exit(0);
                }
            }
        });

        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AssetAssignment assetAssignment = new AssetAssignment();
                assetAssignment.setAssigned(assignedTOField.getText());
                assetAssignment.setAssetID(Integer.parseInt(assetidField.getText()));
                assetAssignment.setReturnDate(returndateField.getText());
                assetAssignment.setAssignmentDate(assignmentdateField.getText());
                assetAssignment.setAssignmentID(Integer.parseInt(assignmentidField.getText()));

                try{
                    assetAssignmentDAO.updateAssignment(assetAssignment);
                    JOptionPane.showMessageDialog(null,"修改成功");
                }catch (SQLException f){
                    JOptionPane.showMessageDialog(null, "修改失败: " + f.getMessage());
                    System.exit(0);
                }
            }
        });
    }
}
