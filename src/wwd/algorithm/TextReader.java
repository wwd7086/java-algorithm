package wwd.algorithm;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import wwd.util.Tuple;

public class TextReader 
{
	private static String path="/Users/wwd/Developer/workspace for java/alogrithm/data/";

	public static List<Integer> readSingle(String fileName)
	{
		ArrayList<Integer> output=new ArrayList<Integer>();
		try 
		{
			File input=new File(path+fileName);
			Scanner scanner=new Scanner(input);
			while(scanner.hasNextInt())
				output.add(scanner.nextInt());
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
	
	public static List<Tuple> readPair(String fileName)
	{
		List<Tuple> output=new ArrayList<Tuple>();
		try 
		{
			File input=new File(path+fileName);
			Scanner scanner=new Scanner(input);
			while(scanner.hasNextInt())
				output.add(new Tuple(scanner.nextInt(), scanner.nextInt()));
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
	
	public static void main(String[] Args)
	{
		//System.out.println(new TextReader().output);
	}

}
