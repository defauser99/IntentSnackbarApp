package binar.ch4.myintentapp

import android.os.Parcel
import android.os.Parcelable

data class Person2(val username:String?, val comment:String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(comment)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Person2> {
        override fun createFromParcel(parcel: Parcel): Person2 {
            return Person2(parcel)
        }

        override fun newArray(size: Int): Array<Person2?> {
            return arrayOfNulls(size)
        }
    }
}


