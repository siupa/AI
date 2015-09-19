package com.aisim.dal.sqllite;

import com.aisim.dal.model.Probe;
import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * ai
 * Created by Krzysztof on 9/19/2015.
 */
public class ProbeSQLiteDaoMapper implements SQLiteDaoMapper<Probe> {

    private Gson gson;
    class GenomeData {
        public GenomeData(List<Float> genomeWeights) {
            this.genomeWeights = genomeWeights;
        }
        public List<Float> getGenomeWeights() {
            return genomeWeights;
        }
        private List<Float> genomeWeights;
    }

    public ProbeSQLiteDaoMapper() {
        gson = new GsonBuilder().create();
    }

    @Override
    public Probe fromDb(SQLiteStatement st) throws SQLiteException {
        return new Probe(new ObjectId(st.columnString(0)),
                st.columnInt(1),
                DateTime.parse(st.columnString(2), DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")),
                st.columnLong(3),
                st.columnLong(4),
                (float) st.columnDouble(5),
                gson.fromJson(st.columnString(6), GenomeData.class).getGenomeWeights());
    }

    @Override
    public SQLiteStatement toDb(SQLiteConnection db, Probe object) throws SQLiteException {
        SQLiteStatement st = db.prepare("insert into probe (id, evolutionId, createdOn, epochId, genomeId, fitness, data) select ?, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?");
        st.bind(1, new ObjectId(new Date()).toString());
        st.bind(2, object.getEvolutionId());
        st.bind(3, object.getEpochId());
        st.bind(4, object.getGenomeId());
        st.bind(5, object.getFitness());
        st.bind(6, gson.toJson(new GenomeData(object.getGenomeWeights())));
        return st;
    }
}
