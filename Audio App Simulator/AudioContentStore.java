
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore
{                
		private ArrayList<AudioContent> contents;  //initalizing ArrayList contents of type AudioContent
		private Map<String, Integer>  searchTitleMap; //initalizing Map searchTitleMap of key String and value Integer
		private Map<String, ArrayList<Integer>> searchArtistMap; //initalizing Map searchArtistMap of key String and value ArrayList of Integers
		private Map<String, ArrayList<Integer>> searchGenreMap; //initalizing Map searchGenreMap of key String and value ArrayList of Integers
		
		public AudioContentStore()
		{
			contents = new ArrayList<AudioContent>(); //initalizing ArrayList contents of type AudioContent
			searchTitleMap = new HashMap<String, Integer>(); //initalizing HashMap searchTitleMap of key String and value Integer
			searchArtistMap = new HashMap<String, ArrayList<Integer>>(); //initalizing HashMap searchArtistMap of key String and value ArrayList of Integers
			searchGenreMap = new HashMap<String, ArrayList<Integer>>(); //initalizing HashMap searchGenreMap of key String and value ArrayList of Integers
			
			//try catch block
			try{
				ArrayList <AudioContent> audioContentObjects = readFile("store.txt"); //calls readFile() method and stores it in an ArrayList of type AudioContent variable
				//loops through audioContentOjects and adds the iterating object into ArrayList contents each time
				for(AudioContent object : audioContentObjects){ 
					contents.add(object);
				}
			//if error, catches exception
			}catch(IOException ex){
				ex.printStackTrace(); // prints what the error is
				System.exit(1); // exits system
			}
			
			//initializing string variables
			String searchArtist = ""; //utilized to store the artist of the specific song/audiobook
			String searchGenre = ""; //utilized to store the genre of the specific song/audiobook

			//for loop to iterate through the contents ArrayList
			for(int i = 0; i < contents.size(); i++){
				AudioContent contentToFind = contents.get(i); //creates contentToFind of type AudioContent and stores the current index in the contents ArrayList
				searchTitleMap.put(contentToFind.getTitle(), i); //everytime it loops, the title map adds the new content and the index

				//initializing ArrayList of type Integer values
				ArrayList <Integer> indicesArtist = new ArrayList<Integer>(); //used to store the indices for artist
				ArrayList <Integer> indicesGenre = new ArrayList<Integer>(); //used to store the indices for genre

				//condition to check if the content is of Song type
				if(contentToFind.getType().equals(Song.TYPENAME)){
					Song contentSong = (Song) contentToFind; //if condition true, casts the contentSong to Song object
					searchArtist = contentSong.getArtist(); //stores the contentSong artist to String searchArtist
					searchGenre = contentSong.getGenre().toString(); //stores the contentSong genre to String searchGenre
					//condition to check if searchArtistMap does not contain the key searchArtist
					if(!searchArtistMap.containsKey(searchArtist)){
						indicesArtist.add(i); //if condition is true then adds i to indiciesArtist
						searchArtistMap.put(searchArtist, indicesArtist); //searchArtistMap is updated with the artist of the song, and the indice
					}else{ //else condition if searchArtistMap does contain the key searchArtist
						searchArtistMap.get(searchArtist).add(i); //the searchArtistMap gets the searchArtist and adds the current i value
					}

					//condition to check if searchGenreMap does not contain the key searchGenre
					if(!searchGenreMap.containsKey(searchGenre)){
						indicesGenre.add(i); //if condition is true then adds i to indicesGenre
						searchGenreMap.put(searchGenre, indicesGenre); //searchGenreMap is updated with the genre of the song, and the indice
					}else{ //else condition if searchGenreMap does contain the key searchGenre
						searchGenreMap.get(searchGenre).add(i); //the searchGenreMap gets the searchGenre and adds the current i value
					}
				//condition to check if the content is of AudioBook type
				}else if(contents.get(i).getType().equals(AudioBook.TYPENAME)){ 
					AudioBook contentAudioBook = (AudioBook) contentToFind; //if condition true, casts the contentAudioBook to AudioBook object
					searchArtist = contentAudioBook.getAuthor(); //stores the contentAudioBook to String searchArtist

					//condition to check if searchArtistMap does not contain the key searchArtist
					if(!searchArtistMap.containsKey(searchArtist)){
						indicesArtist.add(i); //if condition is true then add i to indiciesArtist
						searchArtistMap.put(searchArtist, indicesArtist); //searchArtistMap is updated with the artist of the song, and the indice
					}else{ //else condition if searchArtistMap does contain the key searchArtist
						searchArtistMap.get(searchArtist).add(i); //the searchArtistMap gets the searchArtist and adds the current i value

					}
				}
			}
		}

		//private method readFile that reads the store.txt file, returns an ArrayList of type AudioContent
		private ArrayList<AudioContent> readFile(String file) throws IOException{
			ArrayList <AudioContent> listOfAudio = new ArrayList<>();
			//try catch block
			try {
				File inputFile = new File(file); //creating a File variable inputFile to import store.txt 
				Scanner in = new Scanner(inputFile); //initializing a scanner in and assinging scanner to inputFile (store.txt)
				
				//while loop to check if theres another line
				while(in.hasNextLine()){
					String type = in.nextLine(); //checks next line for which type

					//condition to check if the type is SONG
					if(type.equals("SONG")){
						String id = in.nextLine(); //stores next line to String id
						String title = in.nextLine(); //stores next line to String title
						int year = in.nextInt(); //stores next integer to int year
						in.nextLine(); //goes to the next line
						int length = in.nextInt(); //stores the next integer to int length
						in.nextLine(); //goes to the next line
						String artist = in.nextLine(); //stores next line to String artist
						String composer = in.nextLine(); //stores next line to String composer
						String genre = in.nextLine(); //stores next line to String genre
						int numOfLines = in.nextInt(); //stores the next integer to int numOfLines
						in.nextLine(); //goes to the next line

						//initializes a String variable lyrics
						String lyrics = "";

						//loop through numOfLines and adds each line to lyrics
						for(int i = 0; i < numOfLines; i++) {
							lyrics += in.nextLine() + "\n";
						}
						//adds the song and its paramters/variables into the ArrayList listOfAudio
						listOfAudio.add(new Song(title, year, id, Song.TYPENAME, lyrics, length, artist, composer, Song.Genre.valueOf(genre), lyrics));

					//condition to check if the type is AUDIOBOOK
					}else if(type.equals("AUDIOBOOK")){

						ArrayList <String> chapTitles = new ArrayList<String>(); //initializing an ArrayList of Strings to record chapter Titles
						ArrayList <String> chaps = new ArrayList <String>(); //initializing an ArrayList of Strings to record chapters

						String id = in.nextLine(); //stores next line to String id
						String title = in.nextLine(); //stores next line to String title
						int year = in.nextInt(); //stores the next integer to int year
						in.nextLine(); //goes to the next line
						int length = in.nextInt(); //stores the next integer to int length
						in.nextLine(); //goes to the next line
						String author = in.nextLine(); //stores next line to String author
						String narrator = in.nextLine(); //stores next line to String narrator
						int numOfChaps = in.nextInt(); //stores the next integer to int numOfChaps
						in.nextLine(); //goes to the next line

						//loops through numOfChaps and adds each line to chapter Titles
						for(int i = 0; i < numOfChaps; i++){
							chapTitles.add(in.nextLine() + "\n");
						}

						//initializes String variable lyrics
						String lyrics = "";

						//loops through numOfChaps  
						for(int i = 0; i < numOfChaps; i++){
							int lyricsLength = in.nextInt(); //stores the next integer value to int lyricsLength
							in.nextLine(); //goes to the next line

							//loops through lyricsLength and adds each line to lyrics
							for(int j = 0; j < lyricsLength; j++){
								lyrics += in.nextLine() + "\n";
							}
							//adds lyrics to ArrayList chaps
							chaps.add(lyrics);
						}
						//adds the AudioBook and its parameters/variables into the ArrayList of listOfAudio
						listOfAudio.add(new AudioBook(title, year, id, AudioBook.TYPENAME, lyrics, length, author, narrator, chapTitles, chaps));

					}
				}
				//closes scanner
				in.close();

			//catches exception if file is not found and prints error message that file is not found
			} catch (FileNotFoundException ex) {
				System.out.println("File " + file + " is not found");
			}
			//returns listOfAudio after adding the Song and AudioBook content
			return listOfAudio;
		}

		// Search Methods (title, artist, genre) //

		//method to search for title and print contents, takes a String title and returns void
		public void search(String title){
			String errorMsg = ""; //declaring errorMsg String 
			//condition to check if title does not exist in the searchTitleMap
			if(searchTitleMap.get(title) == null){
				//if condition is true then it sets error message no matches found for the specific title
				errorMsg = ("No matches for " + title); 
				throw new NoMatchesForSearchException(errorMsg); //throws NoMatchesForSearchException
			}else{//else statement, that if title exists in the searchTitleMap then it performs this part
				int index = searchTitleMap.get(title); //creates an index variable and sets it to the value of title in searchTitleMap
				//condition to check if the key title is inside of the map searchTitleMap
				if(searchTitleMap.containsKey(title)){
					System.out.print("" + (index + 1) + ". "); //prints the index number on the screen
					contents.get(index).printInfo(); //prints the information after title is entered at index
					System.out.println(); //prints a new line for formatting	
				}
			}
		}

		//method to search for artist and print contents, takes a String artist and returns void
		public void searchA(String artist){
			String errorMsg = ""; //declaring errorMsg String 
			//condition to check if artist does not exist in the searchArtistMap
			if(searchArtistMap.get(artist) == null){
				//if condition is true then it sets error message no matches found for the specific artist
				errorMsg = ("No matches for " + artist); 
				throw new NoMatchesForSearchException(errorMsg); //throws NoMatchesForSearchException
			}else{//else statement, that if artist exists in the searchArtistMap then it performs this part
				//condition to check if the key artist is inside of the map searchArtistMap
				if(searchArtistMap.containsKey(artist)){
					//loops through the searchArtistMap that gets the parameter artist
					for(int index : searchArtistMap.get(artist)){
						System.out.print("" + (index + 1) + ". "); //prints the index number on the screen
						contents.get(index).printInfo(); //prints the information after artist is entered at index
						System.out.println(); //prints a new line for formatting
					}
				}
			}
		}

		//method to search for genre and print contents, takes a String genre and returns void
		public void searchG(String genre){
			String errorMsg = ""; //declaring errorMsg String 
			//condition to check if genre does not exist in the searchGenreMap
			if(searchGenreMap.get(genre) == null){
				//if condition is true then it sets error message no matches found for the specific genre
				errorMsg = ("No matches for " + genre); 
				throw new NoMatchesForSearchException(errorMsg); //throws NoMatchesForSearchException
			}else{ //else statement, that if genre exists in the searchGenreMap then it performs this part
				//condition to check if the key genre is inside of the map searchGenreMap
				if(searchGenreMap.containsKey(genre)){
					//loops through the searchGenreMap that gets the parameter genre
					for(int index : searchGenreMap.get(genre)){
						System.out.print("" + (index + 1) + ". "); //prints the index number on the screen
						contents.get(index).printInfo(); //prints the information after the genre is entered at index
						System.out.println(); //prints a new line for formatting
					}
				}
			}
		}

		// Download Methods (artist, genre) //

		//function to calculate the value of where artist is in searchArtistMap (used in MyAudioUI for downloada action)
		public ArrayList<Integer> downloadAIndex(String artist){
			String errorMsg = ""; // //declaring errorMsg String 
			ArrayList<Integer> results = new ArrayList <Integer>(); //initializes a new results ArrayList of type Integer
			//condition to check if artist does not exist in the searchArtistMap
			if(!searchArtistMap.containsKey(artist)){
				//if condition is true then it sets error message no matches found for the specific artist
				errorMsg = ("No matches for " + artist);
				throw new NoMatchesForDownloadException(errorMsg);
			}else{ //else statement, that if artist exists in the searchArtistMap then it performs this part
				results = searchArtistMap.get(artist); //sets results to the index of where artist is at in the searchArtistMap
			}
			return results; //returns results
		}

		//function to calculate the value of where genre is in searchGenreMap (used in MyAudioUI for downloadg action)
		public ArrayList<Integer> downloadGIndex(String genre){
			String errorMsg = ""; // //declaring errorMsg String 
			ArrayList<Integer> results = new ArrayList <Integer>(); //initializes a new results ArrayList of type Integer
			//condition to check if genre does not exist in the searchGenreMap
			if(!searchGenreMap.containsKey(genre)){ 
				//if condition is true then it sets error message no matches found for the specific genre
				errorMsg = ("No matches for " + genre);
				throw new NoMatchesForDownloadException(errorMsg);
			}else{ //else statement, that if genre exists in the searchGenreMap then it performs this part
				results = searchGenreMap.get(genre); //sets results to the index of where genre is at in the searchGenreMap
			}
			return results; //returns results
		}

		public AudioContent getContent(int index)
		{
			if (index < 1 || index > contents.size())
			{
				throw new NullPointerException("Invalid Start Index or Max Content Reached");
			}
			return contents.get(index-1);
		}
		
		public void listAll()
		{
			for (int i = 0; i < contents.size(); i++)
			{
				int index = i + 1;
				System.out.print("" + index + ". ");
				contents.get(i).printInfo();
				System.out.println();
			}
		}
		
		private ArrayList<String> makeHPChapterTitles()
		{
			ArrayList<String> titles = new ArrayList<String>();
			titles.add("The Riddle House");
			titles.add("The Scar");
			titles.add("The Invitation");
			titles.add("Back to The Burrow");
			return titles;
		}
		
		private ArrayList<String> makeHPChapters()
		{
			ArrayList<String> chapters = new ArrayList<String>();
			chapters.add("In which we learn of the mysterious murders\r\n"
					+ " in the Riddle House fifty years ago, \r\n"
					+ "how Frank Bryce was accused but released for lack of evidence, \r\n"
					+ "and how the Riddle House fell into disrepair. ");
			chapters.add("In which Harry awakens from a bad dream, \r\n"
					+ "his scar burning, we recap Harry's previous adventures, \r\n"
					+ "and he writes a letter to his godfather.");
			chapters.add("In which Dudley and the rest of the Dursleys are on a diet,\r\n"
					+ " and the Dursleys get letter from Mrs. Weasley inviting Harry to stay\r\n"
					+ " with her family and attend the World Quidditch Cup finals.");
			chapters.add("In which Harry awaits the arrival of the Weasleys, \r\n"
					+ "who come by Floo Powder and get trapped in the blocked-off fireplace\r\n"
					+ ", blast it open, send Fred and George after Harry's trunk,\r\n"
					+ " then Floo back to the Burrow. Just as Harry is about to leave, \r\n"
					+ "Dudley eats a magical toffee dropped by Fred and grows a huge purple tongue. ");
			return chapters;
		}
		
		private ArrayList<String> makeMDChapterTitles()
		{
			ArrayList<String> titles = new ArrayList<String>();
			titles.add("Loomings.");
			titles.add("The Carpet-Bag.");
			titles.add("The Spouter-Inn.");
			return titles;
		}
		private ArrayList<String> makeMDChapters()
		{
			ArrayList<String> chapters = new ArrayList<String>();
			chapters.add("Call me Ishmael. Some years ago never mind how long precisely having little\r\n"
					+ " or no money in my purse, and nothing particular to interest me on shore,\r\n"
					+ " I thought I would sail about a little and see the watery part of the world.");
			chapters.add("stuffed a shirt or two into my old carpet-bag, tucked it under my arm, \r\n"
					+ "and started for Cape Horn and the Pacific. Quitting the good city of old Manhatto, \r\n"
					+ "I duly arrived in New Bedford. It was a Saturday night in December.");
			chapters.add("Entering that gable-ended Spouter-Inn, you found yourself in a wide, \r\n"
					+ "low, straggling entry with old-fashioned wainscots, \r\n"
					+ "reminding one of the bulwarks of some condemned old craft.");
			return chapters;
		}
		
		private ArrayList<String> makeSHChapterTitles()
		{
			ArrayList<String> titles = new ArrayList<String>();
			titles.add("Prologue");
			titles.add("Chapter 1");
			titles.add("Chapter 2");
			titles.add("Chapter 3");
			return titles;
		}
		
		private ArrayList<String> makeSHChapters()
		{
			ArrayList<String> chapters = new ArrayList<String>();
			chapters.add("The gale tore at him and he felt its bite deep within\r\n"
					+ "and he knew that if they did not make landfall in three days they would all be dead");
			chapters.add("Blackthorne was suddenly awake. For a moment he thought he was dreaming\r\n"
					+ "because he was ashore and the room unbelieveable");
			chapters.add("The daimyo, Kasigi Yabu, Lord of Izu, wants to know who you are,\r\n"
					+ "where you come from, how ou got here, and what acts of piracy you have committed.");
			chapters.add("Yabu lay in the hot bath, more content, more confident than he had ever been in his life.");
			return chapters;
		}
		
		// Podcast Seasons
		/*
		private ArrayList<Season> makeSeasons()
		{
			ArrayList<Season> seasons = new ArrayList<Season>();
		  Season s1 = new Season();
		  s1.episodeTitles.add("Bay Blanket");
		  s1.episodeTitles.add("You Don't Want to Sleep Here");
		  s1.episodeTitles.add("The Gold Rush");
		  s1.episodeFiles.add("The Bay Blanket. These warm blankets are as iconic as Mariah Carey's \r\n"
		  		+ "lip-syncing, but some people believe they were used to spread\r\n"
		  		+ " smallpox and decimate entire Indigenous communities. \r\n"
		  		+ "We dive into the history of The Hudson's Bay Company and unpack the\r\n"
		  		+ " very complicated story of the iconic striped blanket.");
		  s1.episodeFiles.add("There is no doubt that the Klondike Gold Rush was an iconic event. \r\n"
		  		+ "But what did the mining industry cost the original people of the territory? \r\n"
		  		+ "And what was left when all the gold was gone? And what is a sour toe cocktail?");
		  s1.episodeFiles.add("here is no doubt that the Klondike Gold Rush was an iconic event. \r\n"
		  		+ "But what did the mining industry cost the original people of the territory? \r\n"
		  		+ "And what was left when all the gold was gone? And what is a sour toe cocktail?");
		  s1.episodeLengths.add(31);
		  s1.episodeLengths.add(32);
		  s1.episodeLengths.add(45);
		  seasons.add(s1);
		  Season s2 = new Season();
		  s2.episodeTitles.add("Toronto vs Everyone");
		  s2.episodeTitles.add("Water");
		  s2.episodeFiles.add("There is no doubt that the Klondike Gold Rush was an iconic event. \r\n"
		  		+ "But what did the mining industry cost the original people of the territory? \r\n"
		  		+ "And what was left when all the gold was gone? And what is a sour toe cocktail?");
		  s2.episodeFiles.add("Can the foundation of Canada be traced back to Indigenous trade routes?\r\n"
		  		+ " In this episode Falen and Leah take a trip across the Great Lakes, they talk corn\r\n"
		  		+ " and vampires, and discuss some big concerns currently facing Canada's water."); 
		  s2.episodeLengths.add(45);
		  s2.episodeLengths.add(50);
		 
		  seasons.add(s2);
		  return seasons;
		}
		*/
}
