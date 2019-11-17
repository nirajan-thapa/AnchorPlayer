package com.nirajan.anchorplayer.player

import android.os.Bundle
import android.util.Log
import android.view.View
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.fragmentViewModel
import com.google.android.material.snackbar.Snackbar
import com.nirajan.anchorplayer.R
import com.nirajan.anchorplayer.base.BaseFragment
import com.nirajan.anchorplayer.base.simpleController
import com.nirajan.anchorplayer.player.views.basicRow
import com.nirajan.anchorplayer.player.views.loadingView

class PlayerFragment : BaseFragment() {

    override fun getLayoutId() = R.layout.fragment_player

    private val viewModel by fragmentViewModel(PlayerViewModel::class)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun epoxyController() = simpleController(viewModel) { state ->
        if (state.request is Loading) {
            loadingView {
                id("loading-view")
            }
            return@simpleController
        }
        state.tracks.forEachIndexed { index, track ->
            basicRow {
                id("track-$index")
                title(track.title)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recycler_view)
        toolbar = view.findViewById(R.id.toolbar)
        coordinatorLayout = view.findViewById(R.id.coordinator_layout)
        recyclerView.setController(epoxyController)

        viewModel.asyncSubscribe(PlayerState::request, onFail = { error ->
            Snackbar.make(coordinatorLayout, "Tracks request failed.", Snackbar.LENGTH_LONG)
                .show()
            Log.w(TAG, "Tracks request failed", error)
        })
    }

    companion object {
        private const val TAG = "PlayerFragment"
    }
}
