package pers.yurwisher.clockwerk.behavioral.mediator;

/**
 * @author yq
 * @date 2019/09/24 10:20
 * @description 中介者模式测试
 * @since V1.0.0
 */
public class MediatorTest {

    public static void main(String[] args) {
        CardPartner landlord = new Landlord(100);
        CardPartner farmer1 = new Farmer(50);
        CardPartner farmer2 = new Farmer(40);
        AbstractMediator mediator = new QQMediator(farmer1,farmer2,landlord);

        //第一局
        landlord.win(10,mediator);
        currentMoney(landlord,farmer1,farmer2);
        //第二局
        farmer1.win(10,mediator);
        currentMoney(landlord,farmer1,farmer2);
        //第三局
        farmer1.lose(5,mediator);
        currentMoney(landlord,farmer1,farmer2);
        //第四局
        landlord.win(20,mediator);
        currentMoney(landlord,farmer1,farmer2);
    }

    private static void currentMoney(CardPartner landlord,CardPartner farmer1,CardPartner farmer2){
        System.out.print("一局结束,地主当前金钱: " + landlord.currentMoney());
        System.out.print(" 农民1号当前金钱: " + farmer1.currentMoney());
        System.out.print(" 农民2号当前金钱: " + farmer2.currentMoney());
        System.out.println();
    }
}
