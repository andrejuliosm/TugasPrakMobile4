package com.example.fragment;

import android.os.Parcel;
import android.os.Parcelable;

public class heromodel implements Parcelable {
    private String namaHero;
    private int gambarHero;
    private String DescHero;
    public heromodel() { }

    protected heromodel(Parcel in) {
        namaHero = in.readString();
        DescHero = in.readString();
        gambarHero = in.readInt();
    }

    public static final Creator<heromodel> CREATOR = new Creator<heromodel>() {
        @Override
        public heromodel createFromParcel(Parcel in) {
            return new heromodel(in);
        }

        @Override
        public heromodel[] newArray(int size) {
            return new heromodel[size];
        }
    };

    public String getNamaHero() {
        return namaHero;
    }

    public void setNamaHero(String namaHero) {
        this.namaHero = namaHero;
    }

    public int getGambarHero() {
        return gambarHero;
    }

    public void setGambarHero(int gambarHero) {
        this.gambarHero = gambarHero;
    }

    public String getDescHero() {
        return DescHero;
    }

    public void setDescHero(String descHero) {
        DescHero = descHero;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(namaHero);
        dest.writeString(DescHero);
        dest.writeInt(gambarHero);
    }
}
