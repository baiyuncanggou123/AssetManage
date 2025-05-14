import java.sql.SQLException;
import java.util.List;

public class AssetAssignmentService {
    private AssetAssignmentDAO assetAssignmentDAO = new AssetAssignmentDAO();
    public void addAssignment(AssetAssignment assetAssignment) throws SQLException {
        assetAssignmentDAO.addAssignment(assetAssignment);
    }
    public List<AssetAssignment> getallAssigenment() throws SQLException{
        return assetAssignmentDAO.getAllAssetAssignments();
    }
    public void updateAssignment(AssetAssignment assetAssignment) throws SQLException{
        assetAssignmentDAO.updateAssignment(assetAssignment);
    }
    public void deleteAssignment(int assignmentID) throws SQLException{
        assetAssignmentDAO.deleteAssignment(assignmentID);
    }
}
