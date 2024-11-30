package ie.setu.controllers

import ie.setu.models.Employee
import ie.setu.utils.formatListString
import java.util.*

class EmployeeAPI {
    private var employeeList = LinkedList<Employee>()

    fun addEmployee(employee: Employee) {
        employeeList.add(employee)
    }

    fun listOfEmployees(): String = if (employeeList.isEmpty()) "No Employees" else formatListString(employeeList)

    fun numOfEmployees(): Int = employeeList.size

    fun removeEmployee(index: Int): Employee = employeeList.removeAt(index)
}