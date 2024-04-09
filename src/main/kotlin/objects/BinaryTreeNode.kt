package at.fhv.objects

class BinaryTreeNode(override val value: Int): TreeNode {

    override var left: BinaryTreeNode? = null
    override var right: BinaryTreeNode? = null

    private constructor(value: Int, left: BinaryTreeNode?, right: BinaryTreeNode?): this(value) {
        this.left = left
        this.right = right
    }
    
    override fun insert (value: Int): BinaryTreeNode {
        if (value < this.value) {
            left?.insert(value) ?: run { left = BinaryTreeNode(value) }
        } else {
            right?.insert(value) ?: run { right = BinaryTreeNode(value) }
        }

        return this
    }

    override fun remove(value: Int): BinaryTreeNode? {
        if (value < this.value) {
            left = left?.remove(value)
        } else {
            right = right?.remove(value)
        }


        if (value == this.value) {
            when {
                left == null -> return right
                right == null -> return left
                else -> {
                    val successor = right?.min()!!
                    BinaryTreeNode(
                        successor.value,
                        left,
                        right?.remove(successor.value)
                    )
                }
            }
        }

        return this
    }
}