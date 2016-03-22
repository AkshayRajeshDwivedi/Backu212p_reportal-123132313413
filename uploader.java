

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class uploader extends JPanel implements ActionListener  {
	
	static private final String newline = "\n";
	public static List<String> getName;
	  JButton openButton, runScript,saveButton;
	  JTextArea log;
	  JPanel buttonPanel;
	  JFileChooser fc;
	  private static File openfile;
	  private static File savefile;
	 
	 public uploader() {
	   super(new BorderLayout());

	    //Create the log first, because the action listeners
	    //need to refer to it.
	    log = new JTextArea(5, 20);
	    log.setMargin(new Insets(5, 5, 5, 5));
	    log.setEditable(false);
	    JScrollPane logScrollPane = new JScrollPane(log);

	    //Create a file chooser
	    fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

	    JPanel buttonPanel=new JPanel();
	    openButton = new JButton("Open a File...");
	    openButton.addActionListener(this);
	    saveButton = new JButton("Save a File...");
	 	saveButton.addActionListener(this);
	    runScript = new JButton("Run the script...");
	    runScript.addActionListener(this);
        
	    add(buttonPanel, BorderLayout.PAGE_START);
	    add(logScrollPane, BorderLayout.CENTER);
	    buttonPanel.add(openButton);
	    buttonPanel.add(saveButton);
	    buttonPanel.add(runScript);
	    
	    
	  }

	  public void actionPerformed(ActionEvent e) {

	    //Handle open button action.
	      if (e.getSource() == openButton) {
	      int returnVal = fc.showOpenDialog(uploader.this);
	      if (returnVal == JFileChooser.APPROVE_OPTION) {
	    	  openfile= fc.getSelectedFile();
	   //This is where a real application would open the file.
	       log.append("Uploaded: " + openfile.getName() + "." + newline);
	    
	  
	    }
	    }else if (e.getSource() == runScript) {
	    	try
	    	{
	           boolean status=  PdfConv.main(openfile, savefile);
	            log.append("Completed: " + status + "." + newline);
	         }catch(Exception e1)
	    	{
	    		 log.append("Error " +e.toString() + "." + newline);
	    	}
	      
	  }
	    else if(e.getSource()==saveButton)
	     {
	    	  int returnVal1 = fc.showOpenDialog(uploader.this);
		      if (returnVal1 == JFileChooser.APPROVE_OPTION) {
		        savefile = fc.getSelectedFile();
		        log.append("Saved: " + savefile.getName() + "." + newline);
		    
	            }
	     }
	  }
	  
	  /** Returns an ImageIcon, or null if the path was invalid. */
	  /*protected static ImageIcon createImageIcon(String path) {
	    java.net.URL imgURL = fgh.class.getResource(path);
	    if (imgURL != null) {
	      return new ImageIcon(imgURL);
	    } else {
	      System.err.println("Couldn't find file: " + path);
	      return null;
	    }
	  }*/

	  
	  public static void createAndShowGUI() {
	    //Make sure we have nice window decorations.
	    JFrame.setDefaultLookAndFeelDecorated(true);
	    JDialog.setDefaultLookAndFeelDecorated(true);

	    //Create and set up the window.
	    JFrame frame = new JFrame("Uploader");
	    frame.setSize(1200, 200);
		frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    //Create and set up the content pane.
	    JComponent newContentPane = new uploader();
	   newContentPane.setOpaque(true); //content panes must be opaque
	    frame.setContentPane(newContentPane);

	    //Display the window.
	    frame.pack();
	    frame.setVisible(true);
	  }
	  public static void perform()
	  {
		     javax.swing.SwingUtilities.invokeLater(new Runnable() {
		      public void run() {
		        createAndShowGUI();
		      }
		      }); 
	  }
      public static void main(String[] args)
	   {
		  perform();
	    
	  }
}
