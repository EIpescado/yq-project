package pers.yurwisher.clockwerk.structural.filter.criteria;

import pers.yurwisher.clockwerk.structural.filter.Person;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yq
 * @date 2019/09/20 10:58
 * @description 未婚
 * @since V1.0.0
 */
public class CriteriaNotMarried implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        return persons.stream().filter(p -> p.getMarried() == null || !p.getMarried() ).collect(Collectors.toList());
    }
}
