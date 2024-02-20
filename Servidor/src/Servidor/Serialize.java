/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

/**
 *
 * @author system32
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Serialize {

    public static byte[] toBytes(Serializable o) {
        ByteArrayOutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            os = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(os);
            oos.writeObject(o);
            return os.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (Exception ex2) {
                ex2.printStackTrace();
                throw new RuntimeException(ex2);
            }
        }
    }

    public static Object toObject(byte[] s) {
        ByteArrayInputStream is = null;
        ObjectInputStream ois = null;

        try {
            is = new ByteArrayInputStream(s);
            ois = new ObjectInputStream(is);
            return ois.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (Exception ex2) {
                ex2.printStackTrace();
                throw new RuntimeException(ex2);
            }
        }
    }
    
}
