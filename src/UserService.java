import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private List<Users> users;

    public UserService() {
        users = loadUsers();  
        if (users == null) {
            users = new ArrayList<>(); // fallback
        }
    }

    @SuppressWarnings({"UseSpecificCatch", "ConvertToTryWithResources"})
    //-----------------Load Users from json-------------------//
    private List<Users> loadUsers() {
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader("data/users.json");

            List<Users> list = gson.fromJson(reader, new TypeToken<List<Users>>() {}.getType());

            reader.close();
            return list;

        } catch (Exception e) {
            System.out.println("No existing user found. Starting fresh");
            return new ArrayList<>();
        }
    }
//-----------------------------Save to json--------------------------------------//
    @SuppressWarnings({"ConvertToTryWithResources", "CallToPrintStackTrace", "UseSpecificCatch"})
    public void saveUsers(){
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter writer = new FileWriter("data/users.json");

            gson.toJson(users,writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//----------------------------Check if user exists-----------------------------------//
    public boolean usernameExists(String username){
        for(Users u: users){
            if(u.getUsername().equalsIgnoreCase(username)){
                return true;
            }
        }

        return false;
    }
//--------------------------Register new Users------------------------------------------//
    public boolean registerUser(String username, String password){
        if(usernameExists(username)){
            return false;
        }

        Users newUser = new Users(username,password);
        users.add(newUser);
        saveUsers();
        return true;
    }
//---------------------------Validate User login-----------------------------------------//
    public boolean validateLogin(String username, String password) {
        if (users == null) return false;

        for (Users u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
