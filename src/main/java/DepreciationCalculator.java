public class DepreciationCalculator {
    // 这里主要使用 年限平均法与双倍余额递减法 如果方法错误或者错误使用请见谅 :(
    // 直线法计算折扣 （年限平均法）
    public double straight(double cost,double salvageValue,double usefulLife){
        return (cost-salvageValue)/usefulLife;
    }
    // 双倍余额递减法
    public double doubleDecline(double cost,double salvageValue,double usefulLife){
        double rate = 2.0/usefulLife;
        if(usefulLife<3){
            return (cost-salvageValue)/usefulLife;
        }
        double[] nextcost = new double[(int) usefulLife];
        int k=1;
        int life = (int) usefulLife;
        double newcost = cost;
        while(life!=2){
            for(int i=1;i<=k;i++){
                newcost =  newcost - nextcost[i];
            }
            nextcost[k] = newcost*rate;
            life--;
            k++;
            newcost=cost;
        }
        for(int i=1;i<k;i++){
            cost = cost - nextcost[i];
        }
        return (cost-salvageValue)/2;
    }
}
