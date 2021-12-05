package richieJmartDR.jmart_android.model;

import java.util.regex.Pattern;

public class Account extends Serializable{
    public static final String REGEX_EMAIL = "^(?!\\.)([\\dA-Za-z&_*~]+\\.?)+@([\\dA-Za-z]+\\.?)+[\\dA-Za-z]+$";
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
    public double balance;
    public String name;
    public String email;
    public String password;
    public Store store;

    public Account(String name, String email, String password, double balance)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public boolean validate(){
        Pattern emailPattern = Pattern.compile(REGEX_EMAIL);
        Pattern passwordPattern = Pattern.compile(REGEX_PASSWORD);

        return
                emailPattern.matcher(this.email).find() &&
                        passwordPattern.matcher(this.password).find();
    }

    public String toString(){
        return
                "name: " + this.name + "\n" +
                        "email: " + this.email + "\n" +
                        "password: " + this.password + "\n";
    }
}
