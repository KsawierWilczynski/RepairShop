package ie.setu.controllers

import ie.setu.models.Device
import ie.setu.models.Employee
import ie.setu.types.DeviceType
import ie.setu.utils.formatListString
import java.util.LinkedList

class DeviceAPI {
    private var deviceList = LinkedList<Device>()

    fun addDevice(device: Device):Boolean {
        return deviceList.add(device)
    }

    fun listOfDevices(): String = if (deviceList.isEmpty()) "No Devices Stores" else formatListString(deviceList)

    fun numOfDevices(): Int = deviceList.size

    fun removeDevice(index: Int): Device? = if (index < 0 || index >= deviceList.size) { null } else deviceList.removeAt(index)

    fun getDeviceByIndex(index: Int): Device? = if (index < 0 || index >= deviceList.size) { null } else deviceList[index]

    fun getDeviceByType(deviceType: DeviceType): String = if (deviceList.isEmpty()) "No Devices Stores" else formatListString(deviceList.stream().filter{it.type == deviceType}.toList())

    fun getDeviceByEmployee(employee: Employee): String = if (deviceList.isEmpty()) "No Devices Stores" else formatListString(deviceList.stream().filter{it.employee == employee}.toList())

    fun fixDevice(index: Int) {
        deviceList[index].isFixed = true
    }
}