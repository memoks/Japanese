import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class KanjiDialogCreator 
{
	public static final String kanjiDelimeter = "K:{}";
	public static final String hiraganaDelimeter = "H:{}";
	public static final int fontSizeKanji = 5;
	public static final int fontSizeHiragana = 1;
	
	public static void main(String[] args)
	{ 
		List<String> lines = readFile("lesson16_words");
		
		for(String line : lines)
			if(line.length() > 0)
				System.out.println(createTableRow(line));
			else
				System.out.println();
	}
	
	public static List<String> readFile(String fileName)
	{
		List<String> list = new ArrayList<String>();
		BufferedReader br = null;
		try 
		{
			br = new BufferedReader(new FileReader(fileName));
			String line = br.readLine();
			while(line != null)
			{
				list.add(line);
				line = br.readLine();
			}
			
			br.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static String createTableRow(String rowData)
	{
		String kanjiRow = "<tr><td><font size='" + fontSizeKanji + "'>";
		String hiraganaRow = "<tr><td><font size='" + fontSizeHiragana + "'>";
		
		
		int i = 0;
		boolean normal = true;
		
		while(i < rowData.length())
		{
			normal = true;
			
			if(rowData.charAt(i) == 'K')
			{
				i += 2;
				kanjiRow += "</font></td><td><font size='" + fontSizeKanji + "'>";
				kanjiRow += rowData.charAt(i);
				++i;
				normal = false;
				kanjiRow += "</font></td><td><font size='" + fontSizeKanji + "'>";
			}
			
			if(rowData.charAt(i) == 'H')
			{
				i += 2;
				hiraganaRow += "</font></td><td><font size='" + fontSizeHiragana + "'>";
				while(rowData.charAt(i) != '｝')
				{
					hiraganaRow += rowData.charAt(i);
					++i;
				}
				hiraganaRow += "</font></td><td><font size='" + fontSizeHiragana + "'>";
				normal = false;
			}
			
			if(normal)
				kanjiRow += rowData.charAt(i) + "";
			
			++i;
		}
		
		hiraganaRow += "</td></tr>";
		kanjiRow += "</td></tr>";
		
		
		return "<table><tbody>\n" + hiraganaRow + "\n" + kanjiRow + "\n</tbody></table>";
	}
}
