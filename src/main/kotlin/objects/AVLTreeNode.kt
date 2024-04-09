package at.fhv.objects

import kotlin.math.abs

class AVLTreeNode(
    override val value: Int,
): TreeNode {
    override var left: AVLTreeNode? = null
    override var right: AVLTreeNode? = null

    private constructor(value: Int, left: AVLTreeNode?, right: AVLTreeNode?): this(value) {
        this.left = left
        this.right = right
    }

    override fun insert(value: Int): AVLTreeNode {
        if (value < this.value) {
            left?.insert(value) ?: run { left = AVLTreeNode(value) }
        } else {
            right?.insert(value) ?: run { right = AVLTreeNode(value) }
        }

        return balance()
    }

    private fun height(): Int {
        return 1 + maxOf(left?.height() ?: 0, right?.height() ?: 0)
    }

    private fun balance(): AVLTreeNode {
        val leftHeight = left?.height() ?: 0
        val rightHeight = right?.height() ?: 0

        return when {
            leftHeight > rightHeight -> left?.balance() ?: this
            leftHeight < rightHeight -> right?.balance() ?: this
            else -> this
        }
    }
}