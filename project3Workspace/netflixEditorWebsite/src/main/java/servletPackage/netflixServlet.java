//RoJon Barnett
//Netflix Editor Website
//CSCI 3381 Object Oriented Programming with Java
//Netflix editor website created with an html page and a series of Java Servlet Pages that communicate through the servlet
package servletPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class netflixServlet
 */
@WebServlet("/netflixServlet")
public class netflixServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private Shows allData;  //Will contain netflix movie data
    private String userName;//Will contain the user-inputted username
    private String passWord;//Will contain the user-inputted password
    private String EnterButtonName;//Button for the user to enter the website
    private boolean verified;//Boolean that determines whether the user's password and username has been authenticated
    private RequestDispatcher rd;//Variable that directs the user to the apppropriate web file
    private String parameter1;//Contains the selectionList Parameter
    private String label1Value;//Contains the selectionText
    private String title;//Title of the movies
    private String purgeButtonName;//Button that will purge a show
    private String selection;//Option within the selection list the user chooses
    private String performButtonName;//Performs the radio button selection
    private String radioVal;//Contains the radio button selection from the group
    private String viewTopMoviesButtonName;//Button that directs the user to the web page that shows the top movies
    private String AddNewMovieButton;
    private String AddMovieButton;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public netflixServlet() {
        super();
        allData = new Shows("allData","./servletPackage/netflixAllWeeksGlobalProcessed.txt");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//Initialize username and password variable
		userName = request.getParameter("username"); 	
		passWord = request.getParameter("password"); 	
		request.setAttribute("username",userName);
		request.setAttribute("password",passWord);
		
		
		
		 EnterButtonName = request.getParameter("Enter");
		 purgeButtonName = request.getParameter("purgeButton");
		 performButtonName = request.getParameter("Perform");
		 viewTopMoviesButtonName = request.getParameter("topMovieButton");
		 AddNewMovieButton = request.getParameter("AddNewMovie");
		 AddMovieButton = request.getParameter("AddMovie");
		 
		 
		 radioVal = request.getParameter("navigationMenu");
		
		//If user selects the enter button
		if(EnterButtonName!=null)
		{
			try {
			
				//Code to authenticate password
			    verified = false;
				if (userName.equals("md") && passWord.equals("pw"))
				{
					verified = true;
				}
				if (!verified)
				{
					//Initialize java request dispatcher to the index.html
					 rd = request.getRequestDispatcher("/index.html");
					 
				}
				else 
				{
					//Populate selection list
					parameter1="selectionList";
				    label1Value = "<select name=\"movies\">";
				    //Loop through all of the titles in the data set
					for(ShowWeek showWeek : allData.getShowWeeks()) 
					{
					    title = showWeek.getShowTitle();
						label1Value += "<option value=\""+title+"\">"+title+"\"</option>";
					}
					
					
				    //Send the selection list to the netflix.jsp file
					request.setAttribute(parameter1, label1Value);
					//Initialize Java's request dispatcher to netflixHome.jsp
				    rd = request.getRequestDispatcher("/netflixHome.jsp");
					
				}
				//Send user to the jsp page
				rd.forward(request, response);
			
			
			}catch(Exception e){
			e.printStackTrace();
		    }
			
		}
		//If the user selects the purge
		else if (purgeButtonName != null)
		{
			//Purge show
			selection = request.getParameter("movies");
			if(allData.purgeCheck(selection)== true)
			{
				allData.unPurgeShow(selection);
			}
			else if(allData.purgeCheck(selection)== false)
			{
				allData.purgeShow(selection);
			}
			
			
			rd = request.getRequestDispatcher("/purgePage.jsp");
			
			rd.forward(request,response);
			
			
		}
		//If the user selects the perform button
		else if( performButtonName != null)
		{
		    
			if("Return Home".equals(radioVal))
			{	
				//Recreate selection list
				 parameter1="selectionList";
				 label1Value = "<select name=\"movies\">";
				 for(ShowWeek showWeek : allData.getShowWeeks()) 
				 {
					 title = showWeek.getShowTitle();
					 label1Value += "<option value=\""+title+"\">"+title+"\"</option>";
				 }
					request.setAttribute(parameter1, label1Value);
					
				//Return to netflix home
				rd = request.getRequestDispatcher("/netflixHome.jsp");
				rd.forward(request,response);
				
			}
			else
			{
				rd = request.getRequestDispatcher("/index.html");
				rd.forward(request,response);
			}
			
			
		}
		//If the user selects the top movies button
		else if(viewTopMoviesButtonName != null)	
		{
			//Creates and populates the selection list of the top movies
			 parameter1 = "weeksSelectionList";
			 label1Value = "<select name=\"TopMovies\">";
			 for(ShowWeek showWeek : allData.getTopShows()) 
			 {
				 title = showWeek.getShowTitle();
				 label1Value += "<option value=\""+title+"\">"+title+"\"</option>";
			 }
				request.setAttribute(parameter1, label1Value);
			//Direct to add movie page
			rd = request.getRequestDispatcher("/netflixTopMovies.jsp");
			rd.forward(request,response);
			
			//Recreate selection list for home page
//			 parameter1="selectionList";
//			 label1Value = "<select name=\"movies\">";
//			 for(ShowWeek showWeek : allData.getShowWeeks()) 
//			 {
//				 title = showWeek.getShowTitle();
//				 label1Value += "<option value=\""+title+"\">"+title+"\"</option>";
//			 }
//				request.setAttribute(parameter1, label1Value);
			
			
		}
		//If the user selects the add new movie button
		else if (AddNewMovieButton != null)
		{
			rd = request.getRequestDispatcher("/AddAMovie.jsp");
			rd.forward(request,response);
		}
		//When the user selects the add movie button and the text area is not
		else if (AddMovieButton != null && request.getParameter("showTitleTextArea")!= "")
		{
			//Get show from text area and add it
			String addedMovie = request.getParameter("showTitleTextArea");
			ShowWeek addedShow = new ShowWeek("1","2","3",addedMovie,"5","6","7");
			allData.addShowWeek(addedShow);
			
			//Re-populate selectionList and go home
			 parameter1="selectionList";
			 label1Value = "<select name=\"movies\">";
			 for(ShowWeek showWeek : allData.getShowWeeks()) 
			 {
				 title = showWeek.getShowTitle();
				 label1Value += "<option value=\""+title+"\">"+title+"\"</option>";
			 }
				request.setAttribute(parameter1, label1Value);
				
			//Return to netflix home
			rd = request.getRequestDispatcher("/netflixHome.jsp");
			rd.forward(request,response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
