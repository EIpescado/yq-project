package pers.yurwisher.clockwerk.behavioral.iterator;

/**
 * @author yq
 * @date 2019/09/23 17:43
 * @description 迭代器模式测试
 * @since V1.0.0
 */
public class IteratorTest {

    public static void main(String[] args) {
        String[] names = {"Robert" , "John" ,"Julie" , "Lora"};
        Container container = new NameContainer(names);
        for(Iterator iterator = container.getIterator(); iterator.hasNext();){
            String name = (String)iterator.next();
            System.out.println("Name : " + name);
        }
    }
}
