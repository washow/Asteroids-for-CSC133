package com.mycompany.a1.Interface;

import com.mycompany.a1.Objects.GameObject;

public interface ICollection {
	public boolean add(Object obj);
	public boolean remove(Object obj);
	public IIterator getIIterator();
	
	
}
