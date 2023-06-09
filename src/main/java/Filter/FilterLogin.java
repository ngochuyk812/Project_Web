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
        if(session.getAttribute("user") != null){
            filterChain.doFilter(httpReq, httpResp);
            System.out.println("Filter Login: OK ");

        }else{
            System.out.println("Filter Login:Not OK ");

            String contextPath = httpReq.getContextPath();
            httpResp.sendRedirect(contextPath + "/login");

        }
    }

    @Override
    public void destroy() {

    }
}
