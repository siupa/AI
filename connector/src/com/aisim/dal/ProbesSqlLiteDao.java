package com.aisim.dal;

import com.aisim.ai.connector.ProbesService;
import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteStatement;

import java.io.File;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * ai
 * Created by Krzysztof Slupski on 9/12/2015.
 */
public class ProbesSqlLiteDao implements ProbesService {

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

	public ProbesSqlLiteDao(String dbFileName) {
		db = new SQLiteConnection(new File(dbFileName));
		gson = new GsonBuilder().create();
	}

	@Override
	public void save(int evolutionId, int epochId, int genomeId, Float fitness, List<Float> weights) throws Exception {
		SQLiteStatement st = null;
		try {
			db.open(true);
			st = db.prepare("insert into probe (id, evolutionId, epochId, genomeId, fitness, data) select ?, ?, ?, ?, ?, ?");
			st.bind(1, java.util.UUID.randomUUID().toString());
			st.bind(2, evolutionId);
			st.bind(3, epochId);
			st.bind(4, genomeId);
			st.bind(5, fitness);
			st.bind(6, gson.toJson(new GenomeData(weights)));
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
	protected void finalize() throws Throwable {
		db.dispose();
		gson = null;
		super.finalize();
	}
}
