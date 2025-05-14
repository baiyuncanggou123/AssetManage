import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
// 预处理阶段 增加初始数据 没必要进行删除操作
public class PreInsertAsset {
    // 增加资产类别记录
    public void AssetCategory(String categoryname) throws SQLException {
        String url = "INSERT INTO assetcategories (CategoryName) VALUES (?)";
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(url);) {
            stmt.setString(1, categoryname);
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    // 增加资产位置记录
    public void AssetLocation(String Assetlocation) throws SQLException{
        String url = "SELECT INTO assetlocations (LocationName) VALUES (?)";
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(url);
        ){
            stmt.setString(1,Assetlocation);
        }
    }
    // 增加资产状态记录（在用 维修中 报废）
    public void Assetstatus(String Assetstatus) throws SQLException{
        String url = "SELECT INTO assetstatuses (StatusName) VAlUES (?)";
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(url);){
            stmt.setString(1,Assetstatus);
        }catch (SQLException e){
            System.out.println(e);
        }
    }
}
