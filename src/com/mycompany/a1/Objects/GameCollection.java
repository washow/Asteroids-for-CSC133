package com.mycompany.a1.Objects;

import java.util.Vector;

import com.mycompany.a1.Interface.ICollection;
import com.mycompany.a1.Interface.IIterator;

public class GameCollection implements ICollection {
	private Vector objCollection;
	
	public GameCollection() {
		objCollection = new Vector();
		
	}
	
	public boolean add(Object obj) {
		if (obj != null) {
			objCollection.addElement(obj);
			return true;
		}
		else 
			return false;		
	}
	
	public boolean remove(Object obj) {
		if (obj != null) {
			objCollection.removeElement(obj);
			return true;
		}
		else
			return false;
		
	}
	
	
	public IIterator getIIterator() {
		return new GameVectorIterator();
	}
	
	private class GameVectorIterator implements IIterator {
		private int index;
		
		public GameVectorIterator() {
			index = -1;
		}
		
		public boolean hasNext() {
			if(objCollection.size() <= 0)
				return false;
			
			if(index == objCollection.size() - 1)
				return false;
			
			return true;
		}
		
		public Object getNext() {
			index++;
			return (objCollection.elementAt(index));
			
		}
	}


	
	
}

