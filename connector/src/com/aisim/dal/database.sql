CREATE TABLE IF NOT EXISTS probe (
  id          ObjectId PRIMARY KEY,
  evolutionId INT,
  epochId     INT,
  genomeId    INT,
  fitness     FLOAT,
  data        VARCHAR
);
CREATE INDEX IF NOT EXISTS idx_probe_evolutionId_epochId ON probe (evolutionId, epochId)



