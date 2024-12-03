package ie.setu.controllers

import ie.setu.models.Device
import ie.setu.models.Employee
import ie.setu.persistance.JSONSerializer
import ie.setu.types.DeviceType
import ie.setu.utils.formatListString
import java.io.File
import java.util.LinkedList

class DeviceAPI {

    private var serializer: JSONSerializer = JSONSerializer(File("Devices.json"))
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
        getDeviceByIndex(index)?.isFixed = true;
    }

    @Throws(Exception::class)
    fun load() {
        deviceList = serializer.read() as LinkedList<Device>
    }

    @Throws(Exception::class)
    fun store() {
        serializer.write(deviceList)
    }
}