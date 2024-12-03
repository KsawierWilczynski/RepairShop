package ie.setu.utils

fun formatListString(listToFormat: List<Any>): String =
    listToFormat
        .joinToString(separator = "\n") { item ->
            listToFormat.indexOf(item).toString() + ": " + item.toString()
        }
