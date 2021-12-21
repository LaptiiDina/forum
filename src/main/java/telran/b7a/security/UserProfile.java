package telran.b7a.security;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class UserProfile {
String login;
String password;
@Singular
Set<String>roles;
}
