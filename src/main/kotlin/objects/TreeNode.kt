package at.fhv.objects

interface TreeNode {
    val value: Int
    val left: TreeNode?
    val right: TreeNode?

    fun insert(value: Int): TreeNode

    fun traverse(): List<Int> {
        return mutableListOf<Int>().apply {
            left?.let { addAll(it.traverse()) }
            add(value)
            right?.let { addAll(it.traverse()) }
        }
    }

    fun printList() {
        println(traverse())
    }
}