
public class Vivian {
	static String[] tokenize(String s) {
		String[] arr = new String[ getWordCount(s) ];
		String buffer;
		
		int wordStartIndex, wordEndIndex, wordCount;
		
		buffer = "";
		wordStartIndex = 0;
		wordCount = 0;
		
		for ( int index = 0; index < s.length(); index++ ) {
			
			if ( index != 0 && s.charAt(index) == ' ' ) {
				wordEndIndex = index;
				
				for ( ; wordStartIndex < wordEndIndex ; wordStartIndex++ ) {
					buffer += s.charAt(wordStartIndex);
				}
				
				arr[ wordCount ] = buffer;
				
				wordStartIndex++;
				wordCount++;
							
				buffer = "";
			}
		}
		
		for ( ; wordStart	Index < s.length() ; wordStartIndex++ )
			buffer += s.charAt(wordStartIndex);
		
		arr[ wordCount ] = buffer;
		
		return arr;
	}
	
	static void printShit(String s) {
		String[] arr = tokenize(s);
		
		System.out.println(arr[0]);
		System.out.println(arr[1]);
		System.out.println(arr[2]);
	}
	
	public String join( String[] arr ) {
		String s = "";
		
		for ( int i = 0 ; i < arr.length ; i++ ) {
			s += arr[i];
			
			if (i < arr.length - 1)
				s += " ";
		}
		
		return s;
	}
	
	public static void main(String args[]) {
		String[] arr = {"fuck", "this"};
		int[] arr2 = {1, 2};
		String s = "fuck this shit";
		
		printShit(s);
	}
	
}
