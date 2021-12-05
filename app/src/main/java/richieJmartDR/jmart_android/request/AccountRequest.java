package richieJmartDR.jmart_android.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class AccountRequest{

    public static StringRequest createStore(
            int accountId,
            String name,
            String address,
            String phone,
            Response.Listener<String> listener,
            Response.ErrorListener errorListener
    )  {
        return new StringRequest(
                Request.Method.POST,
                String.format(
                        "http://10.0.2.2:6969/account/%d/registerStore"
                        + "?name=%s"
                        + "&address=%s"
                        + "&phoneNumber=%s",
                        accountId,
                        name,
                        address,
                        phone
                ),
                listener,
                errorListener
        );
    }
}
