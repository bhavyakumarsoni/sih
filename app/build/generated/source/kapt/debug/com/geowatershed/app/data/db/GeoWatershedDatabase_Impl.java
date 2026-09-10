package com.geowatershed.app.data.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class GeoWatershedDatabase_Impl extends GeoWatershedDatabase {
  private volatile CaptureDao _captureDao;

  private volatile InterventionDao _interventionDao;

  private volatile PhotoEvidenceDao _photoEvidenceDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `captures` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `siteCode` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `observationType` TEXT NOT NULL, `description` TEXT NOT NULL, `latitude` REAL, `longitude` REAL, `accuracyMeters` REAL, `photoPath` TEXT, `priorityScore` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `interventions` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `type` TEXT NOT NULL, `siteCode` TEXT NOT NULL, `siteDetail` TEXT NOT NULL, `latitude` REAL, `longitude` REAL, `accuracyMeters` REAL, `stage` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, `sourceCaptureId` INTEGER, `verificationSubmitted` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `photo_evidence` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `interventionId` INTEGER NOT NULL, `photoSet` TEXT NOT NULL, `photoPath` TEXT, `date` TEXT NOT NULL, `meta` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, FOREIGN KEY(`interventionId`) REFERENCES `interventions`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_photo_evidence_interventionId` ON `photo_evidence` (`interventionId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '16ef40d9de03c46a7015a8d85929cfcb')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `captures`");
        db.execSQL("DROP TABLE IF EXISTS `interventions`");
        db.execSQL("DROP TABLE IF EXISTS `photo_evidence`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsCaptures = new HashMap<String, TableInfo.Column>(10);
        _columnsCaptures.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCaptures.put("siteCode", new TableInfo.Column("siteCode", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCaptures.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCaptures.put("observationType", new TableInfo.Column("observationType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCaptures.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCaptures.put("latitude", new TableInfo.Column("latitude", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCaptures.put("longitude", new TableInfo.Column("longitude", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCaptures.put("accuracyMeters", new TableInfo.Column("accuracyMeters", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCaptures.put("photoPath", new TableInfo.Column("photoPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCaptures.put("priorityScore", new TableInfo.Column("priorityScore", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCaptures = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCaptures = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCaptures = new TableInfo("captures", _columnsCaptures, _foreignKeysCaptures, _indicesCaptures);
        final TableInfo _existingCaptures = TableInfo.read(db, "captures");
        if (!_infoCaptures.equals(_existingCaptures)) {
          return new RoomOpenHelper.ValidationResult(false, "captures(com.geowatershed.app.data.db.CaptureEntity).\n"
                  + " Expected:\n" + _infoCaptures + "\n"
                  + " Found:\n" + _existingCaptures);
        }
        final HashMap<String, TableInfo.Column> _columnsInterventions = new HashMap<String, TableInfo.Column>(11);
        _columnsInterventions.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInterventions.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInterventions.put("siteCode", new TableInfo.Column("siteCode", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInterventions.put("siteDetail", new TableInfo.Column("siteDetail", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInterventions.put("latitude", new TableInfo.Column("latitude", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInterventions.put("longitude", new TableInfo.Column("longitude", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInterventions.put("accuracyMeters", new TableInfo.Column("accuracyMeters", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInterventions.put("stage", new TableInfo.Column("stage", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInterventions.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInterventions.put("sourceCaptureId", new TableInfo.Column("sourceCaptureId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInterventions.put("verificationSubmitted", new TableInfo.Column("verificationSubmitted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysInterventions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesInterventions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoInterventions = new TableInfo("interventions", _columnsInterventions, _foreignKeysInterventions, _indicesInterventions);
        final TableInfo _existingInterventions = TableInfo.read(db, "interventions");
        if (!_infoInterventions.equals(_existingInterventions)) {
          return new RoomOpenHelper.ValidationResult(false, "interventions(com.geowatershed.app.data.db.InterventionEntity).\n"
                  + " Expected:\n" + _infoInterventions + "\n"
                  + " Found:\n" + _existingInterventions);
        }
        final HashMap<String, TableInfo.Column> _columnsPhotoEvidence = new HashMap<String, TableInfo.Column>(7);
        _columnsPhotoEvidence.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPhotoEvidence.put("interventionId", new TableInfo.Column("interventionId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPhotoEvidence.put("photoSet", new TableInfo.Column("photoSet", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPhotoEvidence.put("photoPath", new TableInfo.Column("photoPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPhotoEvidence.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPhotoEvidence.put("meta", new TableInfo.Column("meta", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPhotoEvidence.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPhotoEvidence = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysPhotoEvidence.add(new TableInfo.ForeignKey("interventions", "CASCADE", "NO ACTION", Arrays.asList("interventionId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesPhotoEvidence = new HashSet<TableInfo.Index>(1);
        _indicesPhotoEvidence.add(new TableInfo.Index("index_photo_evidence_interventionId", false, Arrays.asList("interventionId"), Arrays.asList("ASC")));
        final TableInfo _infoPhotoEvidence = new TableInfo("photo_evidence", _columnsPhotoEvidence, _foreignKeysPhotoEvidence, _indicesPhotoEvidence);
        final TableInfo _existingPhotoEvidence = TableInfo.read(db, "photo_evidence");
        if (!_infoPhotoEvidence.equals(_existingPhotoEvidence)) {
          return new RoomOpenHelper.ValidationResult(false, "photo_evidence(com.geowatershed.app.data.db.PhotoEvidenceEntity).\n"
                  + " Expected:\n" + _infoPhotoEvidence + "\n"
                  + " Found:\n" + _existingPhotoEvidence);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "16ef40d9de03c46a7015a8d85929cfcb", "a8ab375f78ff263df0710572ead29668");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "captures","interventions","photo_evidence");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `captures`");
      _db.execSQL("DELETE FROM `interventions`");
      _db.execSQL("DELETE FROM `photo_evidence`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(CaptureDao.class, CaptureDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(InterventionDao.class, InterventionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PhotoEvidenceDao.class, PhotoEvidenceDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public CaptureDao captureDao() {
    if (_captureDao != null) {
      return _captureDao;
    } else {
      synchronized(this) {
        if(_captureDao == null) {
          _captureDao = new CaptureDao_Impl(this);
        }
        return _captureDao;
      }
    }
  }

  @Override
  public InterventionDao interventionDao() {
    if (_interventionDao != null) {
      return _interventionDao;
    } else {
      synchronized(this) {
        if(_interventionDao == null) {
          _interventionDao = new InterventionDao_Impl(this);
        }
        return _interventionDao;
      }
    }
  }

  @Override
  public PhotoEvidenceDao photoEvidenceDao() {
    if (_photoEvidenceDao != null) {
      return _photoEvidenceDao;
    } else {
      synchronized(this) {
        if(_photoEvidenceDao == null) {
          _photoEvidenceDao = new PhotoEvidenceDao_Impl(this);
        }
        return _photoEvidenceDao;
      }
    }
  }
}
