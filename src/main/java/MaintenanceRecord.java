public class MaintenanceRecord {
    private int maintenanceID;
    private int AssetID;
    private String maintenanceDate;
    private String desripiton;
    private double maintenanceCost;
    public int getMaintenanceID(){
        return this.maintenanceID;
    }
    public int getAssetID(){
        return this.AssetID;
    }
    public String getMaintenanceDate(){
        return this.maintenanceDate;
    }
    public String getDescripiton(){
        return this.desripiton;
    }
    public double getMaintenanceCost(){
        return this.maintenanceCost;
    }
    public void setMaintenanceCost(double maintenanceCost){
        this.maintenanceCost = maintenanceCost;
    }
    public void setAssetID(int assetID){
        this.AssetID = assetID;
    }
    public void setMaintenanceDate(String maintenancedate){
        this.maintenanceDate = maintenancedate;
    }
    public void setMaintenanceID(int maintenanceId){
        this.maintenanceID = maintenanceId;
    }
    public void setDesripiton(String newdesripiton){
        this.desripiton = newdesripiton;
    }
}
