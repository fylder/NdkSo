package fylder.ndk.demo.bean

import android.os.Parcel
import android.os.Parcelable

class AnimeBean(var name: String) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.readString().toString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AnimeBean> {
        override fun createFromParcel(parcel: Parcel): AnimeBean {
            return AnimeBean(parcel)
        }

        override fun newArray(size: Int): Array<AnimeBean?> {
            return arrayOfNulls(size)
        }
    }

}