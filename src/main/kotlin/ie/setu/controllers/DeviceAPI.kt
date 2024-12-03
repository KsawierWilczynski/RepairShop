package ie.setu.controllers

import ie.setu.models.Device
import ie.setu.models.Employee
import ie.setu.persistance.JSONSerializer
import ie.setu.types.DeviceType
import ie.setu.utils.formatListString
import java.io.File
import java.util.LinkedList

/**
 * Manages a collection of devices, providing functionality for adding,
 * retrieving, filtering, and removing devices. Supports data persistence
 * through JSON serialization.
 */
class DeviceAPI {

    private var serializer: JSONSerializer = JSONSerializer(File("Devices.json"))
    private var deviceList = LinkedList<Device>()

    /**
     * Adds a [Device] to the collection.
     *
     * @param device The [Device] to be added.
     * @return `true` if the device is successfully added, `false` otherwise.
     */
    fun addDevice(device: Device): Boolean {
        return deviceList.add(device)
    }

    /**
     * Retrieves a formatted string representation of all devices in the collection.
     *
     * @return A string of device details, or "No Devices Stores" if the collection is empty.
     */
    fun listOfDevices(): String = if (deviceList.isEmpty()) "No Devices Stores" else formatListString(deviceList)

    /**
     * Gets the total number of devices in the collection.
     *
     * @return The number of devices in the collection.
     */
    fun numOfDevices(): Int = deviceList.size

    /**
     * Removes a [Device] from the collection by its index.
     *
     * @param index The index of the [Device] to remove.
     * @return The removed [Device], or `null` if the index is invalid.
     */
    fun removeDevice(index: Int): Device? = if (index < 0 || index >= deviceList.size) { null } else deviceList.removeAt(index)

    /**
     * Retrieves a [Device] from the collection by its index.
     *
     * @param index The index of the [Device] to retrieve.
     * @return The [Device] at the specified index, or `null` if the index is invalid.
     */
    fun getDeviceByIndex(index: Int): Device? = if (index < 0 || index >= deviceList.size) {
        null
    } else deviceList[index]

    /**
     * Filters devices by their [DeviceType] and retrieves a formatted string of the results.
     *
     * @param deviceType The [DeviceType] to filter devices by.
     * @return A string of matching devices, or "No Devices Stores" if none match or the collection is empty.
     */
    fun getDeviceByType(deviceType: DeviceType): String =
        if (deviceList.isEmpty()) "No Devices Stores" else formatListString(
            deviceList.stream().filter { it.type == deviceType }.toList()
        )

    /**
     * Filters devices by their assigned [Employee] and retrieves a formatted string of the results.
     *
     * @param employee The [Employee] to filter devices by.
     * @return A string of matching devices, or "No Devices Stores" if none match or the collection is empty.
     */
    fun getDeviceByEmployee(employee: Employee): String =
        if (deviceList.isEmpty()) "No Devices Stores" else formatListString(
            deviceList.stream().filter { it.employee == employee }.toList()
        )

    /**
     * Marks a device as fixed based on its index.
     *
     * @param index The index of the [Device] to mark as fixed.
     * If the index is invalid, no action is taken.
     */
    fun fixDevice(index: Int) {
        if (getDeviceByIndex(index) != null) {
            getDeviceByIndex(index)!!.isFixed = true
        }
    }

    /**
     * Loads the collection of devices from a JSON file.
     *
     * @throws Exception if an error occurs during loading.
     */
    @Throws(Exception::class)
    fun load() {
        deviceList = serializer.read() as LinkedList<Device>
    }

    /**
     * Saves the collection of devices to a JSON file.
     *
     * @throws Exception if an error occurs during saving.
     */
    @Throws(Exception::class)
    fun store() {
        serializer.write(deviceList)
    }
}