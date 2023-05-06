import java.util.ArrayList;

/*
 * A Playlist contains an array list of AudioContent (i.e. Song, AudioBooks, Podcasts) from the library
 */
public class Playlist
{
	private String title;
	private ArrayList<AudioContent> contents; // songs, books, or podcasts or even mixture
	
	public Playlist(String title)
	{
		this.title = title;
		contents = new ArrayList<AudioContent>();
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}

	public void addContent(AudioContent content)
	{
		contents.add(content);
	}
	
	public ArrayList<AudioContent> getContent()
	{
		return contents;
	}

	public void setContent(ArrayList<AudioContent> contents)
	{
		this.contents = contents;
	}
	
	/*
	 * Print the information of each audio content object (song, audiobook, podcast)
	 * in the contents array list. Print the index of the audio content object first
	 * followed by ". " then make use of the printInfo() method of each audio content object
	 * Make sure the index starts at 1
	 */
	public void printContents()
	{
		//iterates until i reaches contents.size() 
		for(int i = 0; i < contents.size(); i++){
			//Prints i + 1 since i starts at 0, followed by a period for formatting.
			System.out.print("" + (i + 1) + ". ");
			//gets contents at index i, calls printInfo() method to print info.
			contents.get(i).printInfo();
			//new line for formatting
			System.out.println("");
		}
	}

	// Play all the AudioContent in the contents list
	public void playAll()
	{
		//for loop to loop until i reaches contents.size() 
		for(int i = 0; i < contents.size(); i++){
			//calls play method to play contents at index i
			contents.get(i).play();
			//new line for formatting
			System.out.println();
		}
	}
	
	// Play the specific AudioContent from the contents array list.
	// First make sure the index is in the correct range. 
	public void play(int index)
	{
		//if condition to see if index is in correct conditions using contains method. Carries into the if, if condition is true.
		if(contains(index)){
			//gets the index minus 1, and calls play method to play it.
			contents.get(index - 1).play();
		}
	}
	
	public boolean contains(int index)
	{
		return index >= 1 && index <= contents.size();
	}
	
	// Two Playlists are equal if their titles are equal
	public boolean equals(Object other)
	{
		//casting other to Playlist and setting it to playlist variable.
		Playlist playlist = (Playlist) other;
		//checking if title is equal, if equal returns true
		if(getTitle().equals(playlist.getTitle())){
			return true;
		}
		//return false if not equal
		return false;
	}
	
	// Given an index of an audio content object in contents array list,
	// remove the audio content object from the array list
	// Hint: use the contains() method above to check if the index is valid
	// The given index is 1-indexed so convert to 0-indexing before removing
	public void deleteContent(int index)
	{
		if (!contains(index)) return;
		contents.remove(index-1);
	}
	
	
}
