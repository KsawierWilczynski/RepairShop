package ie.setu.models

import ie.setu.types.DeviceType

data class Device(
    var serialNo: String,
    val type: DeviceType,
    var issue: String,
    var employee: Employee,
    var isFixed: Boolean = false,
)
