import stl.StlImage;
import ifs.IfsCreator;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class serves as a menu for selecting which Ifs will be printed as a 3D Stl solid
 * @author Neil Kingdom
 * @version 1.0
 * @since 2020-2-25
 */
public class IfsStlFile {

    private int selection;
    private String options;
    private String fileName; 
    private Scanner scanInt;
    private Scanner scanString;
    private String other = null;
    
    private IfsStlFile() {
    	options = "";
    	scanInt = new Scanner(System.in);
    	scanString = new Scanner(System.in);
    }

    /**
     * The main method which acts as a GUI menu for the program
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {

        IfsStlFile call = new IfsStlFile();
        IfsCreator ifsCreate = new IfsCreator();
        int ifsLength = ifsCreate.getTransforms().length + 1; //plus one for other
        
        for(int i = 0; i < ifsLength-1; i++)
        	call.options += "\n" + (i+1) + ") " + ifsCreate.getTransforms()[i].getName();

        System.out.println("Available Fractal Images Are:");
        System.out.println(call.options);
        System.out.println((ifsLength) + ") other\n");
        System.out.println("What image number do you want?");

        while(call.selection < 1 || call.selection > ifsLength) {

            System.out.println("Please Select a number between 1 and " + ifsLength);
            call.selection = call.scanInt.nextInt();

            if(call.selection < 1 || call.selection > ifsLength) {

                System.out.println("Sorry, that wasn't a valid number");
                call.selection = 0;
            }
        }
        
        if(call.selection < ifsLength) {

        	call.fileName = "C:\\temp\\" + ifsCreate.getTransforms()[call.selection-1].getName() + ".stl"; //save location
        	PrintWriter pw = new PrintWriter(call.fileName);
        
        	StlImage lithCalc = new StlImage(0.8, 4.0); //object with optimal height values for creating the lithophane
        	lithCalc.createSolid(ifsCreate.generateIfs(ifsCreate.getTransforms()[call.selection-1])).print(pw);
        	System.out.println("Done");
        }
        
        else if(call.selection == ifsLength) {
        	
        	System.out.print("Please enter the directory path for the custom file including the extension: ");
        	call.other = call.scanString.nextLine();
        	call.fileName = "C:\\temp\\" + ifsCreate.getOther(call.other).getName() + ".stl";
        	PrintWriter pw = new PrintWriter(call.fileName);

        	StlImage lithCalc = new StlImage(0.8, 4.0); //object with optimal height values for creating the lithophane
        	lithCalc.createSolid(ifsCreate.generateIfs(ifsCreate.getOther(call.other))).print(pw);
        	System.out.println("Done");
        }
    }
}
