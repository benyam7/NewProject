package com.addisfortune.printversion.base

import androidx.annotation.Keep
import com.google.firebase.firestore.IgnoreExtraProperties

@Keep
@IgnoreExtraProperties
data class Magazine(
    val magazineId: String = "",
    val publishDate: String = "",
    val magazineUrl: String = "",
    val magazineThumbnailSrc: String = "",
    // volume / week
    //
    //
    val pages: ArrayList<Page> = arrayListOf()
)


@Keep
@IgnoreExtraProperties
data class Page(
    val pageId: String = "",
    val pageShareUrl: String = "",
    val pageImageSrc: String = "",
    val videoUrl: String? = null
)