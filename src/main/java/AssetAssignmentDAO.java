import com.mysql.cj.jdbc.exceptions.ConnectionFeatureNotAvailableException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class AssetAssignmentDAO {
    // 增加财产领用记录
    public void addAssignment(AssetAssignment assetAssignment) throws SQLException{
        String sql = "INSERT INTO assetassignments (AssetID,AssignedTo,AssignmentDate," +
                "ReturnDate) VALUES (?, ?, ?, ?)";
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1,assetAssignment.getAssetID());
            stmt.setString(2,assetAssignment.getAssigned());
            stmt.setString(3,assetAssignment.getAssignmentDate());
            stmt.setString(4, assetAssignment.getReturnDate());
            stmt.executeUpdate();
        }
    }
    // 查询所有的财产领用记录
    public List<AssetAssignment> getAllAssetAssignments() throws SQLException{
        String sql = "SELECT * FROM assetassignments";
        List<AssetAssignment> assetAssignments = new ArrayList<>();
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ){
            while(rs.next()){
                AssetAssignment assetAssignment = new AssetAssignment();
                assetAssignment.setAssignmentID(rs.getInt("AssignmentID"));
                assetAssignment.setAssigned(rs.getString("AssignedTo"));
                assetAssignment.setReturnDate(rs.getString("ReturnDate"));
                assetAssignment.setAssetID(rs.getInt("AssetID"));
                assetAssignment.setAssignmentDate(rs.getString("AssignmentDate"));
                assetAssignments.add(assetAssignment);
            }
        }
        return assetAssignments;
    }
    // 更新领用记录
    public void updateAssignment(AssetAssignment assetAssignment) throws SQLException{
        String sql = "UPDATE assetassignments SET AssetID=? , AssignedTo=? , AssignmentDate=?," +
                "ReturnDate=? WHERE AssignmentID=?";
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1,assetAssignment.getAssetID());
            stmt.setString(2,assetAssignment.getAssigned());
            stmt.setString(3,assetAssignment.getAssignmentDate());
            stmt.setString(4,assetAssignment.getReturnDate());
            stmt.setInt(5,assetAssignment.getAssignmentID());
            stmt.executeUpdate();
        }
    }
    // 删除领用记录
    public void deleteAssignment(int assignmentID) throws SQLException{
        String sql = "DELETE FROM assetassignments WHERE AssignmentID = ?";
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1,assignmentID);
            stmt.executeUpdate();
        }
    }
    // 自检查，如果存在超出日期的直接删除
    public void checkself () throws SQLException{
        String sql = "SELECT * FROM assetassignments";
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ){
            while(rs.next()){
                AssetAssignment assetAssignment = new AssetAssignment();
                assetAssignment.setReturnDate(rs.getString("ReturnDate"));
                assetAssignment.setAssignmentID(rs.getInt("AssignmentID"));
                LocalDate currentDate = LocalDate.now();
                String sb2 = currentDate.toString();
                LocalDate date2 = LocalDate.parse(sb2.toString(), DateTimeFormatter.ISO_DATE);
                int year1 = date2.getYear();
                int month1 = date2.getMonthValue();
                int day1 = date2.getDayOfMonth();

                String sb3 = assetAssignment.getReturnDate();
                LocalDate date3 = LocalDate.parse(sb3.toString(), DateTimeFormatter.ISO_DATE);
                int year2 = date3.getYear();
                int month2 = date3.getMonthValue();
                int day2 = date3.getDayOfMonth();

                int jiange = (year1-year2)*365+(month1-month2)*30+(day1-day2);
                System.out.println(jiange);
                if(jiange>0){
                    deleteAssignment(assetAssignment.getAssignmentID());
                }
            }
        }
    }

    // 寻找assetID是否已经存在
    public int assetid(int assetID) throws SQLException{
        String sql = "SELECT * FROM assetassignments WHERE AssetID = ? ";
        int maintenceid = -1;
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1,assetID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                maintenceid=rs.getInt("AssignmentID");
            }
        }
        return maintenceid;
    }

    // 寻找assignmentID是否已经存在
    public int assignmenid(int assignmentID) throws SQLException{
        String sql = "SELECT * FROM assetassignments WHERE AssignmentID = ? ";
        int maintenceid = -1;
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1,assignmentID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                maintenceid=rs.getInt("AssignmentID");
            }
        }
        return maintenceid;
    }
}
