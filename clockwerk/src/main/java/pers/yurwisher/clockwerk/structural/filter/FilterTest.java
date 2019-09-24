package pers.yurwisher.clockwerk.structural.filter;

import pers.yurwisher.clockwerk.structural.filter.criteria.AndCriteria;
import pers.yurwisher.clockwerk.structural.filter.criteria.Criteria;
import pers.yurwisher.clockwerk.structural.filter.criteria.CriteriaFemale;
import pers.yurwisher.clockwerk.structural.filter.criteria.CriteriaMale;
import pers.yurwisher.clockwerk.structural.filter.criteria.CriteriaNotMarried;
import pers.yurwisher.clockwerk.structural.filter.criteria.OrCriteria;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yq
 * @date 2019/09/20 11:05
 * @description 过滤器模式或标准模式测试
 * @since V1.0.0
 */
public class FilterTest {

    public static void main(String[] args) {
        Person one = new Person("A","MALE",false);
        Person two = new Person("B","FEMALE",true);
        Person three = new Person("C","MALE",true);
        List<Person> list = new ArrayList<>(3);
        list.add(one);
        list.add(two);
        list.add(three);

        Criteria male = new CriteriaMale();
        printPersons("男的",male.meetCriteria(list));
        Criteria female = new CriteriaFemale();
        printPersons("女的",female.meetCriteria(list));
        Criteria notMarried = new CriteriaNotMarried();
        printPersons("没结婚的",notMarried.meetCriteria(list));
        Criteria notMarriedMale = new AndCriteria(notMarried, male);
        printPersons("没结婚的男的",notMarriedMale.meetCriteria(list));
        Criteria notMarriedOrFemale = new OrCriteria(notMarried, female);
        printPersons("没结婚的或者女的",notMarriedOrFemale.meetCriteria(list));
    }

    public static void printPersons(String criteriaName,List<Person> persons){
        System.out.println(criteriaName + ":");
        for (Person person : persons) {
            System.out.println("Person : [ Name : " + person.getName()
                    +", Gender : " + person.getGender()
                    +", Married : " + person.getMarried()
                    +" ]");
        }
    }
}
