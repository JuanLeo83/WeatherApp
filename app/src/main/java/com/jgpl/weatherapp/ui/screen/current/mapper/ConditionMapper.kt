package com.jgpl.weatherapp.ui.screen.current.mapper

import com.jgpl.weatherapp.R

class ConditionMapper {

    fun getIcon(conditionCode: Int, isDay: Boolean): Int {
        return when (conditionCode) {
            1000 -> if (isDay) R.drawable.ic_sun else R.drawable.ic_moon
            1003 -> if (isDay) R.drawable.ic_partly_cloudy_day else R.drawable.ic_partly_cloudy_night
            1006, 1009 -> R.drawable.ic_mostly_cloudy
            1030 -> R.drawable.ic_mist
            1063, 1180, 1186, 1192, 1240, 1243, 1249, 1252 -> if (isDay) R.drawable.ic_rain_day else R.drawable.ic_rain_night
            1072, 1150, 1153, 1168, 1171, 1183, 1189, 1195, 1198, 1201, 1204, 1207, 1246 -> R.drawable.ic_rain
            1087, 1273, 1276, 1279, 1282 -> R.drawable.ic_thunderbolt
            1066, 1069, 1210, 1216, 1222, 1255, 1258, 1261, 1264 -> if (isDay) R.drawable.ic_snow_day else R.drawable.ic_snow_night
            1114, 1117, 1213, 1219, 1225, 1237 -> R.drawable.ic_snow
            else -> if (isDay) R.drawable.ic_sun else R.drawable.ic_moon
        }
    }

}