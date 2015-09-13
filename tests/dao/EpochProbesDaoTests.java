package dao;

import com.aisim.dal.EpochProbesDao;
import com.aisim.dal.EpochProbesMongodbDao;
import com.aisim.dal.EpochProbesSqlLiteDao;
import com.aisim.dal.Probe;
import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteStatement;
import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * ai
 * Created by Krzysztof Slupski on 2/19/2015.
 */

public class EpochProbesDaoTests {
	private final int TEST_EVOLUTION_ID = -999;
	private final int TEST_EPOCH_ID = -999;
	private final String DB_FILENAME = "../database";
	private EpochProbesDao repo;

	@Test
	public void saveSqlLiteTest() throws Exception {
		repo = new EpochProbesSqlLiteDao(DB_FILENAME);
		saveTest();
	}

	@Test
	public void saveMongodbTest() throws Exception {
		repo = new EpochProbesMongodbDao(new MongoClient());
		saveTest();
	}

	@Test
	public void retrieveLatestSqlLiteTest() throws Exception {
		repo = new EpochProbesSqlLiteDao(DB_FILENAME);
		retrieveLatestTest();
	}

	@Test
	public void retrieveLatestMongodbTest() throws Exception {
		repo = new EpochProbesMongodbDao(new MongoClient());
		retrieveLatestTest();
	}

	private void saveTest() throws Exception {
		repo.create(new Probe(ObjectId.get(), TEST_EVOLUTION_ID, TEST_EPOCH_ID, 1, 0.34f, new ArrayList<Float>() {
			{
				add(0.5f);
				add(0.15f);
				add(0.54f);
				add(0.512f);
				add(120.522f);
			}
		}));
		repo.create(new Probe(ObjectId.get(), TEST_EVOLUTION_ID, TEST_EPOCH_ID, 2, 12.34f, new ArrayList<Float>() {
			{
				add(0.23f);
				add(10.15f);
				add(0.512312f);
				add(112320.522f);
			}
		}));
		assertTrue(repo.retrieve(TEST_EVOLUTION_ID, TEST_EPOCH_ID).size() == 2);
	}

	private void retrieveLatestTest() throws Exception {
		repo.create(new Probe(ObjectId.get(), TEST_EVOLUTION_ID, TEST_EPOCH_ID, 1, 0.34f, new ArrayList<Float>() {
			{
				add(0.5f);
				add(0.15f);
				add(0.54f);
				add(0.512f);
				add(120.522f);
			}
		}));
		repo.create(new Probe(ObjectId.get(), TEST_EVOLUTION_ID, TEST_EPOCH_ID, 2, 12.34f, new ArrayList<Float>() {
			{
				add(0.23f);
				add(10.15f);
				add(0.512312f);
				add(112320.522f);
			}
		}));
		repo.create(new Probe(ObjectId.get(), TEST_EVOLUTION_ID + 1, TEST_EPOCH_ID, 1, 0.34f, new ArrayList<Float>() {
			{
				add(0.5f);
				add(0.15f);
				add(0.54f);
				add(0.512f);
				add(120.522f);
			}
		}));
		repo.create(new Probe(ObjectId.get(), TEST_EVOLUTION_ID + 1, TEST_EPOCH_ID, 2, 12.34f, new ArrayList<Float>() {
			{
				add(0.23f);
				add(10.15f);
				add(0.512312f);
				add(112320.522f);
			}
		}));
		repo.create(new Probe(ObjectId.get(), TEST_EVOLUTION_ID + 1, TEST_EPOCH_ID, 3, 20.34f, new ArrayList<Float>() {
			{
				add(55.23f);
				add(4f);
			}
		}));
		assertTrue(repo.retrieveLatest().size() == 3);
	}

	@After
	public void tearDown() throws Exception {
		repo.clear(TEST_EVOLUTION_ID);
		repo.clear(TEST_EVOLUTION_ID + 1);
	}
}
