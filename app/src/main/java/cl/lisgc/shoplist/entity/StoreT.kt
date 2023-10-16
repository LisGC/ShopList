package cl.lisgc.shoplist.entity

import android.os.Parcel
import android.os.Parcelable

data class StoreT(

    val name: String?,
    val location: String?,
    val schedule: String?
): Parcelable {
    constructor(parcel: Parcel): this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(location)
        parcel.writeString(schedule)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StoreT> {
        override fun createFromParcel(parcel: Parcel): StoreT {
            return StoreT(parcel)
        }

        override fun newArray(size: Int): Array<StoreT?> {
            return arrayOfNulls(size)
        }
    }
}


