package richieJmartDR.jmart_android.model;
/**
 *
 *
 * @author Richie Yoseph Wijaya
 * @version preSBA
 */
public class Product extends Serializable{
    public int accountId;
    public ProductCategory category;
    public boolean conditionUsed;
    public double discount;
    public String name;
    public double price;
    public byte shipmentPlans;
    public int weight;
}
