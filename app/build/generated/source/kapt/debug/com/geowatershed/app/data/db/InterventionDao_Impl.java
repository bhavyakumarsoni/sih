package com.geowatershed.app.data.db;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class InterventionDao_Impl implements InterventionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<InterventionEntity> __insertionAdapterOfInterventionEntity;

  private final EntityDeletionOrUpdateAdapter<InterventionEntity> __updateAdapterOfInterventionEntity;

  public InterventionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfInterventionEntity = new EntityInsertionAdapter<InterventionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `interventions` (`id`,`type`,`siteCode`,`siteDetail`,`latitude`,`longitude`,`accuracyMeters`,`stage`,`createdAt`,`sourceCaptureId`,`verificationSubmitted`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final InterventionEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getType() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getType());
        }
        if (entity.getSiteCode() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getSiteCode());
        }
        if (entity.getSiteDetail() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getSiteDetail());
        }
        if (entity.getLatitude() == null) {
          statement.bindNull(5);
        } else {
          statement.bindDouble(5, entity.getLatitude());
        }
        if (entity.getLongitude() == null) {
          statement.bindNull(6);
        } else {
          statement.bindDouble(6, entity.getLongitude());
        }
        if (entity.getAccuracyMeters() == null) {
          statement.bindNull(7);
        } else {
          statement.bindDouble(7, entity.getAccuracyMeters());
        }
        if (entity.getStage() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getStage());
        }
        statement.bindLong(9, entity.getCreatedAt());
        if (entity.getSourceCaptureId() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getSourceCaptureId());
        }
        final int _tmp = entity.getVerificationSubmitted() ? 1 : 0;
        statement.bindLong(11, _tmp);
      }
    };
    this.__updateAdapterOfInterventionEntity = new EntityDeletionOrUpdateAdapter<InterventionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `interventions` SET `id` = ?,`type` = ?,`siteCode` = ?,`siteDetail` = ?,`latitude` = ?,`longitude` = ?,`accuracyMeters` = ?,`stage` = ?,`createdAt` = ?,`sourceCaptureId` = ?,`verificationSubmitted` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final InterventionEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getType() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getType());
        }
        if (entity.getSiteCode() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getSiteCode());
        }
        if (entity.getSiteDetail() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getSiteDetail());
        }
        if (entity.getLatitude() == null) {
          statement.bindNull(5);
        } else {
          statement.bindDouble(5, entity.getLatitude());
        }
        if (entity.getLongitude() == null) {
          statement.bindNull(6);
        } else {
          statement.bindDouble(6, entity.getLongitude());
        }
        if (entity.getAccuracyMeters() == null) {
          statement.bindNull(7);
        } else {
          statement.bindDouble(7, entity.getAccuracyMeters());
        }
        if (entity.getStage() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getStage());
        }
        statement.bindLong(9, entity.getCreatedAt());
        if (entity.getSourceCaptureId() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getSourceCaptureId());
        }
        final int _tmp = entity.getVerificationSubmitted() ? 1 : 0;
        statement.bindLong(11, _tmp);
        statement.bindLong(12, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final InterventionEntity intervention,
      final Continuation<? super Long> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfInterventionEntity.insertAndReturnId(intervention);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object update(final InterventionEntity intervention,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfInterventionEntity.handle(intervention);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Flow<List<InterventionEntity>> observeAll() {
    final String _sql = "SELECT * FROM interventions ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"interventions"}, new Callable<List<InterventionEntity>>() {
      @Override
      @NonNull
      public List<InterventionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfSiteCode = CursorUtil.getColumnIndexOrThrow(_cursor, "siteCode");
          final int _cursorIndexOfSiteDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "siteDetail");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfAccuracyMeters = CursorUtil.getColumnIndexOrThrow(_cursor, "accuracyMeters");
          final int _cursorIndexOfStage = CursorUtil.getColumnIndexOrThrow(_cursor, "stage");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfSourceCaptureId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceCaptureId");
          final int _cursorIndexOfVerificationSubmitted = CursorUtil.getColumnIndexOrThrow(_cursor, "verificationSubmitted");
          final List<InterventionEntity> _result = new ArrayList<InterventionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final InterventionEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final String _tmpSiteCode;
            if (_cursor.isNull(_cursorIndexOfSiteCode)) {
              _tmpSiteCode = null;
            } else {
              _tmpSiteCode = _cursor.getString(_cursorIndexOfSiteCode);
            }
            final String _tmpSiteDetail;
            if (_cursor.isNull(_cursorIndexOfSiteDetail)) {
              _tmpSiteDetail = null;
            } else {
              _tmpSiteDetail = _cursor.getString(_cursorIndexOfSiteDetail);
            }
            final Double _tmpLatitude;
            if (_cursor.isNull(_cursorIndexOfLatitude)) {
              _tmpLatitude = null;
            } else {
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            }
            final Double _tmpLongitude;
            if (_cursor.isNull(_cursorIndexOfLongitude)) {
              _tmpLongitude = null;
            } else {
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            }
            final Float _tmpAccuracyMeters;
            if (_cursor.isNull(_cursorIndexOfAccuracyMeters)) {
              _tmpAccuracyMeters = null;
            } else {
              _tmpAccuracyMeters = _cursor.getFloat(_cursorIndexOfAccuracyMeters);
            }
            final String _tmpStage;
            if (_cursor.isNull(_cursorIndexOfStage)) {
              _tmpStage = null;
            } else {
              _tmpStage = _cursor.getString(_cursorIndexOfStage);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpSourceCaptureId;
            if (_cursor.isNull(_cursorIndexOfSourceCaptureId)) {
              _tmpSourceCaptureId = null;
            } else {
              _tmpSourceCaptureId = _cursor.getLong(_cursorIndexOfSourceCaptureId);
            }
            final boolean _tmpVerificationSubmitted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfVerificationSubmitted);
            _tmpVerificationSubmitted = _tmp != 0;
            _item = new InterventionEntity(_tmpId,_tmpType,_tmpSiteCode,_tmpSiteDetail,_tmpLatitude,_tmpLongitude,_tmpAccuracyMeters,_tmpStage,_tmpCreatedAt,_tmpSourceCaptureId,_tmpVerificationSubmitted);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<InterventionEntity> observeById(final long id) {
    final String _sql = "SELECT * FROM interventions WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"interventions"}, new Callable<InterventionEntity>() {
      @Override
      @Nullable
      public InterventionEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfSiteCode = CursorUtil.getColumnIndexOrThrow(_cursor, "siteCode");
          final int _cursorIndexOfSiteDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "siteDetail");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfAccuracyMeters = CursorUtil.getColumnIndexOrThrow(_cursor, "accuracyMeters");
          final int _cursorIndexOfStage = CursorUtil.getColumnIndexOrThrow(_cursor, "stage");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfSourceCaptureId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceCaptureId");
          final int _cursorIndexOfVerificationSubmitted = CursorUtil.getColumnIndexOrThrow(_cursor, "verificationSubmitted");
          final InterventionEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final String _tmpSiteCode;
            if (_cursor.isNull(_cursorIndexOfSiteCode)) {
              _tmpSiteCode = null;
            } else {
              _tmpSiteCode = _cursor.getString(_cursorIndexOfSiteCode);
            }
            final String _tmpSiteDetail;
            if (_cursor.isNull(_cursorIndexOfSiteDetail)) {
              _tmpSiteDetail = null;
            } else {
              _tmpSiteDetail = _cursor.getString(_cursorIndexOfSiteDetail);
            }
            final Double _tmpLatitude;
            if (_cursor.isNull(_cursorIndexOfLatitude)) {
              _tmpLatitude = null;
            } else {
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            }
            final Double _tmpLongitude;
            if (_cursor.isNull(_cursorIndexOfLongitude)) {
              _tmpLongitude = null;
            } else {
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            }
            final Float _tmpAccuracyMeters;
            if (_cursor.isNull(_cursorIndexOfAccuracyMeters)) {
              _tmpAccuracyMeters = null;
            } else {
              _tmpAccuracyMeters = _cursor.getFloat(_cursorIndexOfAccuracyMeters);
            }
            final String _tmpStage;
            if (_cursor.isNull(_cursorIndexOfStage)) {
              _tmpStage = null;
            } else {
              _tmpStage = _cursor.getString(_cursorIndexOfStage);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpSourceCaptureId;
            if (_cursor.isNull(_cursorIndexOfSourceCaptureId)) {
              _tmpSourceCaptureId = null;
            } else {
              _tmpSourceCaptureId = _cursor.getLong(_cursorIndexOfSourceCaptureId);
            }
            final boolean _tmpVerificationSubmitted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfVerificationSubmitted);
            _tmpVerificationSubmitted = _tmp != 0;
            _result = new InterventionEntity(_tmpId,_tmpType,_tmpSiteCode,_tmpSiteDetail,_tmpLatitude,_tmpLongitude,_tmpAccuracyMeters,_tmpStage,_tmpCreatedAt,_tmpSourceCaptureId,_tmpVerificationSubmitted);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object count(final Continuation<? super Integer> arg0) {
    final String _sql = "SELECT COUNT(*) FROM interventions";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg0);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
