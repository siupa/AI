import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteStatement;
import org.junit.Test;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Krzysztof on 2/19/2015.
 */

public class SqlLiteTest {

    @Test
    public void basicTest() throws Exception {

        List<Object> results = new LinkedList<Object>();
        SQLiteConnection db = new SQLiteConnection(new File("../database"));
        db.open(true);
        SQLiteStatement st = db.prepare("SELECT ?");
        try {
            st.bind(1, 123);
            while (st.step()) {
                results.add(st.columnLong(0));
            }
        } finally {
            st.dispose();
        }
        db.dispose();
    }
}
