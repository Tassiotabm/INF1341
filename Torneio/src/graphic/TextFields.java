package graphic;

import java.util.Vector;

import javax.swing.JTextField;

final public class TextFields {

	private Vector<JTextField> vectorText = new Vector<JTextField>();

	public TextFields(int n){
        for(int i=0;i<n;i++){
        	vectorText.add(new JTextField());
        }
	}

	public Vector<JTextField> getVectorText() {
		return vectorText;
	}
}
