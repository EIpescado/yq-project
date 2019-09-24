package pers.yurwisher.clockwerk.behavioral.visitor;

/**
 * @author yq
 * @date 2019/09/24 15:14
 * @description
 * @since V1.0.0
 */
public class Computer implements ComputerPart {

    ComputerPart[] parts;

    public Computer() {
        parts = new ComputerPart[]{
                new ComputerPart() {
                    @Override
                    public String name() {
                        return "Mouse";
                    }

                    @Override
                    public void accept(ComputerPartVisitor visitor) {
                        visitor.visit(this);
                    }
                },
                new ComputerPart() {
                    @Override
                    public String name() {
                        return "Keyboard";
                    }

                    @Override
                    public void accept(ComputerPartVisitor visitor) {
                        visitor.visit(this);
                    }
                },
                new ComputerPart() {
                    @Override
                    public String name() {
                        return "Monitor";
                    }

                    @Override
                    public void accept(ComputerPartVisitor visitor) {
                        visitor.visit(this);
                    }
                }

        };
    }

    @Override
    public String name() {
        return "PC";
    }

    @Override
    public void accept(ComputerPartVisitor visitor) {
        for (int i = 0; i < parts.length; i++) {
            parts[i].accept(visitor);
        }
        visitor.visit(this);
    }
}
