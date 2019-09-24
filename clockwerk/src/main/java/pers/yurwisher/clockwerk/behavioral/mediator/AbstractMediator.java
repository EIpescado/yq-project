package pers.yurwisher.clockwerk.behavioral.mediator;

/**
 * @author yq
 * @date 2019/09/24 09:58
 * @description 中介者
 * @since V1.0.0
 */
public abstract class AbstractMediator {

    protected  CardPartner farmer1;
    protected  CardPartner farmer2;
    protected  CardPartner landlord;

    public AbstractMediator(CardPartner farmer1, CardPartner farmer2, CardPartner landlord) {
        this.farmer1 = farmer1;
        this.farmer2 = farmer2;
        this.landlord = landlord;
    }

    /**
     * 地主赢
     * @param money 钱
     */
    public abstract void landlordWin(double money);

    /**
     * 地主输
     * @param money 钱
     */
    public abstract void landlordLose(double money);

    /**
     * 农民赢
     * @param money
     */
    public abstract void farmerWin(double money);

    /**
     * 农民输
     * @param money 钱
     */
    public abstract void farmerLose(double money);
}
