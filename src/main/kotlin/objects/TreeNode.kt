package at.fhv.objects

interface TreeNode {
    val value: Int
    val left: TreeNode?
    val right: TreeNode?

    fun insert(value: Int): TreeNode

    fun remove(value: Int): TreeNode?

    fun search(value: Int): TreeNode? {
        return when {
            value == this.value -> this
            value < this.value -> left?.search(value)
            else -> right?.search(value)
        }
    }

    fun min(): TreeNode {
        return left?.min() ?: this
    }

    fun max(): TreeNode {
        return right?.max() ?: this
    }

    fun predecessor(value: Int): TreeNode? {
        return when {
            value == this.value -> left?.max()
            value < this.value -> left?.predecessor(value) ?: this
            else -> right?.predecessor(value)
        }
    }

    fun successor(value: Int): TreeNode? {
        return when {
            value == this.value -> right?.min()
            value < this.value -> left?.successor(value)
            else -> right?.successor(value) ?: this
        }
    }

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