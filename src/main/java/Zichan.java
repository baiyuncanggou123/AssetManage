import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Zichan {
    public static void main(String args[]){
        String sql0 = "SELECT * FROM assets";
        boolean Mysqlstatus = true;
        while(Mysqlstatus){
            try(Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql0)
            ){
                Scanner scanner = new Scanner(System.in);
                Mysqlstatus=false;
                System.out.println("数据库连接成功 请继续你的操作");
                System.out.println("是否进行基础资产数据修改 输入数字2: 不改 输入数字1: 修改 输入数字0: 退出");
                int choose = 2;
                //int choose = scanner.nextInt();
                if(choose==2) break;
                if(choose==0||choose!=1) System.exit(0);
                System.out.println("请继续你的操作 输入密码:");
                int secret = 155180;
                int input = scanner.nextInt();
                if(input==secret){
                    System.out.println("密码正确成功 请继续你的操作");
                    System.out.println("正在进行基础资产数据增加 资产类别为1 资产位置为2 资产状态为3 输入数字0: 退出");
                    System.out.println("请继续你的操作，输入操作：");
                    int choose1 = scanner.nextInt();
                    if((choose1!=1&&choose1!=2&&choose1!=3)||choose1==0){
                        System.exit(0);
                    }
                    PreInsertAsset preInsertAsset = new PreInsertAsset();
                    switch (choose1){
                        case 1:
                            System.out.println("输入你想增加的资产类别");
                            String assetcategory = scanner.nextLine();
                            preInsertAsset.AssetCategory(assetcategory);
                            break;
                        case 2:
                            System.out.println("输入你想增加的资产位置");
                            String assetlocation = scanner.nextLine();
                            preInsertAsset.AssetLocation(assetlocation);
                            break;
                        case 3:
                            System.out.println("输入你想增加的资产状态");
                            String assetstatus = scanner.nextLine();
                            preInsertAsset.Assetstatus(assetstatus);
                            break;
                        default:
                            System.exit(0);
                    }
                }
                else{
                    System.out.println("wrong input");
                    System.exit(0);
                }
            }catch (SQLException e){
                System.out.println(e);
                System.out.println("数据库连接失败 请检查你的环境配置");
                break;
            }
        }
        if(!Mysqlstatus){
            AssetManageUI assetManageUI = new AssetManageUI();
            assetManageUI.setVisible(true);
        }
    }
}
