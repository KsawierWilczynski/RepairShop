package ie.setu.controllers

import ie.setu.models.Employee
import ie.setu.persistance.JSONSerializer
import ie.setu.utils.formatListString
import java.io.File
import java.util.LinkedList

/**
 * Manages a collection of employees, providing functionality for adding,
 * retrieving, listing, and removing employees. Supports data persistence
 * using JSON serialization.
 */
class EmployeeAPI {
    private var serializer: JSONSerializer = JSONSerializer(File("Employees.json"))
    private var employeeList = LinkedList<Employee>()

    /**
     * Adds an [Employee] to the collection.
     *
     * @param employee The [Employee] to be added.
     * @return `true` if the employee is successfully added, `false` otherwise.
     */
   fun addEmployee(employee: Employee): Boolean = employeeList.add(employee)

    /**
     * Retrieves a formatted string representation of all employees in the collection.
     *
     * @return A string of employee details, or "No Employees" if the collection is empty.
     */
    fun listOfEmployees(): String = if (employeeList.isEmpty()) "No Employees" else formatListString(employeeList)

    /**
     * Gets the total number of employees in the collection.
     *
     * @return The number of employees in the collection.
     */
    fun numOfEmployees(): Int = employeeList.size

    /**
     * Removes an [Employee] from the collection by its index.
     *
     * @param index The index of the [Employee] to remove.
     * @return The removed [Employee], or `null` if the index is invalid.
     */
    fun removeEmployee(index: Int): Employee? = if (index < 0 || index >= employeeList.size) null else employeeList.removeAt(index)

    /**
     * Retrieves an [Employee] from the collection by its index.
     *
     * @param index The index of the [Employee] to retrieve.
     * @return The [Employee] at the specified index, or `null` if the index is invalid.
     */
    fun getEmployeeByIndex(index: Int): Employee? = if (index < 0 || index >= employeeList.size) null else employeeList[index]

    /**
     * Loads the collection of employees from a JSON file.
     *
     * @throws Exception if an error occurs during loading.
     */
    @Throws(Exception::class)
    fun load() {
        employeeList = serializer.read() as LinkedList<Employee>
    }

    /**
     * Saves the collection of employees to a JSON file.
     *
     * @throws Exception if an error occurs during saving.
     */
    @Throws(Exception::class)
    fun store() {
        serializer.write(employeeList)
    }
}
