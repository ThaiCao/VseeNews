package com.vsee.news.domain.features.news.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.vsee.news.domain.features.base.model.Dto
import com.vsee.news.domain.features.base.model.Model
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
data class SourceItem(
    @SerializedName("id")
    val id: String? ="",
    @SerializedName("name")
    val name: String? = "",
): Parcelable, Model {
    override fun toLocalDto(): Dto {
        TODO("Not yet implemented")
    }

    override fun toRemoteDto(): Dto {
        TODO("Not yet implemented")
    }
}