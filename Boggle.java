import java.io.*;
import java.util.*;

// just generates all the strings & prints them as they are generated

public class Boggle
{	
	static String[][] board;
	static long startTime,endTime; // for timing
	static final long MILLISEC_PER_SEC = 1000;
	
	static ArrayList<String> dictionary = new ArrayList<>();
	static ArrayList<String> hits = new ArrayList<>();
	static int maxlength= 0;
	static int time=0;

	public static void main( String args[] ) throws Exception
	{	startTime= System.currentTimeMillis();
		board = loadBoard( args[1] );

		BufferedReader infile= new BufferedReader(new FileReader(args[0]));

		while(infile.ready())
	{
		String words = infile.readLine();
		
		if(words.length()>=3&& words.length()<=Math.pow(board.length,2))
		{
					dictionary.add(words);
					
					if(words.length()>maxlength)
					maxlength=words.length();
					
		}


	}
	infile.close();
	

		
		for (int row = 0; row < board.length; row++)
			for (int col = 0; col < board[row].length; col++)
				dfs( row, col, ""  ); 

				Collections.sort(hits);

				for(int i=0;i<hits.size();i++)
				System.out.println(hits.get(i));
		
	
		
		
		endTime =  System.currentTimeMillis(); // for timing
		
		System.out.println(time);

		
		
		
	} // END MAIN ----------------------------------------------------------------------------

	static void dfs( int r, int c, String word  )
	{	
		word += board[r][c];
		
		


		

		
		if(!prefix(word))// heuristic 
		return;
		else
		if(Search(word))
		{
			if(!hits.contains(word))
			hits.add(word); 
			
		}
		
		

							
		
		
		
		if ( r-1 >= 0 && board[r-1][c] != null )   
		{	String unMarked = board[r][c]; 
			board[r][c] = null; 
			dfs( r-1, c, word ); 
	
			board[r][c] = unMarked; 
		
		if ( r-1 >= 0 && c+1<board.length && board[r-1][c+1] != null ) 
		{	String unMarked = board[r][c]; 
			
			board[r][c] = null; 
			
			dfs( r-1, c+1, word); 
			
			board[r][c] = unMarked; 
		}
		
		if ( c+1<board.length && board[r][c+1] != null) 
		{	String unMarked = board[r][c]; 
			board[r][c] = null; 

			
			dfs( r, c+1, word);
			
			board[r][c] = unMarked; 
		}

		if (r+1<board.length&&c+1<board.length && board[r+1][c+1] != null) 
		{	String unMarked = board[r][c]; 
			board[r][c] = null; 
			
			
			dfs( r+1, c+1, word); 
			
			
			board[r][c] = unMarked; 
		}

		if ( r+1<board.length&&board[r+1][c] != null ) 
		{	String unMarked = board[r][c]; 
			board[r][c] = null; 
			dfs( r+1, c, word); 
			
			board[r][c] = unMarked; 
		}
		
		if ( r+1<board.length&&c-1>=0&&board[r+1][c-1] != null) 
		{	String unMarked = board[r][c]; 
			board[r][c] = null; 
			
			
			dfs( r+1, c-1, word);
			
			board[r][c] = unMarked; 
		}
		if ( c-1>=0&&board[r][c-1] != null)
		{	String unMarked = board[r][c]; 
			board[r][c] = null; 
			
			dfs( r,c-1, word);
			
			board[r][c] = unMarked; 
		}

		if ( r-1>=0&&c-1>=0&&board[r-1][c-1] != null) 
		{	String unMarked = board[r][c]; 
			board[r][c] = null; 

			
			dfs( r-1, c-1, word);
			
			board[r][c] = unMarked; 
		}

	static String[][] loadBoard( String fileName ) throws Exception
	{	Scanner infile = new Scanner( new File(fileName) );
		int rows = infile.nextInt();
		int cols = rows;
		String[][] board = new String[rows][cols];
		for (int r=0; r<rows; r++)
			for (int c=0; c<cols; c++)
				board[r][c] = infile.next();
		infile.close();
		return board;
	} //END LOADBOARD 

	static boolean Search(String word)
	{
		if(word.length()>=3&&word.length()<=maxlength)
		if(dictionary.contains(word))
		return true;

		return false;
		


	}
	static boolean prefix(String word)
	{
		
		for(int i=0;i<dictionary.size();i++)
		{
			if(dictionary.get(i).startsWith(word))
			return true;

		}
		return false;
	}

} // END BOGGLE 
