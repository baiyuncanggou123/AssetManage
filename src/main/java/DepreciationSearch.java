import jdk.jfr.Category;

import java.sql.*;

public class DepreciationSearch {
    public double search(int assetid) throws SQLException{
        String sql = "SELECT * FROM assets WHERE AssetID = ?";
        int rest = 0;
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1,assetid);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                rest = rs.getInt("CategoryID");
            }
        }
        double resst=0;
        if(rest==1){ // 房产估计
            resst = 0.8;
        }
        if(rest==2){ // 车辆估计
            resst = 0.4;
        }
        if(rest==3){ // 消耗品
            resst = 0.1;
        }
        if(rest==4){ // 日常
            resst = 0.3;
        }
        return resst;
    }
    public double getlife(double rest,int jiange){
        double restt=0;
        if(rest==0.8){ //房产100年
            restt = (100*365 - jiange)/365;
        }
        if(rest==0.4){ // 车辆10年
            restt = (10*365 - jiange)/365;
        }
        if(rest==0.1){ // 消耗品2年
            restt = (2*365 - jiange)/365;
        }
        if(rest==0.3){ // 日常3年
            restt = (3*365 - jiange)/365;
        }
        return restt;
    }
}
