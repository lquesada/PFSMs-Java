package org.modelcc.matcher.automata;

public class Type {

    private Object id;
    private int priority;
    private Option option;

    public Type(Object id,Option option) {
    	this(id,option,0);
    }

    public Type(Object id,Option option,int priority) {
        this.id = id;
        this.option = option;
        this.priority = priority;
    }

    public Object getId() {
        return id;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

}
