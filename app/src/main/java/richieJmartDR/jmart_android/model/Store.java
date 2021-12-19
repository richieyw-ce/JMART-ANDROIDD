package richieJmartDR.jmart_android.model;
/**
 *
 *
 * @author Richie Yoseph Wijaya
 * @version preSBA
 */
import java.util.regex.Pattern;

public class Store extends Serializable{
    public static final String REGEX_NAME = "^[A-Z]((?!\\s{2,})[A-Za-z\\s]){3,19}$";
    public static final String REGEX_PHONE = "\\d{9,12}";
    public String address;
    public double balance;
    public String name;
    public String phoneNumber;

    public Store(String name, String address, String phoneNumber, double balance){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    public boolean validate(){
        Pattern namePattern = Pattern.compile(REGEX_NAME);
        Pattern phonePattern = Pattern.compile(REGEX_PHONE);

        return
                namePattern.matcher(this.name).find() &&
                        phonePattern.matcher(this.phoneNumber).find();
    }

    public String toString(){
        return
                "name: " + this.name + "\n" +
                        "address: " + this.address + "\n" +
                        "phoneNumber: " + this.phoneNumber + "\n";
    }
}
