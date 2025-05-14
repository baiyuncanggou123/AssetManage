import java.sql.SQLException;
import java.util.List;
public class AssetService {
    public AssetDAO assetDAO = new AssetDAO();
    public void addAsset(Asset asset) throws SQLException{
        assetDAO.addAsset(asset);
    }
    public List<Asset> getAllAssets() throws SQLException{
        return assetDAO.getAllAssets();
    }
    public void deleteAssets(int assetID) throws SQLException{
        assetDAO.deleteAsset(assetID);
    }
    public void update(Asset asset) throws SQLException{
        assetDAO.updateAsset(asset);
    }
}
