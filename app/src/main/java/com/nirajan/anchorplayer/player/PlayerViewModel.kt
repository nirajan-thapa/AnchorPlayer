package com.nirajan.anchorplayer.player

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.ViewModelContext
import com.nirajan.anchorplayer.api.AnchorPlayerAPIService
import com.nirajan.anchorplayer.api.models.Track
import com.nirajan.anchorplayer.api.models.TracksResponse
import com.nirajan.anchorplayer.base.BaseViewModel
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject

data class PlayerState(
    val tracks: List<Track> = emptyList(),
    val request: Async<TracksResponse> = Uninitialized
) : MvRxState

class PlayerViewModel(
    initialState: PlayerState,
    private val anchorPlayerAPIService: AnchorPlayerAPIService
) : BaseViewModel<PlayerState>(initialState) {

    init {
        fetchTracks()
    }

    private fun fetchTracks() = withState { state ->
        if (state.request is Loading) return@withState

        anchorPlayerAPIService
            .getChallenge("bsb.json")
            .subscribeOn(Schedulers.io())
            .execute {
                copy(
                    request = it,
                    tracks = it()?.results ?: emptyList()
                )
            }
    }

    /**
     * If you implement MvRxViewModelFactory in your companion object, MvRx will use that to create
     * your ViewModel. You can use this to achieve constructor dependency injection with MvRx.
     *
     * @see MvRxViewModelFactory
     */
    companion object : MvRxViewModelFactory<PlayerViewModel, PlayerState> {

        override fun create(viewModelContext: ViewModelContext, state: PlayerState): PlayerViewModel {
            val APIService: AnchorPlayerAPIService by viewModelContext.activity.inject()
            return PlayerViewModel(state, APIService)
        }
    }
}
