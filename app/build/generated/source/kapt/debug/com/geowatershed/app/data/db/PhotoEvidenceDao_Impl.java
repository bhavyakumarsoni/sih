package com.geowatershed.app.data.db;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
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
public final class PhotoEvidenceDao_Impl implements PhotoEvidenceDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PhotoEvidenceEntity> __insertionAdapterOfPhotoEvidenceEntity;

  public PhotoEvidenceDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPhotoEvidenceEntity = new EntityInsertionAdapter<PhotoEvidenceEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `photo_evidence` (`id`,`interventionId`,`photoSet`,`photoPath`,`date`,`meta`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PhotoEvidenceEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getInterventionId());
        if (entity.getPhotoSet() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getPhotoSet());
        }
        if (entity.getPhotoPath() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getPhotoPath());
        }
        if (entity.getDate() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getDate());
        }
        if (entity.getMeta() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getMeta());
        }
        statement.bindLong(7, entity.getCreatedAt());
      }
    };
  }

  @Override
  public Object insert(final PhotoEvidenceEntity photo, final Continuation<? super Long> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfPhotoEvidenceEntity.insertAndReturnId(photo);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Flow<List<PhotoEvidenceEntity>> observeForIntervention(final long interventionId) {
    final String _sql = "SELECT * FROM photo_evidence WHERE interventionId = ? ORDER BY id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, interventionId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"photo_evidence"}, new Callable<List<PhotoEvidenceEntity>>() {
      @Override
      @NonNull
      public List<PhotoEvidenceEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfInterventionId = CursorUtil.getColumnIndexOrThrow(_cursor, "interventionId");
          final int _cursorIndexOfPhotoSet = CursorUtil.getColumnIndexOrThrow(_cursor, "photoSet");
          final int _cursorIndexOfPhotoPath = CursorUtil.getColumnIndexOrThrow(_cursor, "photoPath");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfMeta = CursorUtil.getColumnIndexOrThrow(_cursor, "meta");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<PhotoEvidenceEntity> _result = new ArrayList<PhotoEvidenceEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PhotoEvidenceEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpInterventionId;
            _tmpInterventionId = _cursor.getLong(_cursorIndexOfInterventionId);
            final String _tmpPhotoSet;
            if (_cursor.isNull(_cursorIndexOfPhotoSet)) {
              _tmpPhotoSet = null;
            } else {
              _tmpPhotoSet = _cursor.getString(_cursorIndexOfPhotoSet);
            }
            final String _tmpPhotoPath;
            if (_cursor.isNull(_cursorIndexOfPhotoPath)) {
              _tmpPhotoPath = null;
            } else {
              _tmpPhotoPath = _cursor.getString(_cursorIndexOfPhotoPath);
            }
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final String _tmpMeta;
            if (_cursor.isNull(_cursorIndexOfMeta)) {
              _tmpMeta = null;
            } else {
              _tmpMeta = _cursor.getString(_cursorIndexOfMeta);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new PhotoEvidenceEntity(_tmpId,_tmpInterventionId,_tmpPhotoSet,_tmpPhotoPath,_tmpDate,_tmpMeta,_tmpCreatedAt);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
