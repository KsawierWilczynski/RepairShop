package ie.setu.controllers

import ie.setu.models.Device
import ie.setu.models.Employee
import ie.setu.types.DeviceType
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DeviceAPITest {
    private var emptyDevices: DeviceAPI? = DeviceAPI()
    private var populatedDevices: DeviceAPI? = DeviceAPI()
    private var employee: Employee? = null
    private var brokenPhone: Device? = null
    private var brokenTablet: Device? = null
    private var brokenLaptop: Device? = null
    private var brokenDesktop: Device? = null
    private var workingPhone: Device? = null
    private var workingTablet: Device? = null
    private var workingLaptop: Device? = null
    private var workingDesktop: Device? = null

    @BeforeEach
    fun setup() {
        employee = Employee(112211, "John Doe", "13131313")
        brokenPhone = Device("123321", DeviceType.PHONE, "Broken Screen", employee!!)
        brokenTablet = Device("1234321", DeviceType.TABLET, "Broken buttons", employee!!)
        brokenLaptop = Device("123454321", DeviceType.LAPTOP, "Needs new keyboard", employee!!)
        brokenDesktop = Device("12345654321", DeviceType.DESKTOP, "Memory Issue", employee!!)
        workingPhone = Device("123321", DeviceType.PHONE, "Volume gone", employee!!, true)
        workingTablet = Device("1234321", DeviceType.TABLET, "Charging port needed", employee!!, true)
        workingLaptop = Device("123454321", DeviceType.LAPTOP, "Webcam stuck", employee!!, true)
        workingDesktop = Device("12345654321", DeviceType.DESKTOP, "Power supply need fix", employee!!, true)

        populatedDevices!!.addDevice(brokenPhone!!)
        populatedDevices!!.addDevice(brokenTablet!!)
        populatedDevices!!.addDevice(brokenLaptop!!)
        populatedDevices!!.addDevice(brokenDesktop!!)
        populatedDevices!!.addDevice(workingPhone!!)
        populatedDevices!!.addDevice(workingTablet!!)
        populatedDevices!!.addDevice(workingLaptop!!)
        populatedDevices!!.addDevice(workingDesktop!!)
    }

    @AfterEach
    fun tearDown() {
        employee = null
        brokenPhone = null
        brokenTablet = null
        brokenLaptop = null
        brokenDesktop = null
        workingPhone = null
        workingTablet = null
        workingLaptop = null
        workingDesktop = null
        populatedDevices = null
        emptyDevices = null
    }

    @Nested
    inner class AddDevices {
        @Test
        fun `adding a device to a populated list adds to Arraylist`() {
            val newDevice = Device("111", DeviceType.DESKTOP, "Its broken", employee!!)
            assertEquals(8, populatedDevices!!.numOfDevices())
            assertTrue(populatedDevices!!.addDevice(newDevice))
            assertEquals(9, populatedDevices!!.numOfDevices())
        }

        @Test
        fun `adding a device to an empty list adds to Arraylist`() {
            val newDevice = Device("111", DeviceType.DESKTOP, "Its broken", employee!!)
            assertEquals(0, emptyDevices!!.numOfDevices())
            assertTrue(emptyDevices!!.addDevice(newDevice))
            assertEquals(1, emptyDevices!!.numOfDevices())
        }
    }

    @Nested
    inner class ListDevices {
        @Test
        fun `listDevices returns no devices when arraylist is empty`() {
            assertEquals(0, emptyDevices!!.numOfDevices())
            assertTrue(emptyDevices!!.listOfDevices().lowercase().contains("no devices"))
        }

        @Test
        fun `listDevices returns Devices when ArrayList has devices stored`() {
            assertEquals(8, populatedDevices!!.numOfDevices())
            val devicesString = populatedDevices!!.listOfDevices().lowercase()
            assertTrue(devicesString.contains("screen"))
            assertTrue(devicesString.contains("buttons"))
            assertTrue(devicesString.contains("keyboard"))
            assertTrue(devicesString.contains("memory"))
            assertTrue(devicesString.contains("volume"))
            assertTrue(devicesString.contains("charging"))
            assertTrue(devicesString.contains("webcam"))
            assertTrue(devicesString.contains("power"))
        }
    }

    @Nested
    inner class RemoveDevice {
        @Test
        fun `Removing a device removes them from a populated list`() {
            assertEquals(8, populatedDevices!!.numOfDevices())
            assertTrue(
                populatedDevices!!
                    .removeDevice(0)!!
                    .issue
                    .lowercase()
                    .contains("screen"),
            )
            assertEquals(7, populatedDevices!!.numOfDevices())
        }

        @Test
        fun `Removing a device from an empty list does nothing`() {
            assertEquals(0, emptyDevices!!.numOfDevices())
            assertEquals(null, emptyDevices!!.removeDevice(0))
            assertEquals(0, emptyDevices!!.numOfDevices())
        }
    }

    @Nested
    inner class ListByIndex {
        @Test
        fun `Listing a device returns correct device`() {
            assertTrue(
                populatedDevices!!
                    .getDeviceByIndex(0)!!
                    .issue
                    .lowercase()
                    .contains("screen"),
            )
        }

        @Test
        fun `Listing an device from a empty list does nothing`() {
            assertEquals(null, emptyDevices!!.getDeviceByIndex(0))
        }
    }

    @Nested
    inner class ListByType {
        @Test
        fun `Listing device by type selects correct type`() {
            assertTrue(populatedDevices!!.getDeviceByType(DeviceType.DESKTOP).lowercase().contains("memory"))
            assertTrue(populatedDevices!!.getDeviceByType(DeviceType.LAPTOP).lowercase().contains("webcam"))
        }

        @Test
        fun `Listing by type when no devices returns no devices`() {
            assertTrue(emptyDevices!!.getDeviceByType(DeviceType.DESKTOP).lowercase().contains("no devices"))
        }
    }

    @Nested
    inner class ListByEmployee {
        @Test
        fun `Listing by employee with repairs in populated list shows devices`() {
            assertTrue(populatedDevices!!.getDeviceByEmployee(employee!!).lowercase().contains("memory"))
            assertTrue(populatedDevices!!.getDeviceByEmployee(employee!!).lowercase().contains("webcam"))
        }

        @Test
        fun `Listing by employee with no repairs in populated list shows no devices`() {
            val employeeTest = Employee(1231223123, "aaa", "StreetEasy")
            assertFalse(populatedDevices!!.getDeviceByEmployee(employeeTest).lowercase().contains("memory"))
            assertFalse(populatedDevices!!.getDeviceByEmployee(employeeTest).lowercase().contains("webcam"))
        }

        @Test
        fun `Listing by employee in empty list returns no devices`() {
            assertTrue(emptyDevices!!.getDeviceByEmployee(employee!!).lowercase().contains("no devices"))
        }
    }
}
