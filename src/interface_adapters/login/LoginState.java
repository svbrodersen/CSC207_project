package interface_adapters.login;

import interface_adapters.defaultState;

public class LoginState extends defaultState {
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;
    private boolean returning = false;

    public LoginState(LoginState copy) {
        username = copy.username;
        usernameError = copy.usernameError;
        password = copy.password;
        passwordError = copy.passwordError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoginState() {}

    public String getUsername() {
        return username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public boolean isReturning() {
        return returning;
    }

    public void setReturning(boolean returning) {
        this.returning = returning;
    }
}
