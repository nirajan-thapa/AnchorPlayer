package com.nirajan.anchorplayer.player

import com.airbnb.mvrx.MvRxState
import com.nirajan.anchorplayer.base.BaseViewModel

data class PlayerState(val count: Int = 0) : MvRxState


class PlayerViewModel(initialState: PlayerState)
    : BaseViewModel<PlayerState>(initialState) {


}
