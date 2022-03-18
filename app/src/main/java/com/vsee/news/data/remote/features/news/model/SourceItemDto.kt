package com.vsee.news.data.remote.features.news.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.vsee.news.domain.features.base.model.Dto
import com.vsee.news.domain.features.base.model.Model
import com.vsee.news.domain.features.news.model.SourceItem
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
data class SourceItemDto(
    @SerializedName("id")
    val id: String? ="",
    @SerializedName("name")
    val name: String? = "",
): Parcelable, Dto {

    override fun mapToDomainModel(): Model {
        return SourceItem(
            id = id,
            name = name,
        )
    }

}