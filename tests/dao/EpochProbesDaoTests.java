package dao;

import com.aisim.dal.model.EpochProbesDao;
import com.aisim.dal.model.LatestEpochInfo;
import com.aisim.dal.model.Probe;
import com.aisim.dal.mongodb.EpochProbesMongodbDao;
import com.aisim.dal.sqllite.EpochProbesSqlLiteDao;
import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
		repo.create(new Probe(ObjectId.get(), TEST_EVOLUTION_ID, DateTime.now(), TEST_EPOCH_ID, 1, 0.34f, new ArrayList<Float>() {
			{
				add(0.5f);
				add(0.15f);
				add(0.54f);
				add(0.512f);
				add(120.522f);
			}
		}));
		repo.create(new Probe(ObjectId.get(), TEST_EVOLUTION_ID, DateTime.now(), TEST_EPOCH_ID, 2, 12.34f, new ArrayList<Float>() {
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
		repo.create(new Probe(ObjectId.get(), TEST_EVOLUTION_ID, DateTime.now(), TEST_EPOCH_ID, 1, 0.34f, new ArrayList<Float>() {
			{
				add(0.5f);
				add(0.15f);
				add(0.54f);
				add(0.512f);
				add(120.522f);
			}
		}));
		repo.create(new Probe(ObjectId.get(), TEST_EVOLUTION_ID, DateTime.now(), TEST_EPOCH_ID, 2, 12.34f, new ArrayList<Float>() {
			{
				add(0.23f);
				add(10.15f);
				add(0.512312f);
				add(112320.522f);
			}
		}));
		repo.create(new Probe(ObjectId.get(), TEST_EVOLUTION_ID + 1, DateTime.now(), TEST_EPOCH_ID, 1, 0.34f, new ArrayList<Float>() {
			{
				add(0.5f);
				add(0.15f);
				add(0.54f);
				add(0.512f);
				add(120.522f);
			}
		}));
		repo.create(new Probe(ObjectId.get(), TEST_EVOLUTION_ID + 1, DateTime.now(), TEST_EPOCH_ID + 1, 1, 12.34f, new ArrayList<Float>() {
			{
				add(0.23f);
				add(10.15f);
				add(0.512312f);
				add(112320.522f);
			}
		}));
		repo.create(new Probe(ObjectId.get(), TEST_EVOLUTION_ID + 1, DateTime.now(), TEST_EPOCH_ID + 1, 2, 2f, new ArrayList<Float>() {
			{
				add(55.23f);
				add(4f);
			}
		}));
		repo.create(new Probe(ObjectId.get(), TEST_EVOLUTION_ID + 1, DateTime.now(), TEST_EPOCH_ID + 1, 3, 4.1f, new ArrayList<Float>() {
			{
				add(5.3f);
				add(65.3f);
				add(4f);
			}
		}));
		LatestEpochInfo info = repo.getLatestEpochInfo();
		assertEquals("Number of latest probes should be 3", 3, repo.retrieve(info.getEvolutionId(), info.getEpochId()).size());
	}

	@After
	public void tearDown() throws Exception {
		repo.clear(TEST_EVOLUTION_ID);
		repo.clear(TEST_EVOLUTION_ID + 1);
	}
}
