package com.nirajan.anchorplayer.api.models

import com.squareup.moshi.Json

data class Track (
    @get:Json(name = "title") @Json(name = "title") val title : String,
    @get:Json(name = "mediaUrl") @Json(name = "mediaUrl") val mediaUrl : String,
    @get:Json(name = "imageUrl") @Json(name = "imageUrl") val imageUrl : String,
    @get:Json(name = "duration") @Json(name = "duration") val duration : Int
)
