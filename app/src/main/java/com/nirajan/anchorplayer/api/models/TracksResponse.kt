package com.nirajan.anchorplayer.api.models

import com.squareup.moshi.Json

data class TracksResponse(
    @get:Json(name = "tracks") @Json(name = "tracks") val results: List<Track>
)
