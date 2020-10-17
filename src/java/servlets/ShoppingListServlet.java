package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
                        
        if(action != null) {
            
            if(action.equals("logout")){
                session.invalidate();
                response.sendRedirect("/Week05Lab/ShoppingList");
            }
        } else {
            
            if(session.getAttribute("username") != null) {
                request.setAttribute("user", session.getAttribute("username"));
//              request.setAttribute("items", (ArrayList<String>) session.getAttribute("items"));
                getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request, response);
            
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String action = request.getParameter("action");
        String item = request.getParameter("addItem");
        ArrayList<String> items = new ArrayList<>();
        
        if(session.getAttribute("username") == null) {
            session.setAttribute("username", username);
        }
               
        request.setAttribute("user", session.getAttribute("username"));
                
        if(action.equals("add")) {
            if(item != null ) {
                if(session.getAttribute("items") != null) {
                    items = (ArrayList<String>) session.getAttribute("items");
                }
                items.add(item);
                session.setAttribute("items", items);
//              request.setAttribute("items", items);
            }        
                           
        } else if(action.equals("delete")){
            items = (ArrayList<String>) session.getAttribute("items");
            String checked_item = request.getParameter("item");
            
            for(int i = 0; i < items.size(); i++) {
                
                if(items.get(i).equals(checked_item)) {
                    items.remove(i);
////                session.setAttribute("items", items);
//                  getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request, response);
                }
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request, response);
               
    }  
}
