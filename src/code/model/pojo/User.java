package code.model.pojo;

/**
 *
 */
public class User {

    private String login;
    private String mail;
    private String password;
    private int accountType;
    private int locked;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String login, String mail, String password, int accountType, int locked) {
        this.login = login;
        this.mail = mail;
        this.password = password;
        this.accountType = accountType;
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", accountType=" + accountType +
                ", locked=" + locked +
                '}';
    }
}
