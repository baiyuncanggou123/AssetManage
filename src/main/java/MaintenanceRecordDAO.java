import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class MaintenanceRecordDAO {
    public void addMaintenanceRecord(MaintenanceRecord record) throws SQLException{
        String sql = "INSERT INTO maintenancerecords (AssetID, MaintenanceDate, MaintenanceCost, Description) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, record.getAssetID());
            stmt.setString(2, record.getMaintenanceDate());
            stmt.setDouble(3, record.getMaintenanceCost());
            stmt.setString(4, record.getDescripiton());
            stmt.executeUpdate();
        }
    }
    public List<MaintenanceRecord> getAllMaintenanceRecords() throws SQLException {
        List<MaintenanceRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM maintenancerecords";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                MaintenanceRecord record = new MaintenanceRecord();
                record.setMaintenanceID(rs.getInt("MaintenanceID"));
                record.setAssetID(rs.getInt("AssetID"));
                record.setMaintenanceDate(rs.getString("MaintenanceDate"));
                record.setMaintenanceCost(rs.getDouble("MaintenanceCost"));
                record.setDesripiton(rs.getString("Description"));
                records.add(record);
            }
        }
        return records;
    }
    // 更新维修记录
    public void updateMaintenanceRecord(MaintenanceRecord record) throws SQLException {
        String sql = "UPDATE maintenancerecords SET AssetID=?, MaintenanceDate=?, MaintenanceCost=?, Description=? WHERE MaintenanceID=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, record.getAssetID());
            stmt.setString(2, record.getMaintenanceDate());
            stmt.setDouble(3, record.getMaintenanceCost());
            stmt.setString(4, record.getDescripiton());
            stmt.setInt(5, record.getMaintenanceID());
            stmt.executeUpdate();
        }
    }
    // 删除维修记录
    public void deleteMaintenanceRecord(int maintenanceID) throws SQLException {
        String sql = "DELETE FROM maintenancerecords WHERE MaintenanceID=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maintenanceID);
            stmt.executeUpdate();
        }
    }
    // 寻找assetID是否已经存在
    public int mainid(int maintenceid1) throws SQLException{
        String sql = "SELECT * FROM maintenancerecords WHERE MaintenanceID = ? ";
        int maintenceid = -1;
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1,maintenceid1);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                maintenceid=rs.getInt("AssetID");
            }
        }
        return maintenceid;
    }
    // 寻找assetID是否已经存在
    public int assetid(int assetID) throws SQLException{
        String sql = "SELECT * FROM maintenancerecords WHERE AssetID = ? ";
        int maintenceid = -1;
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1,assetID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                maintenceid=rs.getInt("AssetID");
            }
        }
        return maintenceid;
    }
    // 修改状态
    public void changestatus(int statusidd,int assetid) throws SQLException{
        String sql = "UPDATE Assets SET StatusID=? WHERE AssetID=?";
        try(Connection coon = DBUtil.getConnection();
            PreparedStatement stmt = coon.prepareStatement(sql)){
            stmt.setInt(1,statusidd);
            stmt.setInt(2,assetid);
            stmt.executeUpdate();
        }
    }
}
