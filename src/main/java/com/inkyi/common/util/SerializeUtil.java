package com.inkyi.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {

	public static byte[] serialize(Object object) {
		// return serializeSp(object);
		if (object == null) {
			throw new RuntimeException("serialize input object==null");
		}
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream(256);
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			oos.flush();
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("serialize error");
		}
	}

	public static Object deserialize(byte[] bytes) {
		// return deserializeSp(bytes);
		if (bytes == null) {
			throw new RuntimeException("deserialize input bytes[]==null");
		}
		ObjectInputStream ois = null;
		ByteArrayInputStream bais = null;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("deserialize error");
		}
	}
}
