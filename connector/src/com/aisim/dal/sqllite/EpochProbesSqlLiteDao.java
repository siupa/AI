package com.aisim.dal.sqllite;

import com.aisim.dal.contracts.EpochProbesDao;
import com.aisim.dal.model.LatestEpochInfo;
import com.aisim.dal.model.Probe;
import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteStatement;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * ai
 * Created by Krzysztof Slupski on 9/12/2015.
 */
public class EpochProbesSqlLiteDao implements EpochProbesDao {

    private SQLiteConnection db;
    private SQLiteDaoMapper<Probe> mapper;

    public EpochProbesSqlLiteDao(String dbFileName) {
        mapper = new ProbeSQLiteDaoMapper();
        try {
            db = new SQLiteConnection(new File(dbFileName));
            db.open(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Probe probe) throws Exception {
        SQLiteStatement st = null;
        try {
            st = mapper.toDb(db, probe);
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
    public LatestEpochInfo getLatestEpochInfo() throws Exception {
        SQLiteStatement st = null;
        try {
            st = db.prepare("select evolutionId, createdOn, epochId from probe order by evolutionId DESC, epochId DESC LIMIT 1");
            if (st.step())
                return new LatestEpochInfo(st.columnInt(0), DateTime.parse(st.columnString(1), DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")), st.columnLong(2));
            else
                return null;
        } catch (Exception e) {
            throw new Exception("Getting latest epoch info failed!", e);
        } finally {
            if (st != null) {
                st.dispose();
            }
        }
    }

    @Override
    public List<Probe> retrieve(int evolutionId, long epochId) throws Exception {
        List<Probe> results = new ArrayList<>();
        SQLiteStatement st = null;
        try {
            st = db.prepare("select id, evolutionId, createdOn, epochId, genomeId, fitness, data from probe where evolutionId = ? and epochId = ?");
            st.bind(1, evolutionId);
            st.bind(2, epochId);
            while (st.step())
                results.add(mapper.fromDb(st));
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
    public void clear(int evolutionId) throws Exception {
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
        if (db != null)
            db.dispose();
        super.finalize();
    }
}
