package org.modelcc.matcher;

public class Match {
	
    private int startIndex;
    
    private int endIndex;
    
    private Object id;
    
    private boolean shadowed;

    public Match(int startIndex,int endIndex,Object id) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.id = id;
        this.shadowed = false;
    }

    public int getStartIndex() {
        return startIndex;
    }
    
    public int getEndIndex() {
        return endIndex;
    }

    public Object getId() {
        return id;
    }

    public boolean isShadowed() {
        return shadowed;
    }
    
    public void setShadowed(boolean shadowed) {
        this.shadowed = shadowed;
    }


}
