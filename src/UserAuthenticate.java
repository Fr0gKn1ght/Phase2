import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
 * Servlet implementation class UserAuthenticate
 */
@WebServlet
public class UserAuthenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAuthenticate() {
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
                out.println("<b>Login</b><br>");
                out.println("<form name=frm method=post action=\"login\"><br>");
                out.println("Email <input type = text name = \"email\" id = \"email\"><br>");
                out.println("Password <input type = text name = \"password\" id = \"password\"><br>");
                out.println("<button>Submit</button>");
                out.println("</form>");
                out.println("</body>");
                out.println("</html>");
                out.println("</body></html>");
            
            
        } catch (Exception ex) {
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
			String email = request.getParameter("email");
			String pword = request.getParameter("password");
			
			if((email == null || email.isBlank()) || (pword == null || pword.isBlank()))
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
			List<User> list = session.createQuery("from User where email = \'"+email+"\'", User.class).list();
			session.close();
			if(list.size() > 0)
			{
				if(list.get(0).getPassword().equals(pword))
				{
					response.sendRedirect("landing.jsp");
				}
				
				else
				{
					out.println("<html><body>");
					out.println("<b>Incorrect Password! Please try again.</b></br>");
					out.println("</body></html>");
					doGet(request,response);
					return;
				}
			}
			
			else
			{
				out.println("<html><body>");
				out.println("<b>No Account Associated With This Email! Please try another.</b></br>");
				out.println("</body></html>");
				doGet(request,response);
				return;
			}
		}
		
		catch(Exception ex)
		{
			throw ex;
		}
   
               
		doGet(request, response);
	}

}
