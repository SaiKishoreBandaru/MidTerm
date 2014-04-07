The MIT License (MIT)

Copyright (c) 2013 Simon Dooms

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Main {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int temp=0;/*List<String> sl= new ArrayList<String>();*/
		Map<String, String> map = new HashMap<>();
		//String s="0000417::Le voyage dans la lune (1902)::Short|Adventure|Fantasy|Short";
	
		BufferedReader reader = new BufferedReader(new FileReader("/home/cloudera/Desktop/Data/movies/movies.dat"));
		String line = null;
		while ((line = reader.readLine()) != null) {
			//System.out.println(line);
			temp=0;List<String> sl= new ArrayList<String>();
			String[] sp=line.split("::",3 );
			String last=sp[2];
			String movie=sp[1];
		//System.out.println(last);
		
		for(int i=0;i<last.length();i++)
		{
			if(last.charAt(i)=='|')
			{
				if(!sl.contains(last.substring(temp, i))) {
					
				    sl.add(last.substring(temp, i));
				}
				temp=i+1;
			}
		}
		if(!sl.contains(last.substring(temp, last.length()))) {
	
		    sl.add(last.substring(temp, last.length()));
		}
		Iterator<String> iterator = sl.iterator();
		while (iterator.hasNext()) {
			String g=iterator.next();
			if(g.compareTo("")==0)
				g="General";
			//System.out.println(g);
			if(!map.isEmpty())
			{
				if(map.containsKey(g))
				{
					//System.out.println(g);
					//System.out.println(map.get(g));
					String content=map.get(g)+","+movie;
					map.put(g, content);
				}
				else
				{
					map.put(g, movie);
				}
						
					
			}
			
			else
			{
				//System.out.println(g);
				map.put(g, movie);
			}
				
				
		}
	    // ...
			}
		BufferedWriter reader1 = new BufferedWriter(new FileWriter("/home/cloudera/Desktop/Data/movies/output1.json"));
		System.out.println(map.size()+"\n");
		Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
		reader1.write("{");
		while (entries.hasNext()) {
		
		    Map.Entry<String, String> entry = entries.next();
		    if(entry.getKey().compareTo("Drama")==0)
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		    String entireline="\"" + entry.getKey() +"\":{\""+ "movie"+"\":"+"\""+ entry.getValue()+"\""+"},"+"\n";
		    reader1.write(entireline);
		}
		reader1.write("}");
		reader1.close();reader.close();
	}

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}

}