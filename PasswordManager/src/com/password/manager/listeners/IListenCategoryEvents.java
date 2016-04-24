package com.password.manager.listeners;

import com.password.manager.bean.Category;

public interface IListenCategoryEvents {
	void categoryAdded(Category category);
	void categoryDeleted(int index);
}
