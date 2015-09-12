import com.aisim.dal.ProbesSqlLiteDao;
import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteStatement;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Krzysztof on 2/19/2015.
 */

public class ProbesSqlLiteDaoTests {
	private final int TEST_EVOLUTION_ID = -999;
	private final String DB_FILENAME = "../database";

	private ProbesSqlLiteDao repo;

	public ProbesSqlLiteDaoTests() {
		repo = new ProbesSqlLiteDao(DB_FILENAME);
	}

	@Test
	public void basicSqlLiteTest() throws Exception {
		List<Integer> results = new LinkedList<Integer>();
		SQLiteConnection db = new SQLiteConnection(new File(DB_FILENAME));
		db.open(true);
		SQLiteStatement st = db.prepare("SELECT ?");
		try {
			st.bind(1, 123);
			while (st.step()) {
				results.add(st.columnInt(0));
			}
		} finally {
			st.dispose();
		}
		db.dispose();
		assertTrue(results.size() == 1);
		assertTrue(results.get(0) == 123);
	}

	@Test
	public void saveTest() throws Exception {
		repo.save(TEST_EVOLUTION_ID, 1, 1, 0.1f, new ArrayList<Float>() {
			{
				add(0.5f);
				add(1f);
			}
		});

		SQLiteConnection db = new SQLiteConnection(new File(DB_FILENAME));
		db.open(true);
		SQLiteStatement st = db.prepare("SELECT * FROM probe where evolutionId = ?");
		st.bind(1, TEST_EVOLUTION_ID);
		assertTrue(st.step());
		assertFalse(st.step());
	}

	@After
	public void tearDown() throws Exception {
		SQLiteStatement st = null;
		SQLiteConnection db = new SQLiteConnection(new File(DB_FILENAME));
		try {
			db.open(true);
			st = db.prepare("DELETE FROM probe where evolutionId = ?");
			st.bind(1, TEST_EVOLUTION_ID);
			st.step();
		} finally {
			db.dispose();
			if (st != null)
				st.dispose();
		}
	}
}
