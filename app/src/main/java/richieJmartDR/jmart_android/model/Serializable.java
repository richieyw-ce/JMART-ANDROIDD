package richieJmartDR.jmart_android.model;
/**
 *
 *
 * @author Richie Yoseph Wijaya
 * @version preSBA
 */
import java.util.HashMap;

public class Serializable implements Comparable<Serializable>
{
    public final int id;
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<>();

    protected Serializable(){
        if (!mapCounter.containsKey(getClass()))
            mapCounter.put(getClass(), -1);

        Integer counter = mapCounter.get(getClass());
        mapCounter.put(getClass(), ++counter);

        this.id = counter;
    }

    public boolean equals(Object instance){
        if (instance instanceof Serializable){
            Serializable r = (Serializable) instance;
            return (r.id == id);
        }
        return false;
    }

    @Override
    public int compareTo(Serializable other){
        return Integer.compare(this.id, other.id);
    }

    public static <T extends Serializable> Integer setClosingId(Class<T> instance, int id){
        return mapCounter.put(instance, id);
    }

    public static <T extends Serializable> Integer getClosingId(Class<T> instance){
        return mapCounter.get(instance);
    }

    public boolean equals(Serializable instance){
        return instance.id == id;
    }
}

