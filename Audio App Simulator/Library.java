import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library
{
	private ArrayList<Song> 			songs; 
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	playlists; 
	
  //private ArrayList<Podcast> 	podcasts;
	
	// Public methods in this class set errorMesg string 
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions
	String errorMsg = "";
	
	public String getErrorMessage()
	{
		return errorMsg;
	}

	public Library()
	{
		songs 			= new ArrayList<Song>(); 
		audiobooks 	= new ArrayList<AudioBook>(); ;
		playlists   = new ArrayList<Playlist>();
	  //podcasts		= new ArrayList<Podcast>(); ;
	}
	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
	 * See the video
	 */

	public void download(AudioContent content)
	{
		//condition to check if the type of content is song
		if (content.getType().equals(Song.TYPENAME))
		{
			//cast content to Song and set it to variable contentSong
			Song contentSong = (Song) content;
			//condition to check if songs contains the contentSong
			if (songs.contains(contentSong))
			{
				//if it does then sets error message and throws SongAlreadyDownloadedException
				errorMsg = "Song " + contentSong.getTitle() + " already downloaded";
				throw new SongAlreadyDownloadedException(errorMsg);
			}
			//adds contentSong to songs and prints the message that SONG song Added to Library
			songs.add(contentSong);
			System.out.println("SONG " + contentSong.getTitle() + " Added to Library");
		}
		//condition to check if the type of content is AudioBook
		else if (content.getType().equals(AudioBook.TYPENAME))
		{
			//cast content to AudioBook and set it to variable contentAudiobook
			AudioBook contentAudiobook = (AudioBook) content;
			//condition to check if audiobooks contains the contentAudiobook
			if (audiobooks.contains(contentAudiobook))
			{
				//if it does then sets error message and throws AudioBookAlreadyDownloadedException
				errorMsg = "AudioBook " + contentAudiobook.getTitle() + " already downloaded";
				throw new AudioBookAlreadyDownloadedException(errorMsg);
			}
			//adds contentAudiobook to audiobooks and prints the message that AUDIOBOOK audiobook Added to Library
			audiobooks.add(contentAudiobook);
			System.out.println("AUDIOBOOK " + contentAudiobook.getTitle() + " Added to Library");
		}
	}

	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs()
	{
		//iterates until i reaches the size of songs
		for (int i = 0; i < songs.size(); i++){
			//prints index number followed by period
			System.out.print("" + (i + 1) + ". ");
			//prints songs at index i. Calls printInfo method.
			songs.get(i).printInfo();
			//prints a new line for formatting
			System.out.println();	
		}
	}
	
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks()
	{
		//iterates until i reaches the size of songs
		for(int i = 0; i < audiobooks.size(); i++) {
			//prints index number followed by period
			System.out.print("" + (i + 1) + ". ");
			//prints audiobooks at index i. Calls printInfo method.
			audiobooks.get(i).printInfo();
			//prints a new line for formatting
			System.out.println();
		}
	}
	
  // Print Information (printInfo()) about all podcasts in the array list
	public void listAllPodcasts()
	{
		
	}
	
  // Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	// done
	public void listAllPlaylists()
	{
		//iterates until i reaches the size of songs
		for(int i = 0; i < playlists.size(); i++) {
			//prints index number followed by period
			System.out.print("" + (i + 1) + ". ");
			//prints the titles of playlist at index i. Uses getTitle method to get the title.
			System.out.println(playlists.get(i).getTitle());
		}
	}
	
  // Print the name of all artists. 
	public void listAllArtists()
	{
		// First create a new (empty) array list of string 
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arrayl ist is complete, print the artists names

		//creates a new empty array list of String called newArtists
		ArrayList<String> newArtists = new ArrayList<String>();
		//iterates until i reaches the size of songs
		for(int i = 0; i < songs.size(); i++){
			//checks if newArtists already contains the artist in songs at index i
			if(newArtists.contains(songs.get(i).getArtist())){
				//if it does contain it, no changes, continues.
				continue;
			}else{
				//if it doesnt contain the artist, then it adds the artist name (in songs at index i) into newArtists.
				newArtists.add(songs.get(i).getArtist());
			}
		}
		//iterates through newArtists until i reaches the size of songs
		for(int i = 0; i < newArtists.size(); i++){
			//prints out index number followed by a period, followed by the artists at index i
			System.out.println("" + (i + 1) + ". " + newArtists.get(i));
		}
	}

	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	public void deleteSong(int index)
	{
		//condition to check if index is out of bounds.
		if(index < 1 || index > songs.size()){
			//if out of bounds, then sets error message and throws SongNotFoundException
			errorMsg = "Song Not Found";
			throw new SongNotFoundException(errorMsg);
		}
		//condition if size is bigger than 0
		if(songs.size() > 0){
			//Creates a new Song object that sets newSong to songs in Song array list to the given index - 1
			Song newSong = songs.get(index - 1);
			// deleting a song from library at given index - 1
			songs.remove(index - 1); 
			//iterates until i reaches playlists.size
			for(int i = 0; i < playlists.size(); i++){
				//iterates until i reaches the playlists content arraylist
				for(int j = 0; j < playlists.get(i).getContent().size(); j++){
					//Creates a new AudioContent object that sets chooseContent to the current content in playlist.
					AudioContent chooseContent = playlists.get(i).getContent().get(j);
					//Creates a string variable contentType that is equal to the specific type of chooseContent variable
					String contentType = chooseContent.getType();
					//Condition to check if the type is song
					if(contentType.equals(Song.TYPENAME)){
						//condition if the newSong content in playlist equals to the song in library
						if(newSong.equals(chooseContent)){
							//iff true then remove the song from playlist also.
							playlists.get(i).getContent().remove(j);
						}
					}
				}
			}
		}else{
			//if size is not bigger than 0, then set error message and throws SongNotFoundException
			errorMsg = "Song Not Found";
			throw new SongNotFoundException(errorMsg);
		}
	}
	
    // Sort songs in library by year
	public void sortSongsByYear()
	{
		// Use Collections.sort() 
		// uses private class SongYearComparator to intialize songByYear and use that in Collections.sort
		SongYearComparator songByYear = new SongYearComparator();
		Collections.sort(songs, songByYear);
	}

    // Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator <Song>
	{
		//comapre method that checks two song
		//calculates and returns an answer depending on the caclulation of the two song's years.
		public int compare(Song s1, Song s2){
			return s1.getYear() - s2.getYear();
		}
	}

	// Sort songs by length
	public void sortSongsByLength()
	{
	 	// Use Collections.sort() 
		// uses private class SongLengthComparator to intialize songByLength and use that in Collections.sort
		SongLengthComparator songByLength = new SongLengthComparator();
		Collections.sort(songs, songByLength);
	 
	}

  // Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator <Song>
	{
		//comapre method that checks two song
		//calculates and returns an answer depending on the caclulation of the two song's length.
		public int compare(Song s1, Song s2){
			return s1.getLength() - s2.getLength();
		}
	}

	// Sort songs by title 
	public void sortSongsByName()
	{
	  // Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code
		Collections.sort(songs);
	}

	
	
	/*
	 * Play Content
	 */
	
	// Play song from songs list
	public void playSong(int index)
	{
		//condition to check if index is out of bounds.
		if (index < 1 || index > songs.size())
		{
			//if out of bounds then set error message, and throws SongNotFoundException
			errorMsg = "Song Not Found";
			throw new SongNotFoundException(errorMsg);
		}
		//plays songs at index -1. Calls play() method. Returns true after playing.
		songs.get(index-1).play();
	}
	
	// Play podcast from list (specify season and episode)
	// Bonus
	public boolean playPodcast(int index, int season, int episode)
	{
		return false;
	}
	
	// Print the episode titles of a specified season
	// Bonus 
	public boolean printPodcastEpisodes(int index, int season)
	{
		return false;
	}
	
	// Play a chapter of an audio book from list of audiobooks
	// done
	public void playAudioBook(int index, int chapter)
	{
		//condition to check if index is out of bounds.
		if(index < 1 || index > audiobooks.size()){
			//if out of bounds then set error message, and throws AudioBookNotFoundException
			errorMsg = "AudioBook Not Found";
			throw new AudioBookNotFoundException(errorMsg);
		}
		//Creating a new AudioBook called newAudioBook and ets newAudioBook variable to a specific index of audiobooks
		AudioBook newAudiobook = audiobooks.get(index - 1);

		//condition to check if chapters is out of bounds.
		if (chapter < 1 || chapter > newAudiobook.getNumberOfChapters()){
			//if out of bounds then set error message, and throws AudioBookChapterNotFoundException
			errorMsg = "AudioBook Chapter Not Found";
			throw new AudioBookChapterNotFoundException(errorMsg);
		}
		//selects specific chapter using selectChapter() method
		newAudiobook.selectChapter(chapter);
		//calls play() method and plays newAudioBook. Returns true after playing.
		newAudiobook.play();
	}
	
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public void printAudioBookTOC(int index)
	{
		//condition to check if index is out of bounds.
		if(index < 1 || index > audiobooks.size()){
			//if out of bounds then set error message, and throws AudioBookNotFoundException
			errorMsg = "AudioBook Not Found";
			throw new AudioBookNotFoundException(errorMsg);
		}
		//Prints table of contents at specific index of audiobooks. Calls printTOC() method to print. Returns true after printing.
		audiobooks.get(index - 1).printTOC();
	}
	
  /*
   * Playlist Related Methods
   */
	
	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public void makePlaylist(String title)
	{
		//iterates until i reaches playlists.size()
		for(int i = 0; i < playlists.size(); i++){
			//condition to see if the title at index i in playlists is equal to given title
			if(playlists.get(i).getTitle().equals(title)){
				//if it is equal then it sets error message saying that the playlist already exists and throws PlaylistAlreadyExistsException
				errorMsg = "Playlist " + title + " Already Exists";
				throw new PlaylistAlreadyExistsException(errorMsg);
			}
		}
		//Creates a new Playlist object called newPLaylist that is set to title
		Playlist newPlaylist = new Playlist(title);
		//ads newPlaylist into playlists and return true.
		playlists.add(newPlaylist);
	}
	
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	public void printPlaylist(String title)
	{
		//iterates through playlists until i reaches playlists.size()
		for(int i = 0; i < playlists.size(); i++){
			//condition to see if the title at index i in playlists is equal to given title
			if(playlists.get(i).getTitle().equals(title)){
				//if it is equal, it prints the contents at index i in playlists using the printContents() method. Returns true after printing.
				playlists.get(i).printContents();
			}
		}
		//sets error message that playlist is not found and throws PlaylistNotFoundException
		errorMsg = "Playlist Not Found";
		throw new PlaylistNotFoundException(errorMsg);

	}
	
	// Play all content in a playlist

	public void playPlaylist(String playlistTitle)
	{
		//iterates through playlists until i reaches playlists.size()
		for(int i = 0; i < playlists.size(); i++){
			//condition to see if the title at index i in playlists is equal to given playlistTitle
			if(playlists.get(i).getTitle().equals(playlistTitle)){
				//if it is equal, then calls playAll() method at index i in playlists. Returns true after playing.
				playlists.get(i).playAll();
			}
		}
		//sets error message that playlist is not found and throws PlaylistNotFoundException
		errorMsg = "Playlist Not Found";
		throw new PlaylistNotFoundException(errorMsg);
	}
	
	// Play a specific song/audiobook in a playlist
	public void playPlaylist(String playlistTitle, int indexInPL)
	{
		//prints the user playlist title name before playing anything to screen
		System.out.println(playlistTitle);
		//iterates through playlists until i reaches playlists.size()
		for(int i = 0; i < playlists.size(); i++){
			//condition to see if the title at index i in playlists is equal to given playlistTitle
			if(playlists.get(i).getTitle().equals(playlistTitle)){
				//using contains method to see if i in playlists has indexInPL
				if(playlists.get(i).contains(indexInPL)){
					//if condition is true, then i in playlists calls the play method and plays at indexInPL. Returns true after playing
					playlists.get(i).play(indexInPL);
				}
			}
		}
		//sets error message is playlist is not found and throws PlaylistNotFoundException
		errorMsg = "Playlist Not Found";
		throw new PlaylistNotFoundException(errorMsg);
	}
	
	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list

	public void addContentToPlaylist(String type, int index, String playlistTitle)
	{
		//compares type to song, ignoring lower case and upper case differences
		if(type.equalsIgnoreCase(Song.TYPENAME)){
			//index condition to check if it is not in bounds
			if (index > songs.size() || index < 1){
				//if not in bounds, sets error message and throws SongNotFoundException
				errorMsg = "Song Not Found";
				throw new SongNotFoundException(errorMsg);
			}else{
				//if index in bounds then, iterates through playlists
				for(int i = 0; i < playlists.size(); i++){
					//condition to see if the title of i in playlists is equal to given playlistTitle
					if(playlists.get(i).getTitle().equals(playlistTitle)){
						//if condition is true, then it adds song at given index into i in playlists. Sets isFound to true;
						playlists.get(i).addContent(songs.get(index-1));
					}else{
						//if condition is false, continues;
						continue;
					}
				}
			}
		}//compares type to audiobook, ignoring lower case and upper case differences
		else if(type.equalsIgnoreCase(AudioBook.TYPENAME)){
			//index condition to check if it is not in bounds
			if (index > songs.size() || index < 1){
				//if not in bounds, sets error message and throws AudioBookNotFoundException
				errorMsg = "AudioBook Not Found";
				throw new AudioBookNotFoundException(errorMsg);
			}else{
				//if index in bounds then, iterates through playlists
				for(int i = 0; i < playlists.size(); i++){
					//condition to see if the title of i in playlists is equal to given playlistTitle
					if(playlists.get(i).getTitle().equals(playlistTitle)){
						//if condition is true, then it adds audiobook at given index into i in playlists. Sets isFound to true;
						playlists.get(i).addContent(audiobooks.get(index-1));
					}else{
						//if condition is false, continues;
						continue;
					}
				}
			}
		}
	}

  // Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid 

	public void delContentFromPlaylist(int index, String title)
	{
		//creates a new Playlist object called newPlaylist and set it to nothing. 
		Playlist newPlaylist = null;
		//boolean value to track true/false to return
		boolean isFound = false;

		//iterates through playlists until i reaches playlists.size()
		for(int i = 0; i < playlists.size(); i++){
			//condition to see if the title of i in playlists is equal to given title
			if(playlists.get(i).getTitle().equals(title)){
				//if condition is true, then add playlists at index i to newPlaylist, and set isFound to true
				newPlaylist = playlists.get(i);
				isFound = true;
			}else{
				//if condition is not true, set isFound to false and continue.
				isFound = false;
				continue;
			}
		}

		//condition to see if tracker is false
		if(isFound == false){
			//sets error message to Playlist Not Found and throws PlaylistNotFoundException
			errorMsg = "Playlist Not Found";
			throw new PlaylistNotFoundException(errorMsg);
		}

		//condition to check if index is in bounds
		if (index > 0 && index <= newPlaylist.getContent().size()) { 
			//if it is in bounds, then remove the content at specific index from newPlaylist and return true;
            newPlaylist.getContent().remove(index-1); 
        }
		//set error msg to content not found and throws ContentNotInPlaylistException
		errorMsg = "Content Not In Playlist"; 
        throw new ContentNotInPlaylistException(errorMsg);

	}
	
}

//Custom Exception Classes

// Exception class for song that has already been downloaded
class SongAlreadyDownloadedException extends RuntimeException {
	public SongAlreadyDownloadedException(String errorMsg){
		super(errorMsg);
	}
}

// Exception class for song that is not found
class SongNotFoundException extends RuntimeException {
	public SongNotFoundException(String errorMsg){
		super(errorMsg);
	}
}

// Exception class for Audiobook that has already been downloaded
class AudioBookAlreadyDownloadedException extends RuntimeException{
	public AudioBookAlreadyDownloadedException(String errorMsg){
		super(errorMsg);
	}
}

// Exception class for Audiobook that is not found
class AudioBookNotFoundException extends RuntimeException {
	public AudioBookNotFoundException(String errorMsg){
		super(errorMsg);
	}
}

// Exception class for Audiobook Chapters that are not found
class AudioBookChapterNotFoundException extends RuntimeException{
	public AudioBookChapterNotFoundException(String errorMsg){
		super(errorMsg);
	}
}

// Exception class for Playlist that already exists
class PlaylistAlreadyExistsException extends RuntimeException {
	public PlaylistAlreadyExistsException(String errorMsg){
		super(errorMsg);
	}
}

// Exception class for Playlist that is not found
class PlaylistNotFoundException extends RuntimeException {
	public PlaylistNotFoundException(String errorMsg){
		super(errorMsg);
	}
}

// Exception class for content that is not in playlist
class ContentNotInPlaylistException extends RuntimeException{
	public ContentNotInPlaylistException(String errorMsg){
		super(errorMsg);
	}
}

// Exception class for search methods (Title, Artist, or Genre not being found)
class NoMatchesForSearchException extends RuntimeException{
	public NoMatchesForSearchException(String errorMsg){
		super(errorMsg);
	}
}

// Exception class for download methods (Artist or Genre not being found)
class NoMatchesForDownloadException extends RuntimeException{
	public NoMatchesForDownloadException(String errorMsg){
		super(errorMsg);
	}
}

