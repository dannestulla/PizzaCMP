package presentation.model

import data.model.Driver
import data.model.MapDirections

data class MapState(
    val driver: DriverState? = null,
    val mapDirections: MapDirectionsState? = null
)
