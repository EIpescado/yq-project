package pers.yurwisher.clockwerk.structural.facade;

/**
 * @author yq
 * @date 2019/09/20 16:16
 * @description
 * @since V1.0.0
 */
public class SmallFreshMeatFacade {

    private SmallFreshMeat cxk;
    private SmallFreshMeat wyf;

    public SmallFreshMeatFacade() {
        this.cxk = new SmallFreshMeat() {
            @Override
            public void sing() {
                System.out.println("cxk sing : wei?wei?wei?");
            }

            @Override
            public void dance() {
                System.out.println("cxk dance : ji ni tai mei");
            }

            @Override
            public void rap() {
                System.out.println("cxk rap : ji ni tai mei,ni jiu shi tai mei");
            }
        };
        this.wyf = new SmallFreshMeat() {
            @Override
            public void sing() {
                System.out.println("wyf sing : da wan kuan mian");
            }

            @Override
            public void dance() {
                System.out.println("wyf dance : dance xxxx");
            }

            @Override
            public void rap() {
                System.out.println("wyf rap : ni kan zhe ge mian tiao,you cu you kuan");
            }
        };
    }

    public void cxkRap(){
        cxk.rap();
    }

    public void cxkSing(){
        cxk.sing();
    }

    public void cxkDance(){
        cxk.dance();
    }

    public void wyfRap(){
        wyf.rap();
    }

    public void wyfSing(){
        wyf.sing();
    }

    public void wyfDance(){
        wyf.dance();
    }
}
