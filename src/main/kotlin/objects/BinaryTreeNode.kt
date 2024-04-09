package at.fhv.objects

import kotlin.math.abs

class BinaryTreeNode(override val value: Int): TreeNode {

    override var left: BinaryTreeNode? = null
    override var right: BinaryTreeNode? = null
    
    override fun insert (value: Int): BinaryTreeNode {
        if (value < this.value) {
            left?.insert(value) ?: run { left = BinaryTreeNode(value) }
        } else {
            right?.insert(value) ?: run { right = BinaryTreeNode(value) }
        }

        return this;
    }
}