package com.geowatershed.app.data.db;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
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
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CaptureDao_Impl implements CaptureDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CaptureEntity> __insertionAdapterOfCaptureEntity;

  public CaptureDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCaptureEntity = new EntityInsertionAdapter<CaptureEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `captures` (`id`,`siteCode`,`timestamp`,`observationType`,`description`,`latitude`,`longitude`,`accuracyMeters`,`photoPath`,`priorityScore`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CaptureEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getSiteCode() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getSiteCode());
        }
        statement.bindLong(3, entity.getTimestamp());
        if (entity.getObservationType() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getObservationType());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getDescription());
        }
        if (entity.getLatitude() == null) {
          statement.bindNull(6);
        } else {
          statement.bindDouble(6, entity.getLatitude());
        }
        if (entity.getLongitude() == null) {
          statement.bindNull(7);
        } else {
          statement.bindDouble(7, entity.getLongitude());
        }
        if (entity.getAccuracyMeters() == null) {
          statement.bindNull(8);
        } else {
          statement.bindDouble(8, entity.getAccuracyMeters());
        }
        if (entity.getPhotoPath() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getPhotoPath());
        }
        statement.bindLong(10, entity.getPriorityScore());
      }
    };
  }

  @Override
  public Object insert(final CaptureEntity capture, final Continuation<? super Long> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfCaptureEntity.insertAndReturnId(capture);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Flow<List<CaptureEntity>> observeAll() {
    final String _sql = "SELECT * FROM captures ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"captures"}, new Callable<List<CaptureEntity>>() {
      @Override
      @NonNull
      public List<CaptureEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSiteCode = CursorUtil.getColumnIndexOrThrow(_cursor, "siteCode");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfObservationType = CursorUtil.getColumnIndexOrThrow(_cursor, "observationType");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfAccuracyMeters = CursorUtil.getColumnIndexOrThrow(_cursor, "accuracyMeters");
          final int _cursorIndexOfPhotoPath = CursorUtil.getColumnIndexOrThrow(_cursor, "photoPath");
          final int _cursorIndexOfPriorityScore = CursorUtil.getColumnIndexOrThrow(_cursor, "priorityScore");
          final List<CaptureEntity> _result = new ArrayList<CaptureEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CaptureEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpSiteCode;
            if (_cursor.isNull(_cursorIndexOfSiteCode)) {
              _tmpSiteCode = null;
            } else {
              _tmpSiteCode = _cursor.getString(_cursorIndexOfSiteCode);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpObservationType;
            if (_cursor.isNull(_cursorIndexOfObservationType)) {
              _tmpObservationType = null;
            } else {
              _tmpObservationType = _cursor.getString(_cursorIndexOfObservationType);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
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
            final String _tmpPhotoPath;
            if (_cursor.isNull(_cursorIndexOfPhotoPath)) {
              _tmpPhotoPath = null;
            } else {
              _tmpPhotoPath = _cursor.getString(_cursorIndexOfPhotoPath);
            }
            final int _tmpPriorityScore;
            _tmpPriorityScore = _cursor.getInt(_cursorIndexOfPriorityScore);
            _item = new CaptureEntity(_tmpId,_tmpSiteCode,_tmpTimestamp,_tmpObservationType,_tmpDescription,_tmpLatitude,_tmpLongitude,_tmpAccuracyMeters,_tmpPhotoPath,_tmpPriorityScore);
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
  public Object getById(final long id, final Continuation<? super CaptureEntity> arg1) {
    final String _sql = "SELECT * FROM captures WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<CaptureEntity>() {
      @Override
      @Nullable
      public CaptureEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSiteCode = CursorUtil.getColumnIndexOrThrow(_cursor, "siteCode");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfObservationType = CursorUtil.getColumnIndexOrThrow(_cursor, "observationType");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfAccuracyMeters = CursorUtil.getColumnIndexOrThrow(_cursor, "accuracyMeters");
          final int _cursorIndexOfPhotoPath = CursorUtil.getColumnIndexOrThrow(_cursor, "photoPath");
          final int _cursorIndexOfPriorityScore = CursorUtil.getColumnIndexOrThrow(_cursor, "priorityScore");
          final CaptureEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpSiteCode;
            if (_cursor.isNull(_cursorIndexOfSiteCode)) {
              _tmpSiteCode = null;
            } else {
              _tmpSiteCode = _cursor.getString(_cursorIndexOfSiteCode);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpObservationType;
            if (_cursor.isNull(_cursorIndexOfObservationType)) {
              _tmpObservationType = null;
            } else {
              _tmpObservationType = _cursor.getString(_cursorIndexOfObservationType);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
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
            final String _tmpPhotoPath;
            if (_cursor.isNull(_cursorIndexOfPhotoPath)) {
              _tmpPhotoPath = null;
            } else {
              _tmpPhotoPath = _cursor.getString(_cursorIndexOfPhotoPath);
            }
            final int _tmpPriorityScore;
            _tmpPriorityScore = _cursor.getInt(_cursorIndexOfPriorityScore);
            _result = new CaptureEntity(_tmpId,_tmpSiteCode,_tmpTimestamp,_tmpObservationType,_tmpDescription,_tmpLatitude,_tmpLongitude,_tmpAccuracyMeters,_tmpPhotoPath,_tmpPriorityScore);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg1);
  }

  @Override
  public Object count(final Continuation<? super Integer> arg0) {
    final String _sql = "SELECT COUNT(*) FROM captures";
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
