package ifs;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class allows the user to create a file containing the required tags and their respective values in order to create a custom 3D fractal.
 * There must be at least one space between the tags and their values. The program can handle virtually any other amount of whitespace, though there are exceptions such as
 * the matrix values.
 *  @author Neil Kingdom
 *  @version 1.1
 *  @since 2020-3-27
 */
public class FileIfs implements AffineTransform {
	
	private String name;
	private double[][] affine;
	private double scale;
    private int height;
    private int width;
    private int xOffset;
    private int yOffset;
  
    /**
     * @param fileName Accepts a valid file path
     */
    public FileIfs(String fileName) {
    	
    	File ifsSpecs = new File(fileName);
    	try {	
    		Scanner scan = new Scanner(ifsSpecs);
    		while(scan.hasNext()) {
    			switch(scan.next()) {
					case "scale":
						scale = scan.nextDouble(); 
						break;
					case "height":
						height = scan.nextInt();
						break;
					case "width":
						width = scan.nextInt();
						break;
					case "xOffset":
						xOffset = scan.nextInt();
						break;
					case "yOffset":
						yOffset = scan.nextInt();
						break;
					case "name":
						name = scan.nextLine().replaceAll("\\s+", ""); //replace all extra whitespace with no space
						break;
					case "affine":
						int y = scan.nextInt();
						String s = null;	
						
						for(int i = 0; i < y; i++) {
							while(scan.hasNextDouble()) {
								s += scan.next() + " ";
							}
						}
						
						s = s.replaceAll("\\bnull", ""); //remove the newline character which appears as null in scan.next()
						int x = s.split("\\s+").length/y; //count each string which appears between any amount of whitespace on the current line
						Scanner nw = new Scanner(s);
						affine = new double[y][x];
						
						for(int j = 0; j < y; j++) {
							for(int k = 0; k < x; k++) {
								affine[j][k] = nw.nextDouble();
							}
						}
						break;
    			}
    		}
    		scan.close();
		} 
    	
    	catch (FileNotFoundException | NoSuchElementException e) {
    		
			System.out.print("File is either missing or is corrupt. Please try again: ");
			Scanner scan = new Scanner(System.in);
			fileName = scan.nextLine();
			new FileIfs(fileName);
			scan.close();
		}
    }
    			
    @Override
	public String getName() {
        return name;
    }

    @Override
    public double[][] getAffine() {
        return affine;
    }

    @Override
    public double getScale() {
        return scale;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getXOffset() {
        return xOffset;
    }

    @Override
    public int getYOffset() {
        return yOffset;
    }
} 
