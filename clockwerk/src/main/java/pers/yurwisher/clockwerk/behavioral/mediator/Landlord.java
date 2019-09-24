package pers.yurwisher.clockwerk.behavioral.mediator;

/**
 * @author yq
 * @date 2019/09/24 09:54
 * @description 地主
 * @since V1.0.0
 */
public class Landlord implements CardPartner {

    private double currentMoney;

    public Landlord(double currentMoney) {
        this.currentMoney = currentMoney;
    }

    @Override
    public double currentMoney() {
        return currentMoney;
    }

    @Override
    public void win(double money, AbstractMediator mediator) {
        mediator.landlordWin(money);
        System.out.println("地主赢: "+ money);
    }

    @Override
    public void lose(double money, AbstractMediator mediator) {
        mediator.landlordLose(money);
        System.out.println("地主输: "+ money);
    }

    @Override
    public void updateMoney(double money) {
        this.currentMoney = money;
    }

}
