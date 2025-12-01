import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.util.List;

public class UserService {

    private List<Users> users;

    public UserService() {
        loadUsers();
    }

    @SuppressWarnings({"UseSpecificCatch", "ConvertToTryWithResources"})
    private void loadUsers() {
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader("data/users.json");

            users = gson.fromJson(reader, new TypeToken<List<Users>>() {}.getType());

            reader.close();

            System.out.println("Loaded users: ");
            for (Users u : users) {
            System.out.println(u.getUsername() + " - " + u.getPassword());
        }
        } catch (Exception e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

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
