CREATE TABLE IF NOT EXISTS probe (
  id          ObjectId PRIMARY KEY,
  evolutionId INT,
  createdOn DATETIME,
  epochId     BIGINT,
  genomeId    BIGINT,
  fitness     FLOAT,
  data        VARCHAR
);
CREATE INDEX IF NOT EXISTS idx_probe_evolutionId_epochId ON probe (evolutionId, epochId)



