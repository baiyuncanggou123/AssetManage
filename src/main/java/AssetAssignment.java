public class AssetAssignment {
    private int assignmentID=0;
    private int assetID=0;
    private String assigned;
    private String assignedTo;
    private String assignmentDate;
    private String returnDate;
    public int getAssignmentID() {
        return this.assignmentID;
    }
    public void setAssignmentID(int setassignmentID){
        this.assignmentID=setassignmentID;
    }
    public int getAssetID() {
        return this.assetID;
    }
    public void setAssetID(int asset1ID){
        this.assetID=asset1ID;
    }
    public String getAssigned(){
        return this.assigned;
    }
    public void setAssigned(String assigned1){
        this.assigned=assigned1;
    }
    public String getAssignedTo() {
        return this.assignedTo;
    }
    public void setAssignedTo(String assignedTo1){
        this.assignedTo=assignedTo1;
    }
    public String getAssignmentDate(){
        return this.assignmentDate;
    }
    public void setAssignmentDate(String assignmentDate1){
        this.assignmentDate=assignmentDate1;
    }
    public String getReturnDate(){
        return this.returnDate;
    }
    public void setReturnDate(String returnDate1){
        this.returnDate=returnDate1;
    }
}
