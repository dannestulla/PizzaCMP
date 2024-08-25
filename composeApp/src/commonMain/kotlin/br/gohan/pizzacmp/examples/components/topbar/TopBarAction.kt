package br.gohan.pizzacmp.examples.components.topbar


sealed class TopBarAction {
    data class Search(val search: String?) : TopBarAction()
    data object Back : TopBarAction()
    data object CancelSearch : TopBarAction()
}

