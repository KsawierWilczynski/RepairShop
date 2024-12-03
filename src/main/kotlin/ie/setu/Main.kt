package ie.setu

import ie.setu.controllers.DeviceAPI
import ie.setu.controllers.EmployeeAPI
import ie.setu.models.Device
import ie.setu.models.Employee
import ie.setu.types.DeviceType
import ie.setu.utils.readNextInt
import ie.setu.utils.readNextLine

private val deviceAPI = DeviceAPI()
private val employeeAPI = EmployeeAPI()

fun main() {
    start()
}

fun start() {
    do {
        val option = mainMenu()
        when (option) {
            1 -> if (employeeAPI.numOfEmployees() <= 0) println("No Employees for Repair...") else {
                do {
                    val repairOption = repairMenu()
                    when (repairOption) {
                        1 -> addRepair()
                        2 -> println(deviceAPI.listOfDevices())
                        3 -> removeRepair()
                        4 -> getDeviceByEmployee()
                        5 -> getDeviceByType()
                        6 -> setDeviceToRepaired()
                        0 -> println("Exiting Repairs...")
                        else -> println("Invalid Option Entered: $option")
                    }
                } while (repairOption != 0)
            }
            2 -> do {
                    val employeeOption = employeeMenu()
                    when (employeeOption) {
                        1 -> addEmployee()
                        2 -> println(employeeAPI.listOfEmployees())
                        3 -> removeEmployee()
                        0 -> println("Exiting Employees...")
                        else -> println("Invalid Option Entered: $option")
                    }
                } while (employeeOption != 0)

            0 -> println("Exiting...")
            else -> println("Invalid Option Entered: $option")
        }
    } while (option != 0)
}

fun mainMenu(): Int {
    println("""
        > ----------------------------------
         > |        Repair Shop UI         |
         > ----------------------------------
         > | NOTE MENU                     |
         > |   1) Repair options           |
         > |   2) Employee options         |
         > ---------------------------------
         > |   0) Exit                     |
         > ---------------------------------
    """.trimMargin(">"))
    return readNextInt("==>> ")
}

fun repairMenu(): Int {
    println("""
        > ----------------------------------
         > |       Repair Options UI       |
         > ----------------------------------
         > | NOTE MENU                     |
         > |   1) Add a repair             |
         > |   2) List repairs             |
         > |   3) Remove a repair          |
         > |   4) List repairs by employee |
         > |   5) List repairs by type     |
         > |   6) Finish a repair          |
         > ---------------------------------
         > |   0) Exit                     |
         > ---------------------------------
    """.trimMargin(">"))
    return readNextInt("==>> ")
}

fun employeeMenu(): Int {
    println("""
        > ----------------------------------
         > |     Employee Options UI       |
         > ----------------------------------
         > | NOTE MENU                     |
         > |   1) Add an employee          |
         > |   2) List employees           |
         > |   3) Remove an employee       |
         > ---------------------------------
         > |   0) Exit                     |
         > ---------------------------------
    """.trimMargin(">"))
    return readNextInt("==>> ")
}

fun addEmployee() {
    val pps = readNextInt("What's the Employee's PPS Number? ")
    val name = readNextLine("What's the Employee's Name? ")
    val phoneNo = readNextLine("What's the Employee's Phone Number? ")
    if (employeeAPI.addEmployee(Employee(pps, name, phoneNo))) {
        println("Successfully added employee")
    } else {
        println("An Error has occurred")
    }
}

fun removeEmployee() {
    if (employeeAPI.numOfEmployees() > 0) {
        println(employeeAPI.listOfEmployees())
        val index = readNextInt("Please enter employee index to remove: ")
        if (index < 0 && index + 1 >= employeeAPI.numOfEmployees()) {
            println("Invalid Employee Index")
        } else {
            println("Removed: ${employeeAPI.removeEmployee(index)}")
        }
    } else {
        println("No employees stored")
    }
}

fun addRepair() {
    val serialNum = readNextLine("Please enter device serial number: ")
    var deviceType = readNextInt("Please Select device type:\n1. Phone\n2. Tablet\n3. Laptop\n4. Desktop\n")
    while (0 > deviceType || deviceType > 4) {
        println("Invalid deviceType")
        deviceType = readNextInt("Please Select device type:\n1. Phone\n2. Tablet\n3. Laptop\n4. Desktop\n")
    }
    var deviceTypeConstructor: DeviceType = DeviceType.PHONE
    when (deviceType) {
        1 -> deviceTypeConstructor = DeviceType.PHONE
        2 -> deviceTypeConstructor = DeviceType.TABLET
        3 -> deviceTypeConstructor = DeviceType.LAPTOP
        4 -> deviceTypeConstructor = DeviceType.DESKTOP
    }
    val issue = readNextLine("Please enter issue description: ")
    var input: Int
    var employee: Employee? = null
    while (employee == null) {
        println(employeeAPI.listOfEmployees())
        input = readNextInt("Please enter index of employee selected: ")
        employee = employeeAPI.getEmployeeByIndex(input)
    }
    if (deviceAPI.addDevice(Device(serialNum, deviceTypeConstructor, issue, employee))) {
        println("Repair successfully added")
    }
}

fun removeRepair() {
    if(deviceAPI.numOfDevices() > 0) {
        println(deviceAPI.listOfDevices())
        val index = readNextInt("Please enter device index to remove: ")
        if (index < 0 || index >= deviceAPI.numOfDevices()) {
            println("Invalid Device Index")
        } else {
            println("Removed: ${deviceAPI.removeDevice(index)}")
        }
    } else {
        println("No Devices")
    }
}

fun getDeviceByEmployee() {
    if (employeeAPI.numOfEmployees() > 0 && deviceAPI.numOfDevices() > 0) {
        var input: Int = -1
        while (input < 0 || input >= employeeAPI.numOfEmployees()) {
            println(employeeAPI.listOfEmployees())
            input = readNextInt("Please enter employee index")
        }
        val employee = employeeAPI.getEmployeeByIndex(input)
        if (employee == null) {
            println("Something has gone wrong")
            return
        }
        println(deviceAPI.getDeviceByEmployee(employee))
    } else {
        println("No selections available")
    }
}

fun getDeviceByType() {
    if (deviceAPI.numOfDevices() > 0) {
        var deviceType: Int
        do {
             deviceType = readNextInt("Please Select device type:\n1. Phone\n2. Tablet\n3. Laptop\n4. Desktop\n")
        } while (0 > deviceType || deviceType > 4)
        var deviceTypeConstructor: DeviceType = DeviceType.PHONE
        when (deviceType) {
            1 -> deviceTypeConstructor = DeviceType.PHONE
            2 -> deviceTypeConstructor = DeviceType.TABLET
            3 -> deviceTypeConstructor = DeviceType.LAPTOP
            4 -> deviceTypeConstructor = DeviceType.DESKTOP
        }
        println(deviceAPI.getDeviceByType(deviceTypeConstructor))
    } else {
        println("No devices")
    }
}

fun setDeviceToRepaired() {
    if (deviceAPI.numOfDevices() > 0) {
        println(deviceAPI.listOfDevices())
        val index = readNextInt("Please enter device index to fix: ")
        if (index < 0 || index >= deviceAPI.numOfDevices()) {
            println("Invalid Device Index")
        } else {
            println("Set device to repaired...")
            deviceAPI.fixDevice(index)
        }
    }
}