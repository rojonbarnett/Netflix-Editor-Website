//Chris Baugh
//CSCI 3381
//Project 1
package project1;

import java.io.File;
import java.util.ArrayList;

public class P1MainTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//tests using hard coded data
		
		//test empty constructor
		ShowWeek s1 = new ShowWeek();
		System.out.println(s1);
		
		//test constructor
		ShowWeek s2 = new ShowWeek("2022-09-04", "Films(English)","1","Me Time","N/A","56560000","2");
		System.out.println(s2);
		
		//test setters/getters
		s1.setWeek("new week");
		s1.setCategory("new category");
		s1.setRank("new rank");
		s1.setShowTitle("new title");
		s1.setSeasonTitle("new season title");
		s1.setHrsViewed("new hrs viewed");
		s1.setWeeksTop10("new weeks top ten");
		System.out.println(s1.getWeek());
		System.out.println(s1.getCategory());
		System.out.println(s1.getRank());
		System.out.println(s1.getShowTitle());
		System.out.println(s1.getSeasonTitle());
		System.out.println(s1.getHrsViewed());
		System.out.println(s1.getWeeksTop10());
		System.out.println(s1);
		//System.out.println();
		
		//more test data
		ShowWeek s3 = new ShowWeek("2023-09-04", "Films(English)","1","Me Time","N/A","56560000","2");
		ShowWeek s4 = new ShowWeek("2023-09-04", "Films(English)","1","terminator","N/A","66560000","7");
		ShowWeek s5 = new ShowWeek("2023-10-04", "Films(English)","1","something else","N/A","89960000","9");
		ShowWeek s6 = new ShowWeek("2023-10-04", "Films(English)","1","Test Data","N/A","89960000","10");
		
		//test equals method
		System.out.println(s1.equals(s2)); //false
		System.out.println(s2.equals(s2));//true
		System.out.println(s2.equals(s3));//expect false from week differ
		System.out.println(s2.equals(new ShowWeek("Me Time", "2022-09-04")));//expect true
		System.out.println(s2.equals(new ShowWeek("Me Time", "2023-09-04")));//expect false
		System.out.println(s4.equals(new ShowWeek("Me Time", "2023-09-04")));//expect false
		s4.setWeeksTop10("15");
		s5.setWeeksTop10("20");
		
		//test Shows class
		//build test list
		Shows myList = new Shows();
		//test adding new data
		myList.addShowWeek(s1);
		myList.addShowWeek(s2);
		myList.addShowWeek(s3);
		myList.addShowWeek(s4);
		myList.addShowWeek(s5);
		myList.addShowWeek(s6);
		System.out.println("\nMy test Data\n"+myList);
		
		//test find 
		//returns all data for the show specified by showTitle and week
		System.out.println("Found me!\n"+myList.find("Me Time", "2022-09-04"));
		System.out.println("Found me!\n"+myList.find("terminator", "2023-09-04"));
		
		//returns all shows in a given week
		System.out.println(myList.getOneWeek("2023-09-04"));
		
		//returns a random show from the list
		System.out.println("Random Show\n"+myList.randomShow());
		System.out.println("Random Show\n"+myList.randomShow());
		System.out.println("Random Show\n"+myList.randomShow());
		
		//returns a suggested show based on watched show
		System.out.println("Suggested show\n"+myList.suggestShow("Me Time", "2022-09-04"));
		System.out.println("Suggested show\n"+myList.suggestShow("Me Time", "2022-09-04"));
		
		//test suggestShowTrend()
		// returns a list of shows based on viewing history
		//creates list of my watched shows
		Shows iWatched = new Shows();
		iWatched.addShowWeek(s2);
		iWatched.addShowWeek(s3);
		iWatched.addShowWeek(s4);
		//returns a list of 5 suggested shows based on your watching trend
		System.out.println("Shows you might like\n"+myList.suggestShowTrend(iWatched));
		
		//test purge
		System.out.println("List before purge\n"+myList);
		myList.purgeShow("terminator");
		System.out.println("List after purge\n"+myList);
		
		//test random of only unpurged shows
		System.out.println("each run should not produce terminator");
		System.out.println(myList.randomShow());	
		
		//test unpurge
		myList.unPurgeShow("terminator");
		System.out.println("List after unpurge\n"+myList);
		
		//returns a list of showWeeks based on criteria
		System.out.println("Today's top shows:\n"+myList.getTopShows());
		
		//read file in and run methods again
		Shows allData = new Shows("allData","./project1/netflixAllWeeksGlobalProcessed.txt");
		//System.out.println(allData); //test if read worked, commented out to prevent console overload
		
		//Get random show
		System.out.println("Random Show\n"+allData.randomShow());
		System.out.println("Random Show\n"+allData.randomShow());
		
		//Find a showInWeek and edit it
		System.out.println("Found me!\n"+allData.find("Business Proposal", "2022-05-29"));
		ShowWeek editShowWeek = allData.find("Business Proposal", "2022-05-29");
		editShowWeek.setRank("99999");
		System.out.println("the rank is changed\n"+allData.find("Business Proposal", "2022-05-29"));
		
		//get all shows from one week
		System.out.println(allData.getOneWeek("2021-08-29"));
		
		//watch a show, suggest a show
		System.out.println("Suggested show\n"+allData.suggestShow("The Secret Life of Pets", "2021-08-29"));
		System.out.println("Suggested show\n"+allData.suggestShow("The Last Mercenary", "2021-08-29"));
		
		//setup for suggesting shows based on viewing history
		Shows showsWatched = new Shows();
		ShowWeek seenIt1 = allData.find("The Chair", "2021-08-29");
		ShowWeek seenIt2 = allData.find("Look Both Ways", "2022-09-04");
		ShowWeek seenIt3 = allData.find("My Wonderful Life", "2022-03-06");
		ShowWeek seenIt4 = allData.find("Red Notice", "2021-12-26");
		showsWatched.addShowWeek(seenIt1);
		showsWatched.addShowWeek(seenIt2);
		showsWatched.addShowWeek(seenIt3);
		showsWatched.addShowWeek(seenIt4);
		System.out.println("I have watched:\n"+showsWatched);
		//returns 5 suggested shows 
		System.out.println("Suggested List\n"+allData.suggestShowTrend(showsWatched));
		
		//purge show
		allData.purgeShow("The Chair");
		//Check if movie is purged
		System.out.println("Your search for The Chair returned this result:\n"+allData.find("The Chair","2021-08-29"));
		
		//unpurge show
		allData.unPurgeShow("The Chair");
		//Check to see if movie is unpurged
		System.out.println(allData.find("The Chair","2021-08-29"));
		
		//getTopShows. Expect return of all shows with weeksTop10 = 15
		System.out.println("Today's top shows:\n"+allData.getTopShows());
		

		//write to file
		//myList.writeFile("./project1/dataWriteTest.txt");
		allData.writeFile("./project1/dataWriteTest.txt");
		
	
	}

}
