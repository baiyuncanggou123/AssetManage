import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class AssetDAO{
    // 增加新资产
    public void addAsset(Asset asset) throws SQLException{
        String sql = "INSERT INTO Assets (AssetName, CategoryID, " +
                "StatusID, LocationID, PurchaseDate, PurchaseCost, Description) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try(Connection conn = DBUtil.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, asset.getAssetName());
            stmt.setInt(2, asset.getCategoryID());
            stmt.setInt(3, asset.getStatusID());
            stmt.setInt(4, asset.getLocationID());
            stmt.setString(5, asset.getPurchaseDate());
            stmt.setDouble(6, asset.getPurchaseCost());
            stmt.setString(7, asset.getDescription());
            stmt.executeUpdate();
        }
    }
    // 查找所有的资产
    public List<Asset> getAllAssets() throws SQLException{
        List<Asset> assets = new ArrayList<>();
        String sql = "SELECT * FROM Assets";
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
            while(rs.next()){
                Asset asset = new Asset();
                asset.setAssetID(rs.getInt("AssetID"));
                asset.setAssetName(rs.getString("AssetName"));
                asset.setCategoryID(rs.getInt("CategoryID"));
                asset.setStatusID(rs.getInt("StatusID"));
                asset.setLocationID(rs.getInt("LocationID"));
                asset.setPurchaseDate(rs.getString("PurchaseDate"));
                asset.setPurchaseCost(rs.getDouble("PurchaseCost"));
                asset.setDescription(rs.getString("Description"));
                assets.add(asset);
            }
        }
        return assets;
    }
    // 更新资产
    public void updateAsset(Asset asset) throws SQLException{
        String sql = "UPDATE Assets SET AssetName=?, CategoryID=?, StatusID=?, " +
                "LocationID=?, PurchaseDate=?, PurchaseCost=?, Description=? " +
                "WHERE AssetID=?";
        try(Connection coon = DBUtil.getConnection();
            PreparedStatement stmt = coon.prepareStatement(sql)){
            stmt.setString(1, asset.getAssetName());
            stmt.setInt(2, asset.getCategoryID());
            stmt.setInt(3, asset.getStatusID());
            stmt.setInt(4, asset.getLocationID());
            stmt.setString(5, asset.getPurchaseDate());
            stmt.setDouble(6, asset.getPurchaseCost());
            stmt.setString(7, asset.getDescription());
            stmt.setInt(8, asset.getAssetID());
            stmt.executeUpdate();
        }
    }
    // 删除资产
    public void deleteAsset(int assetID) throws SQLException{
        String sql = "DELETE FROM Assets WHERE AssetID=?";
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1,assetID);
            stmt.executeUpdate();
        }
    }
    // 计算折旧时候去数据库查找买的时候差价 同时加上维修的费用
    public double find123(int assetID) throws SQLException{
        String sql = "SELECT * FROM assets WHERE AssetID = ? ";
        String sql1 = "SELECT * FROM maintenancerecords WHERE AssetID = ? ";
        double cost=0;
        double maintence = 0;
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
           ){
            stmt.setInt(1,assetID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                cost = rs.getDouble("PurchaseCost");
            }
        }
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql1);
        ){
            stmt.setInt(1,assetID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                maintence = rs.getDouble("MaintenanceCost");
            }
        }
        return cost+maintence;
    }
    // 计算折旧时候去数据库查找购买日期
    public String find1234(int assetID) throws SQLException{
        String sql = "SELECT * FROM assets WHERE AssetID = ? ";
        String purchasedata = null;
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1,assetID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
               purchasedata=rs.getString("PurchaseDate");
            }
        }
        return purchasedata;
    }
    // 查找对应的维修状态
    public int statusid(int assetID) throws SQLException{
        String sql = "SELECT * FROM assets WHERE AssetID = ? ";
        int maintenceid = -1;
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1,assetID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                maintenceid=rs.getInt("StatusID");
            }
        }
        return maintenceid;
    }
}
