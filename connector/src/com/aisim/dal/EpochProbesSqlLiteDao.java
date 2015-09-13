package com.aisim.dal;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bson.types.ObjectId;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ai
 * Created by Krzysztof Slupski on 9/12/2015.
 */
public class EpochProbesSqlLiteDao implements EpochProbesDao {
	public class GenomeData {
		public GenomeData(List<Float> genomeWeights) {
			this.genomeWeights = genomeWeights;
		}

		public List<Float> getGenomeWeights() {
			return genomeWeights;
		}

		private List<Float> genomeWeights;
	}

	private SQLiteConnection db;
	private Gson gson;

	public EpochProbesSqlLiteDao(String dbFileName) throws SQLiteException {
		db = new SQLiteConnection(new File(dbFileName));
		db.open(false);
		gson = new GsonBuilder().create();
	}

	@Override
	public void create(Probe probe) throws Exception {
		SQLiteStatement st = null;
		try {
			st = db.prepare("insert into probe (id, evolutionId, epochId, genomeId, fitness, data) select ?, ?, ?, ?, ?, ?");
			st.bind(1, new ObjectId(new Date()).toString());
			st.bind(2, probe.getEvolutionId());
			st.bind(3, probe.getEpochId());
			st.bind(4, probe.getGenomeId());
			st.bind(5, probe.getFitness());
			st.bind(6, gson.toJson(new GenomeData(probe.getGenomeWeights())));
			st.step();
		} catch (Exception e) {
			throw new Exception("Saving probe failed!", e);
		} finally {
			if (st != null) {
				st.dispose();
			}
		}
	}

	@Override
	public List<Probe> retrieve(long evolutionId, long epochId) throws Exception {
		List<Probe> results = new ArrayList<>();
		SQLiteStatement st = null;
		try {
			st = db.prepare("select id, evolutionId, epochId, genomeId, fitness, data from probe where evolutionId = ? and epochId = ?");
			st.bind(1, evolutionId);
			st.bind(2, epochId);
			while (st.step())
				results.add(new Probe(new ObjectId(st.columnString(0)),
					st.columnLong(1),
					st.columnLong(2),
					st.columnLong(3),
					(float) st.columnDouble(4),
					gson.fromJson(st.columnString(5), GenomeData.class).getGenomeWeights()));
			return results;
		} catch (Exception e) {
			throw new Exception(String.format("Retrieving probes for evolutionId %d and epochId %d failed!", evolutionId, epochId), e);
		} finally {
			if (st != null) {
				st.dispose();
			}
		}

	}

	@Override
	public List<Probe> retrieveLatest() throws Exception {
		List<Probe> results = new ArrayList<>();
		SQLiteStatement st = null;
		try {
			st = db.prepare("SELECT\n" +
					"  P.id,\n" +
					"  P.evolutionId,\n" +
					"  P.epochId,\n" +
					"  P.genomeId,\n" +
					"  P.fitness,\n" +
					"  P.data\n" +
					"FROM probe P\n" +
					"  INNER JOIN (\n" +
					"               SELECT\n" +
					"                 evolutionId,\n" +
					"                 epochId\n" +
					"               FROM probe\n" +
					"               ORDER BY evolutionId\n" +
					"                 DESC, epochId\n" +
					"                 DESC\n" +
					"               LIMIT 1) T ON T.evolutionId = P.evolutionId AND T.epochId = P.epochId;"
			);
			while (st.step())
				results.add(new Probe(new ObjectId(st.columnString(0)),
					st.columnLong(1),
					st.columnLong(2),
					st.columnLong(3),
					(float) st.columnDouble(4),
					gson.fromJson(st.columnString(5), GenomeData.class).getGenomeWeights()));
			return results;
		} catch (Exception e) {
			throw new Exception("Retrieving latest epoch probes failed!", e);
		} finally {
			if (st != null) {
				st.dispose();
			}
		}
	}

	@Override
	public void clear(long evolutionId) throws Exception {
		SQLiteStatement st = null;
		try {
			st = db.prepare("delete from probe where evolutionId = ?");
			st.bind(1, evolutionId);
			st.step();
		} catch (Exception e) {
			throw new Exception(String.format("Deleting probes for evolutionId %d failed!", evolutionId), e);
		} finally {
			if (st != null) {
				st.dispose();
			}
		}
	}

	@Override
	protected void finalize() throws Throwable {
		db.dispose();
		gson = null;
		super.finalize();
	}
}
