/**
 *
 *
 * @author Richie Yoseph Wijaya
 * @version preSBA
 */
package richieJmartDR.jmart_android.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest{
    public static final String URL = "http://10.0.2.2:6969/account/register";
    public final Map<String, String> params;
    public RegisterRequest(String name,
                           String email,
                           String password,
                           Response.Listener<String> listener,
                           Response.ErrorListener errorListener)
    {
        super(Request.Method.POST,
                URL,
                listener,
                errorListener);
        params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);
    }

    public Map<String, String> getParams(){
        return params;
    }

}
