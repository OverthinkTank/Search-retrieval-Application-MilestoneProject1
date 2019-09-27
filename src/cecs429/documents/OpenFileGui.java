package cecs429.documents;

import javax.swing.JFileChooser;

public class OpenFileGui {
		
	public static void main(String[] args) {
		
		JFileChooser jf = new JFileChooser("e:");
		
		jf.showOpenDialog(null);
		
	}
}
