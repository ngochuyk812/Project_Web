package Filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FilterLogin implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResp = (HttpServletResponse) servletResponse;
        HttpSession session = httpReq.getSession();
        System.out.println("Filter Login: ");
        if(session.getAttribute("user") != null){
            filterChain.doFilter(httpReq, httpResp);

        }else{
            httpReq.getRequestDispatcher("/Page/Login.jsp").forward(httpReq, httpResp);

        }
    }

    @Override
    public void destroy() {

    }
}
