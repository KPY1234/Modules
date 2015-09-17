package modules.utilities.obj;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import modules.ml.core.Instance;
import modules.ml.core.Instances;

/**
 * <p>
 * <b>�`��(Summary)</b></br> <code>Cloner</code> �����骺�ƻs���O, �Q��
 * <code> ObjectStream </code>, </br> <code> ByteArrayStream </code>, �F��ƻs���骺�ĪG�C
 * 
 * <p>
 * <b>�B�檬�A(Runtime Status)</b></br> <code>Cloner.clone(Object object)</code>
 * ���@�R�A���, �i����������ƩI�s�ϥΡC
 * 
 * <p>
 * <b>�`�N�ƶ�(Note)</b></br> �L�C
 * 
 * <p>
 * <b>�ϥΤ覡(Operation Instructions)</b></br> �I�s
 * <code>Cloner.clone(Object object) </code>, �i�o�^�Ǥ��ƻs����C</br>
 * 
 * <p>
 * <b>�ҥ~�B�z(Exception Handle)</b></br> �L�C
 * 
 * @author �Q�{�w
 * @since �����ި�n�� version 1.0
 * 
 */
public class Cloner
{

    /**
     * <code>Cloner</code>�غc���C
     */
    private Cloner()
    {

    }

    /**
     * �ƻs�ǤJ��<code> object </code>����C
     * 
     * @param object �ƻs�ӷ�����C
     * @return �ƻs��������C
     */
    public static synchronized Object clone(Object object)
    {
        try
        {
            final ByteArrayOutputStream bo = new ByteArrayOutputStream();
            final ObjectOutputStream output = new ObjectOutputStream(bo);
            output.writeObject(object);
            output.close();
            final ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
            final ObjectInputStream input = new ObjectInputStream(bi);
            Object o = input.readObject();
            input.close();
            return o;
        }
        catch (final IOException e)
        {
            e.printStackTrace();
        }
        catch (final ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
		
    	
    	Instances insts = new Instances(4);
		insts.addInstance(new Instance("1,2.21,aa1, ,ee",","));
		insts.addInstance(new Instance("2,2.31,aa, ,ee",","));
		insts.addInstance(new Instance("3,7.21,aa,7,qe",","));
		insts.addInstance(new Instance("4,5.2,aaaa, ,uuu",","));
		
		insts.checkBoundry();
		
		
		Instances newInsts = (Instances) Cloner.clone(insts);
		
		newInsts.removeColumn(0);
		System.out.println(insts);
		System.out.println(newInsts);
    	
	}
    
}
