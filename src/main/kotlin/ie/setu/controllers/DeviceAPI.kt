package ie.setu.controllers

import ie.setu.models.Device
import ie.setu.utils.formatListString
import java.util.LinkedList

class DeviceAPI {
    private var deviceList = LinkedList<Device>()

    fun addDevice(device: Device):Boolean {
        return deviceList.add(device)
    }

    fun listOfDevices(): String = if (deviceList.isEmpty()) "No Devices Stores" else formatListString(deviceList)

    fun numOfDevices(): Int = deviceList.size

    fun removeDevice(index: Int): Device? = if (index >= 0 && index+1 >= deviceList.size) { null } else deviceList.removeAt(index)

    fun getDeviceByIndex(index: Int): Device? = if (index >= 0 && index+1 >= deviceList.size) { null } else deviceList[index]

    fun fixDevice(index: Int) {
        deviceList[index].isFixed = true
    }
}