import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
public class AssetRegistrationUI extends JFrame {
    private AssetService assetService = new AssetService();
    public AssetRegistrationUI(){
        setTitle("资产登记");
        setSize(1000,600);
        setLocationRelativeTo(null);
        // 创造面板
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2, 10, 10));
        // 创建组件
        JLabel assetidLabel = new JLabel("修改/删除 资产ID:");
        JTextField assetidField = new JTextField();
        JLabel nameLabel = new JLabel("增加/修改 资产名称:");
        JTextField nameField = new JTextField();
        JLabel categoryLabel = new JLabel("增加/修改 类别ID(必须已经存在):");
        JTextField categoryField = new JTextField();
        JLabel statusLabel = new JLabel("增加/修改  状态ID(必须已经存在):");
        JTextField statusField = new JTextField();
        JLabel locationLabel = new JLabel("增加/修改  位置ID(必须已经存在):");
        JTextField locationField = new JTextField();
        JLabel purchaseDateLabel = new JLabel("增加/修改  购买日期:");
        JTextField purchaseDateField = new JTextField();
        JLabel purchaseCostLabel = new JLabel("增加/修改  购买成本:");
        JTextField purchaseCostField = new JTextField();
        JLabel descriptionLabel = new JLabel("增加/修改 描述:");
        JTextField descriptionField = new JTextField();

        JButton addButton = new JButton("添加资产");
        JButton viewButton = new JButton("查看所有资产");
        JButton deleteButton = new JButton("删除某个资产");
        JButton findButton = new JButton("修改某个资产");

        // 添加组件到面板
        panel.add(assetidLabel);
        panel.add(assetidField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(categoryLabel);
        panel.add(categoryField);
        panel.add(statusLabel);
        panel.add(statusField);
        panel.add(locationLabel);
        panel.add(locationField);
        panel.add(purchaseDateLabel);
        panel.add(purchaseDateField);
        panel.add(purchaseCostLabel);
        panel.add(purchaseCostField);
        panel.add(descriptionLabel);
        panel.add(descriptionField);
        panel.add(addButton);
        panel.add(viewButton);
        panel.add(deleteButton);
        panel.add(findButton);

        // 添加面板到窗口
        add(panel);

        // 按钮事件监听
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Asset asset = new Asset();
                asset.setAssetName(nameField.getText());
                asset.setCategoryID(Integer.parseInt(categoryField.getText()));
                asset.setStatusID(Integer.parseInt(statusField.getText()));
                asset.setLocationID(Integer.parseInt(locationField.getText()));
                asset.setPurchaseDate(purchaseDateField.getText());
                asset.setPurchaseCost(Double.parseDouble(purchaseCostField.getText()));
                asset.setDescription(descriptionField.getText());
                try{
                    assetService.addAsset(asset);
                    // 弹出对话框，告知用户操作成功
                    JOptionPane.showMessageDialog(null, "资产添加成功！");
                }catch (SQLException ee){
                    JOptionPane.showMessageDialog(null, "添加失败: " + ee.getMessage());
                    System.exit(0);
                }
            }
        });
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    List<Asset> assets = assetService.getAllAssets();
                    StringBuilder sb = new StringBuilder();
                    for (Asset asset : assets) {
                        sb.append("AssetID:").append(asset.getAssetID()).append(" AssetName: ").append(asset.getAssetName()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "查询失败: " + ex.getMessage());
                    System.exit(0);
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int k = Integer.parseInt(assetidField.getText());
                try {
                    AssetDAO assetDAO = new AssetDAO();
                    int statusiid = assetDAO.statusid(k);
                    if(statusiid==-1){
                        JOptionPane.showMessageDialog(null, "删除失败,库中不存在");
                        System.exit(0);
                    }
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "删除失败1: " + ex.getMessage());
                    System.exit(0);
                }
                try{
                    assetService.deleteAssets(Integer.parseInt(assetidField.getText()));
                    JOptionPane.showMessageDialog(null,"删除成功");
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "删除失败2: " + ex.getMessage()+Integer.parseInt(assetidField.getText()));
                    System.out.println(ex.getMessage());
                    System.exit(0);
                }
            }
        });
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Asset asset = new Asset();
                    asset.setAssetName(nameField.getText());
                    asset.setCategoryID(Integer.parseInt(categoryField.getText()));
                    asset.setStatusID(Integer.parseInt(statusField.getText()));
                    asset.setLocationID(Integer.parseInt(locationField.getText()));
                    asset.setPurchaseDate(purchaseDateField.getText());
                    asset.setPurchaseCost(Double.parseDouble(purchaseCostField.getText()));
                    asset.setDescription(descriptionField.getText());
                    asset.setAssetID(Integer.parseInt(assetidField.getText()));
                    assetService.update(asset);
                    JOptionPane.showMessageDialog(null,"修改成功");
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "修改失败: " + ex.getMessage());
                    System.out.println(ex.getMessage());
                    System.exit(0);
                }
            }
        });
    }
}
