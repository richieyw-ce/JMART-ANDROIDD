/**
 *
 *
 * @author Richie Yoseph Wijaya
 * @version 19/12/2021
 */

package richieJmartDR.jmart_android;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import richieJmartDR.jmart_android.model.Product;
/**
 *
 *
 * @author Richie Yoseph Wijaya
 * @version preSBA
 */
public class ProductsAdapter extends ArrayAdapter<Product> {
    private int resourceLayout;
    private Context mContext;

    public ProductsAdapter(Context context, int resource, ArrayList<Product> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        Product p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.productItemName);
            if (tt1 != null) {
                tt1.setText(p.name);

            }
        }

        return v;
    }
}