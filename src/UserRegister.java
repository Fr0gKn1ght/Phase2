import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.example.HibernateUtil;
import com.example.User;

/**
 * Servlet implementation class UserRegister
 */
@WebServlet
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        try {    
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<b>User Registration</b><br>");
                out.println("<form name=frm method=post action=\"register\"><br>");
                out.println("Name <input type = text name=\"name\" id = \"name\"><br>");
                out.println("Email <input type = text name = \"email\" id = \"email\"><br>");
                out.println("Password <input type = text name = \"password\" id = \"password\"><br>");
                out.println("<button>Submit</button>");
                out.println("</form>");
                out.println("</body>");
                out.println("</html>");
            
            
        } catch (Exception ex) {
        		System.err.println(ex.getMessage());
                throw ex;
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try
		{
			PrintWriter out = response.getWriter();
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String pword = request.getParameter("password");
			
			if((name == null || name.isBlank()) || (email == null || email.isBlank()) || (pword == null || pword.isBlank()))
			{
				out.println("<html><body>");
				out.println("<b>Invalid Entry, please fill out all forms!</b></br>");
				out.println("</body></html>");
				doGet(request,response);
				return;
			}
			SessionFactory factory = HibernateUtil.getSessionFactory();
			Session session = factory.openSession();
			
			//Determine if there is an account already associated with the email.
			long f = (Long)session.createQuery("select count(email) from User users where email=\'"+request.getParameter("email")+"\'").uniqueResult();
			if(f > 0)
			{
				out.println("<html><body>");
				out.println("<b>Entry Already Exists, please try again!</b><br>");
				out.println("</body></html>");
				session.close();
				doGet(request,response);
			}
			
			else
			{
				session.beginTransaction();
				//New User Object
				User u = new User();
				u.setName(name);
				u.setEmail(email);
				u.setPassword(pword);
				session.save(u);
				session.getTransaction().commit();
				session.close();
				response.sendRedirect("Confirm.jsp");
			}
			
		}
		
		catch(Exception ex)
		{
			throw ex;
		}

		//doGet(request, response);
	}

}
