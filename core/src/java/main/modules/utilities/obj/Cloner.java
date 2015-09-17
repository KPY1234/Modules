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
 * <b>總論(Summary)</b></br> <code>Cloner</code> 為實體的複製類別, 利用
 * <code> ObjectStream </code>, </br> <code> ByteArrayStream </code>, 達到複製實體的效果。
 * 
 * <p>
 * <b>運行狀態(Runtime Status)</b></br> <code>Cloner.clone(Object object)</code>
 * 為一靜態函數, 可直接不實體化呼叫使用。
 * 
 * <p>
 * <b>注意事項(Note)</b></br> 無。
 * 
 * <p>
 * <b>使用方式(Operation Instructions)</b></br> 呼叫
 * <code>Cloner.clone(Object object) </code>, 可得回傳之複製實體。</br>
 * 
 * <p>
 * <b>例外處理(Exception Handle)</b></br> 無。
 * 
 * @author 魏迦安
 * @since 指揮管制軟體 version 1.0
 * 
 */
public class Cloner
{

    /**
     * <code>Cloner</code>建構式。
     */
    private Cloner()
    {

    }

    /**
     * 複製傳入的<code> object </code>實體。
     * 
     * @param object 複製來源物件。
     * @return 複製完成物件。
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
