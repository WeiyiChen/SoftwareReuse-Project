package edu.tongji.reuse.teameleven.rls.storageobject;

import java.io.Serializable;

/**
 * Wrap a object to be serialized
 * @author Dai
 * 
 * @param <T> - the object type to be serialized
 */
class SerializedObj<T> implements Serializable {

	private static final long serialVersionUID = -1145414390501769359L;
	
	/**
	 * the object to be persisted
	 */
	public T obj;

	/**
	 * 
	 * @param obj - the object to be persisted
	 */
	SerializedObj(T obj) {
		this.obj = obj;
	}
}