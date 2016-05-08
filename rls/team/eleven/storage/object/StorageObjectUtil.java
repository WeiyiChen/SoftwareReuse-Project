package team.eleven.storage.object;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Persistent object using StorageObectUtil class
 * @author Dai
 *
 */
public class StorageObjectUtil<T> {
	

	/**
	 * Persistent an object to a file
	 * @param obj - the object to be persistented
	 * @param dumpPath - the path of the file to storage the object 
	 */
	public void storageObject(T obj, String dumpPath) {
		ObjectOutputStream oos = null;
		SerializedObj<T> sObj = new SerializedObj<T>(obj);

		try {
			File f = new File(dumpPath);
			f.getParentFile().mkdirs();
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
			SerializedObj<T> sObj = (SerializedObj<T>)ois.readObject();
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
