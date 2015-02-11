

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruchi.data.PersistentManager;
import com.ruchi.logic.JacksonParsor;
import com.ruchi.logic.TypeAhead;

/**
 * Servlet implementation class CityServlet
 * This Servlet is used to get the city list from the database
 */
@WebServlet("/CityServlet")
public class CityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * This method is used to get city list from database by client
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//city list from database
		List<String> local = PersistentManager.getCityList();
		//creating json for insert in text box typeahead
		TypeAhead typeAhead = new TypeAhead("cities", local);
        String responseResult = JacksonParsor.toJson(typeAhead);

        response.setContentType("application/json");
        response.getOutputStream().write(responseResult.getBytes());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
