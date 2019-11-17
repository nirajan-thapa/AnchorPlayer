package com.nirajan.anchorplayer.player

import android.os.Bundle
import android.view.View
import com.airbnb.mvrx.fragmentViewModel
import com.nirajan.anchorplayer.R
import com.nirajan.anchorplayer.base.BaseFragment
import com.nirajan.anchorplayer.base.simpleController
import com.nirajan.anchorplayer.player.views.basicRow

class PlayerFragment : BaseFragment() {

    override fun getLayoutId() = R.layout.fragment_player

    private val viewModel by fragmentViewModel(PlayerViewModel::class)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun epoxyController() = simpleController {
        arrayOf(0, 10, 50, 100, 1_000, 10_000).forEach { count ->
            basicRow {
                id(count)
                title("$count")
                clickListener { _ ->
                    // viewModel.setCount(count)
                    // findNavController().navigate(R.id.action_flowIntroFragment_to_flowCounterFragment)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recycler_view)
        toolbar = view.findViewById(R.id.toolbar)
        coordinatorLayout = view.findViewById(R.id.coordinator_layout)
        recyclerView.setController(epoxyController)
    }
}
