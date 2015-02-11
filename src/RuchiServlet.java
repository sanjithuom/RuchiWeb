
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruchi.data.PersistentManager;
import com.ruchi.logic.TableCreator;
/**
 * 
 * @author SANJI
 *	This Servlet is used to get the restaurant and food details from database
 */
@WebServlet("/RuchiServlet")
public class RuchiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get the parameters from the request
        String city = request.getParameter("city");
        String option = request.getParameter("option");
        String search = request.getParameter("search");
        String responseResult;
        // if food based search
        if(option.equals("food")){
        	HashMap<String, Double> foodRating = PersistentManager.getFoodRating(city, search);
        	//if food doesn't exist in database send error message
        	if(foodRating==null||foodRating.isEmpty()){
        		responseResult = "Error: Cannot find the food";
        	}
        	else{
        		responseResult = TableCreator.getFoodTable(foodRating);
        	}
        	
        }
        // if restaurant based search
        else{
        	HashMap<String, Double> restaurantRating = PersistentManager.getRestaurantRating(city, search);
        	//if restaurant doesn't exist in database send error message
        	if(restaurantRating==null||restaurantRating.isEmpty()){
        		responseResult = "Error: Cannot find the restaurant";
        	}
        	else{
        		responseResult = TableCreator.getFoodTable(restaurantRating);
        	}
        }

        response.setContentType("text/plain");
        response.getOutputStream().write(responseResult.getBytes());
    }

}
