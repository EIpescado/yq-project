package pers.yurwisher.clockwerk.behavioral.mediator;

/**
 * @author yq
 * @date 2019/09/24 09:56
 * @description 农民
 * @since V1.0.0
 */
public class Farmer implements CardPartner {

    private double money;

    public Farmer(double money) {
        this.money = money;
    }

    @Override
    public double currentMoney() {
        return money;
    }

    @Override
    public void updateMoney(double money) {
        this.money = money;
    }

    @Override
    public void win(double money, AbstractMediator mediator) {
        mediator.farmerWin(money);
        System.out.println("农民赢: "+ money);
    }

    @Override
    public void lose(double money, AbstractMediator mediator) {
        mediator.farmerLose(money);
        System.out.println("农民输: "+ money);
    }

}
