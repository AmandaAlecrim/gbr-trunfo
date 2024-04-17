package app.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import model.dto.UserPrincipalDTO;

import java.security.Principal;

public class TokenRequestWrapper extends HttpServletRequestWrapper {

    private UserPrincipalDTO principal;

    public TokenRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public TokenRequestWrapper(HttpServletRequest request, UserPrincipalDTO principal) {
        super(request);
        this.principal = principal;
    }

    @Override
    public UserPrincipalDTO getUserPrincipal() {
        return this.principal;
    }

    @Override
    public boolean isUserInRole(String role) {
        return true;
    }
}
