package telran.b7a.security;

import telran.b7a.accaunt.model.User;

public interface SecurityContex {
boolean addUser(UserProfile user);
UserProfile removeUser(String login);
UserProfile getUser(String login);
}
