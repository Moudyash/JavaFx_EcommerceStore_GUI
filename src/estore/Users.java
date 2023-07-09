package estore;

public class Users {

    private int userid;
    private String username;
    private String userpassword;

    public Users(int userid, String username, String userpassword) {
        this.userid = userid;
        this.username = username;
        this.userpassword = userpassword;

    }

    public Users() {

    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    @Override
    public String toString() {
        return "Users{" + "userid=" + userid + ", username=" + username + ", userpassword=" + userpassword + '}';
    }

}
