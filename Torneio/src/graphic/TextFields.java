package graphic;

import java.util.Vector;

import javax.swing.JTextField;

final public class TextFields {

	private static Vector<JTextField> vectorText = new Vector<JTextField>();

	public TextFields(){
        for(int i=0;i<8;i++){
        	vectorText.add(new JTextField());
        }
	}

	public Vector<JTextField> getVectorText() {
		return vectorText;
	}
}
