import java.sql.SQLException;
import java.util.List;
public class MaintenanceRecordService {
    private MaintenanceRecordDAO maintenanceRecordDAO = new MaintenanceRecordDAO();
    public void addmaintenanceRecord(MaintenanceRecord record) throws SQLException {
        maintenanceRecordDAO.addMaintenanceRecord(record);
    }
    public List<MaintenanceRecord> getAllMaintenanceRecords() throws SQLException {
        return maintenanceRecordDAO.getAllMaintenanceRecords();
    }
    public void updateMaintenanceRecord(MaintenanceRecord record) throws SQLException {
        maintenanceRecordDAO.updateMaintenanceRecord(record);
    }
    public void deleteMaintenanceRecord(int maintenanceID) throws SQLException {
        maintenanceRecordDAO.deleteMaintenanceRecord(maintenanceID);
    }
}
