package pers.yurwisher.clockwerk.structural.filter.criteria;

import pers.yurwisher.clockwerk.structural.filter.Person;

import java.util.List;

/**
 * @author yq
 * @date 2019/09/20 10:58
 * @description 交集
 * @since V1.0.0
 */
public class AndCriteria implements Criteria {
    private Criteria criteria;
    private Criteria otherCriteria;

    public AndCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
       return otherCriteria.meetCriteria(criteria.meetCriteria(persons));
    }
}
