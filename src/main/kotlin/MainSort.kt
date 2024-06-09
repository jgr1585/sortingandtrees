package at.fhv

fun main() {
    val arr = intArrayOf(10, 7, 8, 9, 1, 5, 3, 4, 2, 6)
    quickSort(arr, pivotType = PivotType.LAST_ELEMENT)
    println("Sorted array: ${arr.joinToString()}")
}

fun quickSort(arr: IntArray, low: Int = 0, high: Int = arr.size - 1, pivotType: PivotType = PivotType.FIRST_ELEMENT) {
    if (low < high) {
        val pi = partition(arr, low, high, pivotType)

        quickSort(arr, low, pi - 1, pivotType)
        quickSort(arr, pi + 1, high, pivotType)
    }
}

fun partition(arr: IntArray, low: Int, high: Int, pivotType: PivotType): Int {
    val pivot = when (pivotType) {
        PivotType.FIRST_ELEMENT -> arr[low]
        PivotType.LAST_ELEMENT -> arr[high]
        PivotType.MIDDLE_ELEMENT -> arr[(low + high) / 2]
        PivotType.RANDOM_ELEMENT -> arr[(low..high).random()]
    }

    var i = low - 1
    for (j in low..<high) {
        if (arr[j] <= pivot) {
            i++
            swap(arr, i, j)
        }
    }

    swap(arr, i + 1, high)
    return i + 1
}

fun swap(arr: IntArray, i: Int, j: Int) {
    arr[i] = arr[j].also { arr[j] = arr[i] }
}

enum class PivotType {
    FIRST_ELEMENT,
    LAST_ELEMENT,
    MIDDLE_ELEMENT,
    RANDOM_ELEMENT
}