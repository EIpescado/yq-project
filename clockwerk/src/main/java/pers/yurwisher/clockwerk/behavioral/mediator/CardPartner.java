package pers.yurwisher.clockwerk.behavioral.mediator;

/**
 * @author yq
 * @date 2019/09/24 09:48
 * @description 抽象牌友
 * @since V1.0.0
 */
public interface CardPartner {

    /**
     * 当前总金钱
     * @return money
     */
    double currentMoney();

    /**
     * 更新金钱
     * @param money money
     */
    void updateMoney(double money);

    /**
     * 赢
     * @param money 钱
     * @param mediator 中介者
     */
    void win(double money, AbstractMediator mediator);

    /**
     * 输
     * @param money 钱
     * @param mediator 中介者
     */
     void lose(double money, AbstractMediator mediator);
}
