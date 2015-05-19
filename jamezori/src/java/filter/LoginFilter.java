/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package filter;

import jamezori.Login;
import java.io.IOException;
import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 5ia13jathomas
 */
public class LoginFilter implements Filter {
    
        @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession httpSession = req.getSession(true);
        
        Login auth = (Login) httpSession.getAttribute("isLogged");

        if (req.getRequestURI().startsWith(req.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER)) {
            chain.doFilter(request, response);
            return;
        }
        if ((auth != null && auth.isIsLogged())
                || req.getServletPath().equals("/index.xhtml")
                || req.getServletPath().equals("/register.xhtml")) {

            if (auth != null && auth.isIsLogged()
                    && (req.getServletPath().equals("/index.xhtml")
                    || req.getServletPath().equals("/register.xhtml"))) {

                HttpServletResponse res = (HttpServletResponse) response;
                res.sendRedirect(req.getContextPath() + "/home.xhtml");
            }
        }
        else {
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect(req.getContextPath() + "/index.xhtml");
        }
    }

    @Override
    public void init(FilterConfig cfg) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
