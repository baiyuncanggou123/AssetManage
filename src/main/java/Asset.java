/* 资产类 */
public class Asset {
    private int assetID;
    private String assetName;
    private int categoryID;
    private int statusID;
    private int locationID;
    private String purchaseDate;
    private double purchaseCost;
    private String description;
    public int getAssetID() {
        return this.assetID;
    }
    public void setAssetID(int newassetid){
        this.assetID = newassetid;
    }
    public String getAssetName() {
        return this.assetName;
    }
    public void setAssetName(String newassetname){
        this.assetName = newassetname;
    }
    public int getCategoryID() {
        return this.categoryID;
    }
    public void setCategoryID(int newcategoryid){
        this.categoryID=newcategoryid;
    }
    public int getStatusID() {
        return this.statusID;
    }
    public void setStatusID(int newstatusid){
        this.statusID=newstatusid;
    }
    public int getLocationID() {
        return this.locationID;
    }
    public void setLocationID(int newlocation){
        this.locationID=newlocation;
    }
    public String getPurchaseDate() {
        return this.purchaseDate;
    }
    public void setPurchaseDate(String newPurcahse){
        this.purchaseDate=newPurcahse;
    }
    public double getPurchaseCost() {
        return this.purchaseCost;
    }
    public void setPurchaseCost(double newCost){
        this.purchaseCost=newCost;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String newdiscription){
        this.description=newdiscription;
    }
}
