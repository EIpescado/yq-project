package pers.yurwisher.clockwerk.structural.filter.criteria;

import pers.yurwisher.clockwerk.structural.filter.Person;

import java.util.List;

/**
 * @author yq
 * @date 2019/09/20 10:57
 * @description 过滤用户
 * @since V1.0.0
 */
public interface Criteria {

    List<Person> meetCriteria(List<Person> persons);
}
