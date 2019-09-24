package pers.yurwisher.clockwerk.behavioral.iterator;

/**
 * @author yq
 * @date 2019/09/23 17:42
 * @description 姓名容器
 * @since V1.0.0
 */
public class NameContainer implements Container {

    String[] args;

    public NameContainer(String[] args) {
        this.args = args;
    }

    @Override
    public Iterator getIterator() {
        return new NameIterator(args);
    }

    private class NameIterator implements Iterator {

        String[] args;
        int index;

        public NameIterator(String[] args) {
            this.args = args;
        }

        @Override
        public boolean hasNext() {
            if(index < args.length){
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if(this.hasNext()){
                return args[index++];
            }
            return null;
        }
    }
}
