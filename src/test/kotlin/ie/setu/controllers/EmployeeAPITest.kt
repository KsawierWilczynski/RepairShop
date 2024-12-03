package ie.setu.controllers

import ie.setu.models.Employee
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class EmployeeAPITest {
    private var employeeOne: Employee? = null
    private var employeeTwo: Employee? = null
    private var populatedEmployees: EmployeeAPI? = null
    private var emptyEmployees: EmployeeAPI? = null

    @BeforeEach
    fun setup() {
        employeeOne = Employee(112211, "John Dolan", "+353112")
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
        fun `Adding employee to an already populated list`() {
            assertEquals(2, populatedEmployees!!.numOfEmployees())
            assertTrue(populatedEmployees!!.addEmployee(Employee(12321, "Wednesday Adams", "911")))
            assertEquals(3, populatedEmployees!!.numOfEmployees())
        }

        @Test
        fun `Adding employee to an empty list`() {
            assertEquals(0, emptyEmployees!!.numOfEmployees())
            assertTrue(emptyEmployees!!.addEmployee(Employee(12321, "Wednesday Adams", "911")))
            assertEquals(1, emptyEmployees!!.numOfEmployees())
        }
    }

    @Nested
    inner class ListEmployee {
        @Test
        fun `Listing employees in an empty list returns no employees`() {
            assertEquals(0, emptyEmployees!!.numOfEmployees())
            assertTrue(emptyEmployees!!.listOfEmployees().lowercase().contains("no employees"))
        }

        @Test
        fun `Listing employees in an list returns employees`() {
            assertEquals(2, populatedEmployees!!.numOfEmployees())
            val output = populatedEmployees!!.listOfEmployees().lowercase()
            assertTrue(output.contains("dolan"))
            assertTrue(output.contains("doe"))
        }
    }

    @Nested
    inner class RemoveEmployee {
        @Test
        fun `Removing an employee removes them from a populated list`() {
            assertEquals(2, populatedEmployees!!.numOfEmployees())
            assertTrue(
                populatedEmployees!!
                    .removeEmployee(0)!!
                    .name
                    .lowercase()
                    .contains("doe"),
            )
            assertEquals(1, populatedEmployees!!.numOfEmployees())
        }

        @Test
        fun `Removing an employee from an empty list does nothing`() {
            assertEquals(0, emptyEmployees!!.numOfEmployees())
            assertEquals(null, emptyEmployees!!.removeEmployee(0))
            assertEquals(0, emptyEmployees!!.numOfEmployees())
        }
    }

    @Nested
    inner class ListByIndex {
        @Test
        fun `Listing an employee returns correct employee`() {
            assertTrue(
                populatedEmployees!!
                    .getEmployeeByIndex(0)!!
                    .name
                    .lowercase()
                    .contains("doe"),
            )
        }

        @Test
        fun `Listing an employee from a empty list does nothing`() {
            assertEquals(null, emptyEmployees!!.getEmployeeByIndex(0))
        }
    }
}
