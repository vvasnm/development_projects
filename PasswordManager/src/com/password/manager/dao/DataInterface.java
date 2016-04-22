package com.password.manager.dao;

public interface DataInterface {
	/* this will be used to set the label displayed text */
    public void setCategoriestobeAdded(String [] text);
    /* this will set the screen's title text */
    public void setTitle(String text);
    /* this will get the label's displayed text */
    public String getCategoriestobeAdded();
}
