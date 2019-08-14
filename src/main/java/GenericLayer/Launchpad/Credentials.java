package GenericLayer.Launchpad;

public class Credentials {
    private String username;
    private String password;


    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }

    @Override
    public String toString() {
        return "Credentials{username='" + this.username + '\''+ ", password='" + this.password + '\'' + '}';
    }

    @Override
    public boolean equals(Object credentials) {
        Credentials creds=(Credentials)credentials;
        if(this.username.equals(creds.username) && this.password.equals(creds.password))return true;
        else return false;
    }
}