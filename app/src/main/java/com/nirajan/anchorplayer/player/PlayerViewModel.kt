package com.nirajan.anchorplayer.player

import android.media.MediaPlayer
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
    val currentTrackIndex: Int = -1,
    val isPlaying: Boolean = false,
    val request: Async<TracksResponse> = Uninitialized
) : MvRxState

class PlayerViewModel(
    initialState: PlayerState,
    private val anchorPlayerAPIService: AnchorPlayerAPIService
) : BaseViewModel<PlayerState>(initialState) {

    private val mediaPlayer: MediaPlayer = MediaPlayer()

    init {
        mediaPlayer.setOnCompletionListener { playNextTrack() }
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
                    tracks = it()?.results?.filter {
                        it.mediaUrl.contains("audio-ssl")
                    } ?: emptyList()
                )
            }
    }

    /**
     * Intentional naive implementation of media player.
     * A robust implementation would include following:
     * - Media Player Service that extends Android Service
     * - Displays notification
     * - Service binds to the Activity/Fragment
     *
     */
    fun playOrPauseTrack(index: Int) = withState {
        if (index == it.currentTrackIndex) {
            if (it.isPlaying)
                mediaPlayer.pause()
            else
                mediaPlayer.start()
            setState {
                copy(
                    isPlaying = !it.isPlaying,
                    currentTrackIndex = index
                )
            }
        } else {
            stopPlayer()
            mediaPlayer.setDataSource((it.tracks[index]).mediaUrl)
            mediaPlayer.prepare()
            mediaPlayer.start()
            setState {
                copy(
                    isPlaying = true,
                    currentTrackIndex = index
                )
            }
        }
    }

    private fun playNextTrack() = withState {
        val nextIndex =
            if ((it.currentTrackIndex + 1) >= 0 && (it.currentTrackIndex + 1) < it.tracks.size)
                it.currentTrackIndex + 1
            else
                0
        playOrPauseTrack(nextIndex)
    }

    private fun stopPlayer() {
        mediaPlayer.stop()
        mediaPlayer.reset()
    }

    fun stopTrack() = withState {
        stopPlayer()
        setState {
            copy(
                currentTrackIndex = -1,
                isPlaying = false
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
