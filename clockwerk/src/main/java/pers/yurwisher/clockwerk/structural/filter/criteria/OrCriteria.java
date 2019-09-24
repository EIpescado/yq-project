package pers.yurwisher.clockwerk.structural.filter.criteria;

import pers.yurwisher.clockwerk.structural.filter.Person;

import java.util.List;

/**
 * @author yq
 * @date 2019/09/20 10:58
 * @description 并集
 * @since V1.0.0
 */
public class OrCriteria implements Criteria {
    private Criteria criteria;
    private Criteria otherCriteria;

    public OrCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> c1 = criteria.meetCriteria(persons);
        List<Person> c2 = otherCriteria.meetCriteria(persons);
        c1.removeAll(c2);
        c1.addAll(c2);
        return c1;
    }
}
