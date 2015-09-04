package wwd.util.graph;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import wwd.util.Importable;

public class myGraph extends HashMap<Integer, ArrayList<Integer>>
				     implements Importable
{
	private static final long serialVersionUID = 1L;

	public void readFromFile(String fileName)
	{
		File input=new File(path+fileName);
		Scanner scannerLine, scannerRow;
		boolean isFirst = true;
		ArrayList<Integer> line;
		int key=0;
		
		try 
		{
			scannerLine=new Scanner(input);
			while(scannerLine.hasNextLine())
			{
				scannerRow = new Scanner(scannerLine.nextLine());
				isFirst = true;
				line = new ArrayList<Integer>(); 
				while(scannerRow.hasNextInt())
				{
					if(isFirst)
					{
						isFirst = false;
						key = scannerRow.nextInt();
					}
					else
					{
						line.add(scannerRow.nextInt());
					}
				}
				this.put(key, line);
			}
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
