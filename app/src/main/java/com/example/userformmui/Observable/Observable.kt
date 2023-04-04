package com.example.userformmui.Observable

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR


class Observables : BaseObservable() {
    @get:Bindable

    var height: String=  ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.height)
        }
}