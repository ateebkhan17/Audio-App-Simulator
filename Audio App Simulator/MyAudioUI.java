import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		
		// Create my music mylibrary
		Library mylibrary = new Library();
		
		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();
			try{
				if (action == null || action.equals("")) 
				{
					System.out.print("\n>");
					continue;
				}
				else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
					return;
				
				else if (action.equalsIgnoreCase("STORE"))	// List all songs
				{
					store.listAll(); 
				}
				else if (action.equalsIgnoreCase("SONGS"))	// List all songs
				{
					mylibrary.listAllSongs(); 
				}
				else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
				{
					mylibrary.listAllAudioBooks(); 
				}
				else if (action.equalsIgnoreCase("PODCASTS"))	// List all songs
				{
					mylibrary.listAllPodcasts(); 
				}
				else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
				{
					mylibrary.listAllArtists(); 
				}
				else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
				{
					mylibrary.listAllPlaylists(); 
				}
				// Download audiocontent (song/audiobook/podcast) from the store 
				// Specify the index of the content
				else if (action.equalsIgnoreCase("DOWNLOAD")) 
				{
					int fromIndex = 0; //initializing an int index1 to get the start of the store content range
					int toIndex = 0; //initializing an int index2 to get the end of the store content range
					
					System.out.print("From Store Content #: "); //prints on screen before user inputs the start index
					if (scanner.hasNextInt()){ //checks if there is another int number in scanner
						fromIndex = scanner.nextInt(); //stores user input into index1
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}

					System.out.print("To Store Content #: "); //prints on screen before user inputs the end index
					if (scanner.hasNextInt()){ //checks if there is another int number in scanner
						toIndex = scanner.nextInt(); //stores user input into index2
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}

					//for loop that starts at the first index and loops to the last index that the user inserted
					for(int i = fromIndex; i <= toIndex; i++){
						//try catch block
						try{
							mylibrary.download(store.getContent(i)); //downloads the store content at index i, and then prints it on the screen if its added
						//catches SongAlreadyDownloadedException and prints message
						}catch(SongAlreadyDownloadedException ex){
							System.out.println(ex.getMessage());
						//catches AudioBookAlreadyDownloadedException and prints message
						}catch(AudioBookAlreadyDownloadedException ex){
							System.out.println(ex.getMessage());
						}
					}
										
				}
				// Get the *library* index (index of a song based on the songs list)
				// of a song from the keyboard and play the song 
				else if (action.equalsIgnoreCase("PLAYSONG")) 
				{
					int index = 0; //sets an index value at 0
					System.out.print("Song Number: "); // Prints on screen before user inputs a song number
					//condition to check if there is another int in scanner
					if(scanner.hasNextInt()){
						index = scanner.nextInt(); // sets index to the next user integer typed
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}
					mylibrary.playSong(index); //calls playSong() method
				}
				// Print the table of contents (TOC) of an audiobook that
				// has been downloaded to the library. Get the desired book index
				// from the keyboard - the index is based on the list of books in the library
				else if (action.equalsIgnoreCase("BOOKTOC")) 
				{
					int index = 0; //sets an index value at 0
					System.out.print("Audio Book Number: "); // Prints on screen before user inputs an audiobook number
					//condition to check if there is another int in scanner
					if(scanner.hasNextInt()){
						index = scanner.nextInt(); // sets index to the next user integer typed
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}
					mylibrary.printAudioBookTOC(index); //calls printAudioBookTOC() method
				}
				// Similar to playsong above except for audio book
				// In addition to the book index, read the chapter 
				// number from the keyboard - see class Library
				else if (action.equalsIgnoreCase("PLAYBOOK")) 
				{
					int index = 0;  //sets an index value at 0
					int chap = 0; //sets a chap value at 0

					System.out.print("Audio Book Number: "); // Prints on screen before user inputs an audiobook number
					//condition to check if there is another int in scanner
					if(scanner.hasNextInt()){
						index = scanner.nextInt(); // sets index to the next user integer typed
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}

					System.out.print("Chapter: "); // Prints on screen before user inputs an chapter number
					//condition to check if there is another int in scanner
					if(scanner.hasNextInt()){
						chap = scanner.nextInt(); //sets chap to next user integer typed
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}
					mylibrary.playAudioBook(index, chap); //calls playAudioBook() method
				}
				// Print the episode titles for the given season of the given podcast
				// In addition to the podcast index from the list of podcasts, 
				// read the season number from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PODTOC")) 
				{
					
				}
				// Similar to playsong above except for podcast
				// In addition to the podcast index from the list of podcasts, 
				// read the season number and the episode number from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYPOD")) 
				{
					
				}
				// Specify a playlist title (string) 
				// Play all the audio content (songs, audiobooks, podcasts) of the playlist 
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYALLPL")) 
				{
					String playlistTitle = ""; //initializes a string playlistTitle

					System.out.print("Playlist Title: "); // Prints on screen before user inputs a playlist title
					//condition to check if there is another input in scanner
					if(scanner.hasNext()){
						playlistTitle = scanner.next();  //sets playlistTitle to next user input typed
						scanner.nextLine();
					}
					mylibrary.playPlaylist(playlistTitle); //calls playPlaylist() method
				}
				// Specify a playlist title (string) 
				// Read the index of a song/audiobook/podcast in the playist from the keyboard 
				// Play all the audio content 
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYPL")) 
				{
					String playlistTitle = ""; //initializes a string playlistTitle
					int index = 0; //initializes an index and sets it to 0

					System.out.print("Playlist Title: "); //outputting a string playlist title on screen
					if(scanner.hasNext()){ //checking to see if there is another input
						playlistTitle = scanner.next(); //getting next input and storing it into playlistTitle
						scanner.nextLine(); //next output is on a new line
					}

					System.out.print("Content Number: "); //outputting a string content number  on screen
					if(scanner.hasNextInt()){ //checking to see if there is another input
						index = scanner.nextInt(); //getting next input and storing it into index
						scanner.nextLine();  //next output is on a new line
					}
					mylibrary.playPlaylist(playlistTitle, index); //calls playPlaylist() method
				}
				// Delete a song from the list of songs in mylibrary and any play lists it belongs to
				// Read a song index from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("DELSONG")) 
				{
					int index = 0; //initializes an index and sets it to 0
					System.out.print("Library Song #: "); //outputting a string library song number on screen
					if(scanner.hasNextInt()){ //checking to see if there is another input
						index = scanner.nextInt(); //getting next input and storing it into index
						scanner.nextLine();  //next output is on a new line
					}
					mylibrary.deleteSong(index); //calls deleteSong() method
				}
				// Read a title string from the keyboard and make a playlist
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("MAKEPL")) 
				{
					String title = ""; //initializes a string title
					System.out.print("Playlist Title: "); //outputting a string on screen, Playlist Title
					if(scanner.hasNext()){ //checking to see if there is another input
						title = scanner.next(); //getting next input and storing it into title
						scanner.nextLine();  //next output is on a new line
					}
					mylibrary.makePlaylist(title); //calls makePlaylist() method
				}
				// Print the content information (songs, audiobooks, podcasts) in the playlist
				// Read a playlist title string from the keyboard
			// see class Library for the method to call
				else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
				{
					String printTitle = ""; //initializes a string printTitle
					System.out.print("Playlist Title: "); //outputting a string on screen, Playlist Title
					if(scanner.hasNext()){ //checking to see if there is another input
						printTitle = scanner.next(); //getting next input and storing it into printTitle
						scanner.nextLine(); //next output is on a new line
					}
					mylibrary.printPlaylist(printTitle); //calls printPlaylist() method
				}
				// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
				// Read the playlist title, the type of content ("song" "audiobook" "podcast")
				// and the index of the content (based on song list, audiobook list etc) from the keyboard
			// see class Library for the method to call
				else if (action.equalsIgnoreCase("ADDTOPL")) 
				{
					String playlistTitle = ""; //initializes a string playlistTitle
					String type = ""; //initializes a string type
					int index = 0; //initializes an index and sets it to 0

					System.out.print("Playlist Title: "); //outputting a string on screen, Playlist Title
					if(scanner.hasNext()){ //checking to see if there is another input
						playlistTitle = scanner.next(); //getting next input and storing it into playlistTitle
						scanner.nextLine(); //next output is on a new line
					}

					System.out.print("Content type [SONG, PODCAST, AUDIOBOOK]: "); //outputting a string on screen for content type
					if(scanner.hasNext()){ //checking to see if there is another input
						type = scanner.next(); //getting next input and storing it into type
						scanner.nextLine(); //next output is on a new line
					}
					
					System.out.print("Library Content #: "); //outputting a string on screen, Librart Content number
					if(scanner.hasNextInt()){ //checking to see if there is another input
						index = scanner.nextInt(); //getting next int input and storing it into index
						scanner.nextLine();  //next output is on a new line
					}
					mylibrary.addContentToPlaylist(type, index, playlistTitle); //calls addContentToPlaylist() method
				}
				// Delete content from play list based on index from the playlist
				// Read the playlist title string and the playlist index
			// see class Library for the method to call
				else if (action.equalsIgnoreCase("DELFROMPL")) 
				{
					String playlistTitle = ""; //initializes a string playlistTitle
					int index = 0; //initializes an index and sets it to 0

					System.out.print("Playlist Title: "); //outputting a string on screen, Playlist Title
					if(scanner.hasNext()){ //checking to see if there is another input
						playlistTitle = scanner.next(); //getting next input and storing it into playlistTitle
						scanner.nextLine(); //next output is on a new line
					}

					System.out.print("Playlist Content #: "); //outputting a string on screen, Playlist Content #
					if(scanner.hasNextInt()){  //checking to see if there is another input int
						index = scanner.nextInt(); //getting next int input and storing it into playlistTitle
						scanner.nextLine(); //next output is on a new line

					}
					mylibrary.delContentFromPlaylist(index, playlistTitle); //calls delContentFromPlaylist() method
				}
				
				else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
				{
					mylibrary.sortSongsByYear();
				}
				else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
				{
					mylibrary.sortSongsByName();
				}
				else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
				{
					mylibrary.sortSongsByLength();
				}
				//else if case for action Search (title)
				else if(action.equalsIgnoreCase("SEARCH"))
				{
					String searchInput = ""; //initializes a string searchInput
					
					System.out.print("Title: "); //outputting a string on screen, Title: 
					if(scanner.hasNextLine()){ //checking to see if there is another input
						searchInput = scanner.nextLine(); //getting the next input and storing it into searchInput
					}
					store.search(searchInput); //calling search method and passing searchInput (user input)
				}
				//else if case for action Search (artist)
				else if(action.equalsIgnoreCase("SEARCHA"))
				{
					String artistInput = ""; //initializes a string artistInput

					System.out.print("Artist: "); //outputting a string on screen, Artist: 
					if(scanner.hasNextLine()){ //checking to see if there is there another input
						artistInput = scanner.nextLine(); //getting the next input and storing it into artistInput
					}
					store.searchA(artistInput); //calling searchA method and passing artistInput (user input)
				}
				//else if case for action Search (genre)
				else if(action.equalsIgnoreCase("SEARCHG"))
				{
					String genreInput = ""; //initializes a string genreInput

					System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: "); //outputting a list of genres on screen
					if(scanner.hasNextLine()){ //checking to see if there is another input
						genreInput = scanner.nextLine(); //getting the next input and storing it into genreInput
					}
					store.searchG(genreInput); //calling searchG method and passing genreInput (user input)
				}
				//else if case for action downloadA (artist)
				else if(action.equalsIgnoreCase("DOWNLOADA"))
				{
					String artistName = ""; //initializes a string artistName

					System.out.print("Artist Name: "); //outputting a string on screen, Artist Name: 
					if(scanner.hasNextLine()){ //checking to see if there is another input
						artistName = scanner.nextLine(); //getting the next input and storing it into artistName
					}
					//calls downloadAIndex method to figure out what the index of the artist input is
					//then loops through with an index
					for (int index : store.downloadAIndex(artistName)){
						//try catch block
						try{
							mylibrary.download(store.getContent(index + 1)); //downloads the store content at index + 1
						//catches SongAlreadyDownloadedException and prints message
						}catch(SongAlreadyDownloadedException ex){
							System.out.println(ex.getMessage());
						//catches AudioBookAlreadyDownloadedException and prints message
						}catch(AudioBookAlreadyDownloadedException ex){
							System.out.println(ex.getMessage());
						}
					}
				}
				//else if case for action downloadG (genre)
				else if(action.equalsIgnoreCase("DOWNLOADG"))
				{
					String genreName = ""; //initializes a string genreName

					System.out.print("Genre: "); //outputting a string on screen, Genre Name:
					if(scanner.hasNextLine()){ //checking to see if there is another input
						genreName = scanner.nextLine(); //getting the next input and storing it into genreName
					}
					//calls downloadGIndex method to figure out what the index of the genre input is
					//then loops through with an index
					for (int index : store.downloadGIndex(genreName)){
						//try catch block
						try{
							mylibrary.download(store.getContent(index + 1)); //downloads the store content at index + 1
						//catches SongAlreadyDownloadedException and prints message
						}catch(SongAlreadyDownloadedException ex){
							System.out.println(ex.getMessage());
						//catches AudioBookAlreadyDownloadedException and prints message
						}catch(AudioBookAlreadyDownloadedException ex){
							System.out.println(ex.getMessage());
						}
					}

				}
			//catches Exception and prints message
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}
			System.out.print("\n>");
		}
	}
}
