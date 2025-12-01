public class Users {
    @SuppressWarnings("FieldMayBeFinal")
    private String username;
    @SuppressWarnings("FieldMayBeFinal")
    private String password;

    public Users(String username, String password){
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
