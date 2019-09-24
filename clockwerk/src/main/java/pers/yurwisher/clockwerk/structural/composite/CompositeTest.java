package pers.yurwisher.clockwerk.structural.composite;

/**
 * @author yq
 * @date 2019/09/20 12:14
 * @description 组合模式测试
 * @since V1.0.0
 */
public class CompositeTest {

    public static void main(String[] args) {
        Employee CEO = new Employee("John", "CEO", 30000);

        Employee headSales = new Employee("Robert", "Head Sales", 20000);

        Employee headMarketing = new Employee("Michel", "Head Marketing", 20000);

        Employee clerk1 = new Employee("Laura", "Marketing", 10000);
        Employee clerk2 = new Employee("Bob", "Marketing", 10000);

        Employee salesExecutive1 = new Employee("Richard", "Sales", 10000);
        Employee salesExecutive2 = new Employee("Rob", "Sales", 10000);

        CEO.add(headSales);
        CEO.add(headMarketing);

        headSales.add(salesExecutive1);
        headSales.add(salesExecutive2);

        headMarketing.add(clerk1);
        headMarketing.add(clerk2);

        //打印该组织的所有员工
        printAllEmployee(CEO);
//        for (Employee headEmployee : CEO.getSubordinates()) {
//            System.out.println(headEmployee);
//            for (Employee employee : headEmployee.getSubordinates()) {
//                System.out.println(employee);
//            }
//        }
    }

    private static void printAllEmployee(Employee top1){
        if(top1 != null){
            System.out.println(top1);
            if(top1.getSubordinates() != null && top1.getSubordinates().size() > 0){
                top1.getSubordinates().forEach(e -> printAllEmployee(e));
            }
        }
    }
}
