package annotations.examples.example01;

// using annotaion Command
// the name `value` is a special name which allow to define annotation like this @Command("login")
// if annotation only has value element
@Command(value = "login", order = 1, description = "Login Command")
public class LoginCommand {

}
