package com.nirajan.anchorplayer.player

import android.os.Bundle
import android.view.View
import com.nirajan.anchorplayer.R
import com.nirajan.anchorplayer.base.BaseFragment
import com.nirajan.anchorplayer.base.simpleController
import dagger.android.support.AndroidSupportInjection

class PlayerFragment : BaseFragment() {

    override fun getLayoutId() = R.layout.fragment_player

    override fun injectDependencies() = AndroidSupportInjection.inject(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun epoxyController() = simpleController {  }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recycler_view)
        toolbar = view.findViewById(R.id.toolbar)
        coordinatorLayout = view.findViewById(R.id.coordinator_layout)
        recyclerView.setController(epoxyController)
    }
}
