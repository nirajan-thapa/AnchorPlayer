package com.nirajan.anchorplayer.api

import com.nirajan.anchorplayer.api.models.TracksResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface AnchorPlayerAPIService {

    @Headers("Accept: application/json")
    @GET("challenges/{name}")
    fun getChallenge(@Path("name") challengeName: String): Observable<TracksResponse>
}
