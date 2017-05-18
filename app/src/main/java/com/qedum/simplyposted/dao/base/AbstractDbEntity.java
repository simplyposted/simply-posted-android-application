package com.qedum.simplyposted.dao.base;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

public class AbstractDbEntity implements IDbEntity, Parcelable {
    private static final String ID_FIELD_NAME = "_id";

    @DatabaseField(generatedId = true, columnName = ID_FIELD_NAME)
    private Long id;

    public Long getId() {
        return id;
    }

    protected AbstractDbEntity() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
    }

    protected AbstractDbEntity(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<AbstractDbEntity> CREATOR = new Creator<AbstractDbEntity>() {
        @Override
        public AbstractDbEntity createFromParcel(Parcel source) {
            return new AbstractDbEntity(source);
        }

        @Override
        public AbstractDbEntity[] newArray(int size) {
            return new AbstractDbEntity[size];
        }
    };
}