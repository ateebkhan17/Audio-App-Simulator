import java.util.ArrayList;

/*
 * An AudioBook is a type of AudioContent.
 * It is a recording made available on the internet of a book being read aloud by a narrator
 * 
 */
public class AudioBook extends AudioContent
{
	public static final String TYPENAME =	"AUDIOBOOK";
	
	private String author; 
	private String narrator;
	private ArrayList<String> chapterTitles;
	private ArrayList<String> chapters;
	private int currentChapter = 0;

	
	public AudioBook(String title, int year, String id, String type, String audioFile, int length,
									String author, String narrator, ArrayList<String> chapterTitles, ArrayList<String> chapters)
	{
		// Make use of the constructor in the super class AudioContent. 
		// Initialize additional AudioBook instance variables. 

		//super class to initialize AudioContent variables 
		super(title, year, id, type, audioFile, length);
		// initialize audiobook instance variables.
		this.author = author;
		this.narrator = narrator;
		this.chapterTitles = chapterTitles;
		this.chapters = chapters;
	}
	
	public String getType()
	{
		return TYPENAME;
	}

  // Print information about the audiobook. First print the basic information of the AudioContent 
	// by making use of the printInfo() method in superclass AudioContent and then print author and narrator
	// see the video
	public void printInfo()
	{
		//calls super method to print info from superclass AudioContent
		super.printInfo();
		//prints remaining variables author and narrator
		System.out.println("Author: " + getAuthor() + " Narrated by: " + narrator);
	}
	
  // Play the audiobook by setting the audioFile to the current chapter title (from chapterTitles array list) 
	// followed by the current chapter (from chapters array list)
	// Then make use of the the play() method of the superclass
	public void play()
	{
		//sets audio file to the current chapter in chapter titles. Prints a period for formatting and a new line and then prints current chapter in chapters.
		setAudioFile(chapterTitles.get(currentChapter) + "." + "\n" + chapters.get(currentChapter));
		//calls super method play.
		super.play();
	}
	
	// Print the table of contents of the book - i.e. the list of chapter titles
	// See the video
	public void printTOC()
	{
		//iterates until i reaches chapters.size()
		for(int i = 0; i < chapters.size(); i++){
			//Prints chapter followed by index number followed by period followed by specific index in chapter titles
			System.out.println("Chapter " + (i + 1) + ". " + chapterTitles.get(i));
			//prints a new line for formatting
			System.out.println();
		}
	}

	// Select a specific chapter to play - nothing to do here
	public void selectChapter(int chapter)
	{
		if (chapter >= 1 && chapter <= chapters.size())
		{
			currentChapter = chapter - 1;
		}
	}
	
	// Two AudioBooks are equal if their AudioContent information is equal and both the author and narrators are equal
	public boolean equals(Object other)
	{
		//casting other to AudioBook and setting it to a variable audiobook
		AudioBook audiobook = (AudioBook) other;
		//calls super.equal to check if audiocontent information is equal. Then checks if both the author and narrators are equal. Returns true, if they are equal.
		if(super.equals(audiobook) && getAuthor().equals(audiobook.getAuthor()) && getNarrator().equals(audiobook.getNarrator())){
			return true;
		}
		//Returns false if not equal
		return false;
	}
	
	public int getNumberOfChapters()
	{
		return chapters.size();
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getNarrator()
	{
		return narrator;
	}

	public void setNarrator(String narrator)
	{
		this.narrator = narrator;
	}

	public ArrayList<String> getChapterTitles()
	{
		return chapterTitles;
	}

	public void setChapterTitles(ArrayList<String> chapterTitles)
	{
		this.chapterTitles = chapterTitles;
	}

	public ArrayList<String> getChapters()
	{
		return chapters;
	}

	public void setChapters(ArrayList<String> chapters)
	{
		this.chapters = chapters;
	}

}
