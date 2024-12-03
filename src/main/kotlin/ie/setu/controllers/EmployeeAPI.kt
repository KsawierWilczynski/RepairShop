package ie.setu.controllers

import ie.setu.models.Employee
import ie.setu.persistance.JSONSerializer
import ie.setu.utils.formatListString
import java.io.File
import java.util.LinkedList

class EmployeeAPI {
    private var serializer: JSONSerializer = JSONSerializer(File("Employees.json"))
    private var employeeList = LinkedList<Employee>()

    fun addEmployee(employee: Employee): Boolean = employeeList.add(employee)

    fun listOfEmployees(): String = if (employeeList.isEmpty()) "No Employees" else formatListString(employeeList)

    fun numOfEmployees(): Int = employeeList.size

    fun removeEmployee(index: Int): Employee? = if (index < 0 || index >= employeeList.size) null else employeeList.removeAt(index)

    fun getEmployeeByIndex(index: Int): Employee? = if (index < 0 || index >= employeeList.size) null else employeeList[index]

    @Throws(Exception::class)
    fun load() {
        employeeList = serializer.read() as LinkedList<Employee>
    }

    @Throws(Exception::class)
    fun store() {
        serializer.write(employeeList)
    }
}
