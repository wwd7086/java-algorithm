package wwd.algorithm;

import java.util.*;
import wwd.util.*;
import java.io.*;

public class TravelSalesProb
{
	private CityList cityList;
	private float[][] subProb;
	private float inf = Float.POSITIVE_INFINITY;
	private int numSubset;

	public TravelSalesProb(CityList cityList)
	{
		this.cityList = cityList;
		numSubset = (int) Math.pow(2, cityList.numCity - 1);
		subProb = new float[numSubset][cityList.numCity];
	}

	public double findRoute()
	{
		// initialize the list
		subProb[0][0] = 1;
		for (int i = 1; i < numSubset; i++)
		{
			subProb[i][0] = inf;
		}

		// recursion
		// different size of the subproblem
		for (int subSize = 1; subSize <= cityList.numCity; subSize++)
		{
			// different combination of same size
			for (int subIndex = 0; subIndex < numSubset; subIndex++)
			{
				if (Integer.bitCount(subIndex) == subSize)
				{
					// for each vertex in a subproblem
					int localIndex = subIndex;
					for (int i = 1; i <= subSize; i++)
					{
						int n = Integer.lowestOneBit(localIndex);
						localIndex -= n;
						int vertIndex = myMath.getBitIndex(n);

						// for each possible optimal sub structure
						int preSubIndex = subIndex - n;
						float minDist = inf;
						if (preSubIndex == 0)
							minDist = cityList.get(0).getDistance(cityList.get(vertIndex));
						else
						{
							int localPreIndex = preSubIndex;							
							for (int j = 1; j < subSize; j++)
							{
								int m = Integer.lowestOneBit(localPreIndex);
								localPreIndex -= m;
								int preVertIndex = myMath.getBitIndex(m);
								float dist = subProb[preSubIndex][preVertIndex] + cityList.get(preVertIndex).getDistance(cityList.get(vertIndex));
								if (dist < minDist)
									minDist = dist;
							}
						}
						subProb[subIndex][vertIndex] = minDist;
					}
				}
			}
		}

		// get the final result;
		float[] finalCandidate  = subProb[numSubset-1];
		float minValue = inf;
		for(int i=0; i<cityList.numCity; i++)
		{
			float cand = finalCandidate[i]+cityList.get(0).getDistance(cityList.get(i));
			if(cand<minValue)
				minValue = cand;
		}
		return minValue;
	}

	public static void main(String[] args)
	{
		CityList cl = new CityList("tsp.txt");
		//System.out.println(cl);
		TravelSalesProb ts = new TravelSalesProb(cl);
		System.out.println(ts.findRoute());
	}
}

class City
{
	double x;
	double y;

	public City(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public float getDistance(City other)
	{
		return (float) (Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2)));
	}

	public String toString()
	{
		return "x:" + x + " - y:" + y + "\n";
	}
}

class CityList extends ArrayList<City> implements Importable
{
	private static final long serialVersionUID = 1L;
	int numCity = 0;

	public CityList(String fileName)
	{
		readFromFile(fileName);
		optimizeDistance();
	}

	public void readFromFile(String fileName)
	{
		try
		{
			File file = new File(path + fileName);
			Scanner sFile = new Scanner(file);
			Scanner sRow = new Scanner(sFile.nextLine());
			if (sRow.hasNextInt())
				numCity = sRow.nextInt();

			while (sFile.hasNextLine())
			{
				sRow = new Scanner(sFile.nextLine());
				double x = sRow.nextDouble();
				double y = sRow.nextDouble();
				this.add(new City(x, y));
			}

			sFile.close();
			sRow.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		// check consistency of the city number
		if (numCity != this.size())
		{
			System.out.println("number of city not consisitent!");
			numCity = this.size();
		}
	}

	private void optimizeDistance()
	{
		// normalize the Distance
		double aveX = 0, aveY = 0;
		for (City city : this)
		{
			aveX += city.x;
			aveY += city.y;
		}
		aveX /= numCity;
		aveY /= numCity;

		for (City city : this)
		{
			city.x -= aveX;
			city.y -= aveY;
		}
	}
}
