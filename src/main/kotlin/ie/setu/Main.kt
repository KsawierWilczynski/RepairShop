package ie.setu

import ie.setu.controllers.DeviceAPI
import ie.setu.controllers.EmployeeAPI
import ie.setu.models.Employee
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
                        2 -> deviceAPI.listOfDevices()
                        3 -> removeRepair()
                        0 -> println("Exiting Repairs...")
                        else -> println("Invalid Option Entered: $option")
                    }
                } while (repairOption != 0)
            }
            2 -> do {
                    val employeeOption = employeeMenu()
                    when (employeeOption) {
                        1 -> addEmployee()
                        2 -> employeeAPI.listOfEmployees()
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
         > |   2) Remove a repair          |
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
         > |   2) Remove an employee       |
         > ---------------------------------
         > |   0) Exit                     |
         > ---------------------------------
    """.trimMargin(">"))
    return readNextInt("==>> ")
}

fun addEmployee() {
    val pps = readNextInt("What's the Employee's PPS Number?")
    val name = readNextLine("What's the Employee's Name?")
    val phoneNo = readNextLine("What's the Employee's Phone Number?")
    if (employeeAPI.addEmployee(Employee(pps, name, phoneNo))) {
        println("Successfully added employee")
    } else {
        println("An Error has occurred")
    }
}

fun removeEmployee() {

}

fun addRepair() {

}

fun removeRepair() {

}