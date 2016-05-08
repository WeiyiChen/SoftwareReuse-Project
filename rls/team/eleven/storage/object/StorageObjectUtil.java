package team.eleven.storage.object;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Persistent object using StorageObectUtil class
 * @author Dai
 *
 */
public class StorageObjectUtil<T> {
	private class SerializedObj implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -1145414390501769359L;
		public T obj;

		SerializedObj(T obj) {
			this.obj = obj;
		}
	}

	/**
	 * Persistent an object to a file
	 * @param obj - the object to be persistented
	 * @param dumpPath - the path of the file to storage the object 
	 */
	public void storageObject(T obj, String dumpPath) {
		ObjectOutputStream oos = null;
		SerializedObj sObj = new SerializedObj(obj);

		try {
			oos = new ObjectOutputStream(new FileOutputStream(dumpPath));
			oos.writeObject(sObj);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			if(oos != null){
				try{
					oos.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * get an object from the file path storaged this object.
	 * @param dumpPath - the path of the file to storage the object 
	 * @return
	 */
	public T getObjectFormDump(String dumpPath) {
		ObjectInputStream ois = null;
		T result = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(dumpPath));
			SerializedObj sObj = (SerializedObj)ois.readObject();
			result = sObj.obj;
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			if(ois != null){
				try{
					ois.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return result;
	}

}
