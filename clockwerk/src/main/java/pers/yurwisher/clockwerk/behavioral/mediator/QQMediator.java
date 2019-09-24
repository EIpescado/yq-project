package pers.yurwisher.clockwerk.behavioral.mediator;

/**
 * @author yq
 * @date 2019/09/24 10:17
 * @description qq对战平台 中介者
 * @since V1.0.0
 */
public class QQMediator extends AbstractMediator {
    public QQMediator(CardPartner farmer1, CardPartner farmer2, CardPartner landlord) {
        super(farmer1, farmer2, landlord);
    }

    @Override
    public void landlordWin(double money){
        //平台抽成,会员增加收益...
        landlord.updateMoney(landlord.currentMoney() + money);
        double x = money / 2;
        farmer1.updateMoney(farmer1.currentMoney() - x);
        farmer2.updateMoney(farmer2.currentMoney() - x);
    }

    @Override
    public void landlordLose(double money){
        landlord.updateMoney(landlord.currentMoney() - money);
        double x = money / 2;
        farmer1.updateMoney(farmer1.currentMoney() + x);
        farmer2.updateMoney(farmer2.currentMoney() + x);
    }

    @Override
    public void farmerWin(double money){
        landlord.updateMoney(landlord.currentMoney() - money * 2);
        farmer1.updateMoney(farmer1.currentMoney() + money);
        farmer2.updateMoney(farmer2.currentMoney() + money);
    }

    @Override
    public void farmerLose(double money){
        landlord.updateMoney(landlord.currentMoney() + money * 2);
        farmer1.updateMoney(farmer1.currentMoney() - money);
        farmer2.updateMoney(farmer2.currentMoney() - money);
    }
}
