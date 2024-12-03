package ie.setu.controllers

import ie.setu.models.Employee
import ie.setu.controllers.EmployeeAPI
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class EmployeeAPITest {
    private var employeeOne:Employee? = null
    private var employeeTwo:Employee? = null
    private var populatedEmployees: EmployeeAPI? = null
    private var emptyEmployees: EmployeeAPI? = null

    @BeforeEach
    fun setup() {
        employeeOne =  Employee(112211, "John Dolan", "+353112")
        employeeTwo = Employee(11211, "John Doe", "+353221")
        emptyEmployees = EmployeeAPI()
        populatedEmployees = EmployeeAPI()

        populatedEmployees!!.addEmployee(employeeTwo!!)
        populatedEmployees!!.addEmployee(employeeOne!!)
    }

    @AfterEach
    fun tearDown() {
        employeeOne = null
        employeeTwo = null
        populatedEmployees = null
        emptyEmployees = null
    }

    @Nested
    inner class AddEmployee {

        @Test
        fun `Adding employee to an already populated Arraylist`() {
            assertEquals(2, populatedEmployees!!.numOfEmployees())
            assertTrue(populatedEmployees!!.addEmployee(Employee(12321, "Wednesday Adams", "911")))
            assertEquals(3, populatedEmployees!!.numOfEmployees())
        }

        @Test
        fun `Adding employee to an empty Arraylist`() {
            assertEquals(0, emptyEmployees!!.numOfEmployees())
            assertTrue(emptyEmployees!!.addEmployee(Employee(12321, "Wednesday Adams", "911")))
            assertEquals(1, emptyEmployees!!.numOfEmployees())
        }
    }

    @Nested
    inner class ListEmployee {

        @Test
        fun `Listing employees in an empty Arraylist returns no employees`() {
            assertEquals(0, emptyEmployees!!.numOfEmployees())
            assertTrue(emptyEmployees!!.listOfEmployees().lowercase().contains("no employees"))
        }

        @Test
        fun `Listing employees in an Arraylist returns employees`() {
            assertEquals(2, populatedEmployees!!.numOfEmployees())
            val output = populatedEmployees!!.listOfEmployees().lowercase()
            assertTrue(output.contains("dolan"))
            assertTrue(output.contains("doe"))
        }
    }
}