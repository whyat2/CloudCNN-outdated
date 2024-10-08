package servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "MainServlet", urlPatterns = "/") //urlPatterns = {"/x/* */", "/y", "/x/y"} allows multiple
public class MainServlet extends HttpServlet{
    @Override
    public void init() throws ServletException{
        super.init();
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
